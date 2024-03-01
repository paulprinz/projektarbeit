package at.technikum.springrestbackend.service;


import at.technikum.springrestbackend.dto.UserDto;
import at.technikum.springrestbackend.exception.EntityNotFoundException;


import at.technikum.springrestbackend.mapper.UserMapper;
import at.technikum.springrestbackend.model.Picture;
import at.technikum.springrestbackend.model.Playlist;
import at.technikum.springrestbackend.model.Song;
import at.technikum.springrestbackend.model.User;
import at.technikum.springrestbackend.repository.UserRepository;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User find(String id){
        return userRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }


    public User save(User user){
        return userRepository.save(user);
    }

    public void delete(User user){
        userRepository.delete(user);
    }

    public void deleteById(String id){
        userRepository.deleteById(id);
    }

    public User updateById(String id, UserDto userDto){
        User existingUser = userRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        existingUser.setNickname(userDto.getNickname());
        existingUser.setEmail(userDto.getEmail());
        existingUser.setRole(userDto.getRole());
        existingUser.setBirthday(userDto.getBirthday());
        existingUser.setPassword(userDto.getPassword());
        existingUser.setCountry(userDto.getCountry());
        existingUser.setProfilePicture(userDto.getProfilePicture());
        existingUser.setSongs(userDto.getSongs());
        existingUser.setPlaylists(userDto.getPlaylists());
        existingUser.setFollowerCount(userDto.getFollowerCount());
        existingUser.setStatus(userDto.isStatus());

        return userRepository.save(existingUser);
    }
}
