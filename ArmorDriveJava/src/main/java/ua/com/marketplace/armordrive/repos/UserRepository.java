package ua.com.marketplace.armordrive.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.marketplace.armordrive.domain.User;


public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByFirstNameIgnoreCase(String firstName);

    boolean existsByLastNameIgnoreCase(String lastName);

    boolean existsByEmailIgnoreCase(String email);

    boolean existsByPhoneNumberIgnoreCase(String phoneNumber);

}
