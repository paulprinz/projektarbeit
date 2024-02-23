package at.technikum.springrestbackend.service;


import at.technikum.springrestbackend.dto.UserDto;
import at.technikum.springrestbackend.exception.EntityNotFoundException;


import at.technikum.springrestbackend.model.User;
import at.technikum.springrestbackend.repository.UserRepository;
import org.springframework.stereotype.Service;

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

//    public UserDto updateById(String id, UserDto userDto){
//
//
//
//        return new UserDto(
//                id,
//                userDto.getNickname(),
//                userDto.get
//                );
//    }
//

}
