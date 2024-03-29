package at.technikum.springrestbackend.mapper;

import at.technikum.springrestbackend.dto.UserDto;
import at.technikum.springrestbackend.model.User;
import org.springframework.stereotype.Component;
import java.util.UUID;

@Component
public class UserMapper {

    public UserDto toDto(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setNickname(user.getNickname());
        userDto.setEmail(user.getEmail());
        userDto.setRole(user.getRole());
        userDto.setBirthday(user.getBirthday());
        userDto.setPassword(user.getPassword());
        userDto.setCountry(user.getCountry());
        userDto.setProfilePicture(user.getProfilePicture());
        userDto.setSongs(user.getSongs());
        userDto.setPlaylists(user.getPlaylists());
        userDto.setFollowerCount(user.getFollowerCount());
        userDto.setStatus(user.isStatus());
        return userDto;
    }

    public User toEntity(UserDto userDto){
        if (userDto.getId()==null){
            return new User (
                    UUID.randomUUID().toString(),
                    userDto.getNickname(),
                    userDto.getEmail(),
                    userDto.getRole(),
                    userDto.getBirthday(),
                    userDto.getPassword(),
                    userDto.getCountry(),
                    userDto.getProfilePicture(),
                    userDto.getSongs(),
                    userDto.getPlaylists(),
                    userDto.getFollowerCount(),
                    userDto.isStatus()
            );
        }
        return new User(
                userDto.getId(),
                userDto.getNickname(),
                userDto.getEmail(),
                userDto.getRole(),
                userDto.getBirthday(),
                userDto.getPassword(),
                userDto.getCountry(),
                userDto.getProfilePicture(),
                userDto.getSongs(),
                userDto.getPlaylists(),
                userDto.getFollowerCount(),
                userDto.isStatus()
        );
    }
}
