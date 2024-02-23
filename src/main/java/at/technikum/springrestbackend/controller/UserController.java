package at.technikum.springrestbackend.controller;


import at.technikum.springrestbackend.dto.UserDto;
import at.technikum.springrestbackend.mapper.UserMapper;
import at.technikum.springrestbackend.model.User;
import at.technikum.springrestbackend.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;


    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping("/all")
    public List<UserDto> getAll(){
        return userService.findAll().stream().map(userMapper::toDto).toList();
    }

    @GetMapping("/{id}")
    public UserDto getById(@PathVariable String id){
        User user = userService.find(id);
        return userMapper.toDto(user);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto create(@RequestBody @Valid UserDto userDto){
        User user = userMapper.toEntity(userDto);
        user = userService.save(user);
        return userMapper.toDto(user);
    }
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable String id){
        userService.deleteById(id);
    }

//    @PutMapping("/{id}")
//    public void updateById(@PathVariable String id){
//        userService.
//    }

//    @PostMapping
//
//    @DeleteMapping
}
