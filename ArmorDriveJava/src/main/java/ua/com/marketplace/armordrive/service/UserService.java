package ua.com.marketplace.armordrive.service;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ua.com.marketplace.armordrive.domain.User;
import ua.com.marketplace.armordrive.model.UserDTO;
import ua.com.marketplace.armordrive.repos.UserRepository;
import ua.com.marketplace.armordrive.util.NotFoundException;

import java.util.List;


@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(final UserRepository userRepository) {
        this.userRepository = userRepository;
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

    public Long create(final UserDTO userDTO) {
        final User user = new User();
        mapToEntity(userDTO, user);
        return userRepository.save(user).getId();
    }

    public void update(final Long id, final UserDTO userDTO) {
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

    private User mapToEntity(final UserDTO userDTO, final User user) {
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setBan(userDTO.getBan());
        user.setConfirmed(userDTO.getConfirmed());
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
