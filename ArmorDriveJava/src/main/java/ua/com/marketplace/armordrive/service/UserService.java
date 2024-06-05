package ua.com.marketplace.armordrive.service;

import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.com.marketplace.armordrive.domain.User;
import ua.com.marketplace.armordrive.exceptions.UserAlreadyExistException;
import ua.com.marketplace.armordrive.model.UserCreationDto;
import ua.com.marketplace.armordrive.model.UserDTO;
import ua.com.marketplace.armordrive.repos.UserRepository;
import ua.com.marketplace.armordrive.exceptions.NotFoundException;

import java.util.List;


@Service
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserService(final UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UserDTO> findAll() {
        final List<User> users = userRepository.findAll(Sort.by("id"));
        return users.stream()
                .map(user -> mapToDTO(user, new UserDTO()))
                .toList();
    }

    public UserDTO get(final Long id) {
        return userRepository.findById(id)
                .map(user -> mapToDTO(user, new UserDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final UserCreationDto userCreationDto) {

        if(emailExists(userCreationDto.email())){
            throw new UserAlreadyExistException("User with email: " + userCreationDto.email() + " is already exists");
        }
        if(phoneNumberExists(userCreationDto.phoneNumber())){
            throw new UserAlreadyExistException("User with phone number: " + userCreationDto.phoneNumber() + " is already exists");
        }

        final User newUser = new User();
        newUser.setFirstName(userCreationDto.firstName());
        newUser.setLastName(userCreationDto.lastName());
        newUser.setEmail(userCreationDto.email());
        newUser.setPhoneNumber(userCreationDto.phoneNumber());
        newUser.setPassword(passwordEncoder.encode(userCreationDto.password()));
        return userRepository.save(newUser).getId();
    }

    public void update(final Long id, final UserCreationDto userDTO) {
        final User user = userRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(userDTO, user);
        userRepository.save(user);
    }

    public void delete(final Long id) {
        userRepository.deleteById(id);
    }

    private UserDTO mapToDTO(final User user, final UserDTO userDTO) {
        userDTO.setId(user.getId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        userDTO.setBan(user.getBan());
        userDTO.setConfirmed(user.getConfirmed());
        return userDTO;
    }

    private User mapToEntity(final UserCreationDto userCreationDto, final User user) {
        user.setFirstName(userCreationDto.firstName());
        user.setLastName(userCreationDto.lastName());
        user.setEmail(userCreationDto.email());
        user.setPassword(userCreationDto.password());
        user.setPhoneNumber(userCreationDto.phoneNumber());
        return user;
    }

    public boolean firstNameExists(final String firstName) {
        return userRepository.existsByFirstNameIgnoreCase(firstName);
    }

    public boolean lastNameExists(final String lastName) {
        return userRepository.existsByLastNameIgnoreCase(lastName);
    }

    public boolean emailExists(final String email) {
        return userRepository.existsByEmailIgnoreCase(email);
    }

    public boolean phoneNumberExists(final String phoneNumber) {
        return userRepository.existsByPhoneNumberIgnoreCase(phoneNumber);
    }

}
