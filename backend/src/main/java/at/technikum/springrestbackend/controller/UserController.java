package at.technikum.springrestbackend.controller;

import at.technikum.springrestbackend.dto.UserDetailsDto;
import at.technikum.springrestbackend.dto.UserDto;
import at.technikum.springrestbackend.exception.EntityNotFoundException;
import at.technikum.springrestbackend.model.User;
import at.technikum.springrestbackend.security.principal.UserPrincipal;
import at.technikum.springrestbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/users")
@CrossOrigin
public class UserController {

    @Autowired
    UserService userService;


    @GetMapping("/get-all")
    public List<UserDto> getAllUsers(){
        return userService.findAll().stream().map(userService::convertToDto).toList();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) throws EntityNotFoundException {
        User user = userService.findById(id);
        return ResponseEntity.ok(userService.convertToDto(user));
    }

    @GetMapping("/me")
    public ResponseEntity<UserDetailsDto> getMyUser(
            @AuthenticationPrincipal UserPrincipal userPrincipal) throws EntityNotFoundException {

        User user = userService.findById(userPrincipal.getId());
        return ResponseEntity.ok(userService.convertToUserDetailsDto(user));
    }

    @GetMapping("/get-user-by-name/{username}")
    public ResponseEntity<UserDto> getUserByName(@PathVariable String username) throws  EntityNotFoundException {
        User user = userService.findByName(username);
        return ResponseEntity.ok(userService.convertToDto(user));
    }

    @PostMapping("/create")
    public ResponseEntity<UserDto> create(@RequestBody UserDto userDto) throws Exception {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userService.convertToDto(userService.save(userDto)));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        try {
            userService.deleteUser(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/update")
    public ResponseEntity<UserDto> updateById(@RequestBody UserDto userDto) {
        try {
            User updatedUser = userService.updateUser(userDto);
            return ResponseEntity.ok(userService.convertToDto(updatedUser));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
