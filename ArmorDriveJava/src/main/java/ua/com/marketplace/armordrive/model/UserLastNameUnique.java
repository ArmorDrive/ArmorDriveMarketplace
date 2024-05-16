package ua.com.marketplace.armordrive.model;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;
import org.springframework.web.servlet.HandlerMapping;
import ua.com.marketplace.armordrive.service.UserService;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Map;

import static java.lang.annotation.ElementType.*;


/**
 * Validate that the lastName value isn't taken yet.
 */
@Target({ FIELD, METHOD, ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(
        validatedBy = UserLastNameUnique.UserLastNameUniqueValidator.class
)
public @interface UserLastNameUnique {

    String message() default "{Exists.user.lastName}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class UserLastNameUniqueValidator implements ConstraintValidator<UserLastNameUnique, String> {

        private final UserService userService;
        private final HttpServletRequest request;

        public UserLastNameUniqueValidator(final UserService userService,
                final HttpServletRequest request) {
            this.userService = userService;
            this.request = request;
        }

        @Override
        public boolean isValid(final String value, final ConstraintValidatorContext cvContext) {
            if (value == null) {
                // no value present
                return true;
            }
            @SuppressWarnings("unchecked") final Map<String, String> pathVariables =
                    ((Map<String, String>)request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE));
            final String currentId = pathVariables.get("id");
            if (currentId != null && value.equalsIgnoreCase(userService.get(Long.parseLong(currentId)).getLastName())) {
                // value hasn't changed
                return true;
            }
            return !userService.lastNameExists(value);
        }

    }

}
