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
 * Validate that the firstName value isn't taken yet.
 */
@Target({ FIELD, METHOD, ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(
        validatedBy = UserFirstNameUnique.UserFirstNameUniqueValidator.class
)
public @interface UserFirstNameUnique {

    String message() default "{Exists.user.firstName}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class UserFirstNameUniqueValidator implements ConstraintValidator<UserFirstNameUnique, String> {

        private final UserService userService;
        private final HttpServletRequest request;

        public UserFirstNameUniqueValidator(final UserService userService,
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
            if (currentId != null && value.equalsIgnoreCase(userService.get(Long.parseLong(currentId)).getFirstName())) {
                // value hasn't changed
                return true;
            }
            return !userService.firstNameExists(value);
        }

    }

}
