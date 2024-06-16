package at.technikum.springrestbackend.service;

import at.technikum.springrestbackend.dto.UserDto;
import at.technikum.springrestbackend.exception.EntityNotFoundException;
import at.technikum.springrestbackend.model.User;
import at.technikum.springrestbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Lazy
    @Autowired
    PasswordEncoder passwordEncoder;

    /**
     * Retrieves all users from the repository.
     *
     * @return a list of all users.
     */
    public List<User> findAll(){
        return userRepository.findAll();
    }

    /**
     * Retrieves a user by their ID.
     *
     * @param id the ID of the user to be retrieved.
     * @return the user with the specified ID.
     * @throws EntityNotFoundException if no user with the given ID is found.
     */
    public User findById(Long id) throws EntityNotFoundException{
        var result = userRepository.findById(id);
        if (result.isEmpty()) {
            throw new EntityNotFoundException("User with id: " + id + " not found.");
        }
        return result.get();
    }

    /**
     * Retrieves a user by their username.
     *
     * @param username the username of the user to be retrieved.
     * @return the user with the specified username.
     * @throws EntityNotFoundException if no user with the given username is found.
     */
    public User findByName(String username) throws EntityNotFoundException {
        var result = userRepository.findByName(username);
        if (result.isEmpty()) {
            throw new EntityNotFoundException("User with id: " + username + " not found.");
        }
        return result.get();
    }

    /**
     * Saves a new user to the repository.
     *
     * @param userDto the data transfer object containing user details.
     * @return the saved user.
     * @throws Exception if an error occurs while saving the user.
     */
    public User save(UserDto userDto) throws Exception {
        try {
            User user = new User();
            user.setUsername(userDto.getUsername());
            user.setEmail(userDto.getEmail());
            user.setRole(userDto.getRole());
            user.setBirthday(userDto.getBirthday());
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
            user.setCountry(userDto.getCountry());
            user.setFollowerCount(userDto.getFollowerCount());
            user.setStatus(userDto.isStatus());

            return userRepository.save(user);
        } catch (Exception e) {
            throw new Exception("Error saving user: " + e.getMessage(), e);
        }
    }

    /**
     * Updates an existing user in the repository.
     *
     * @param userDto the data transfer object containing updated user details.
     * @return the updated user.
     * @throws Exception if an error occurs while updating the user.
     */
    public User updateUser(UserDto userDto) throws Exception {
        try {
            User existingUser = findById(userDto.getId());

            existingUser.setUsername(userDto.getUsername());
            existingUser.setEmail(userDto.getEmail());
            existingUser.setRole(userDto.getRole());
            existingUser.setBirthday(userDto.getBirthday());
            existingUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
            existingUser.setCountry(userDto.getCountry());
            existingUser.setFollowerCount(userDto.getFollowerCount());
            existingUser.setStatus(userDto.isStatus());

            return userRepository.save(existingUser);
        } catch (Exception e) {
            throw new Exception("Error updating user: " + e.getMessage(), e);
        }
    }

    /**
     * Deletes a user from the repository by their ID.
     *
     * @param id the ID of the user to be deleted.
     * @throws Exception if an error occurs while deleting the user.
     */
    public void deleteUser(Long id) throws Exception {
        try {
            userRepository.deleteById(id);
        } catch (Exception e) {
            throw new Exception("Error deleting user: " + e.getMessage(), e);
        }
    }

    /**
     * Converts a User entity to a UserDto.
     *
     * @param user the user entity to be converted.
     * @return the converted UserDto.
     */
    public UserDto convertToDto(User user) {
        return new UserDto(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getRole(),
                user.getBirthday(),
                user.getPassword(),
                user.getCountry(),
                user.getFollowerCount(),
                user.isStatus()
        );
    }
}
