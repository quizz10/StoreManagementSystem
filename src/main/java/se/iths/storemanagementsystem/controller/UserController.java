package se.iths.storemanagementsystem.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.iths.storemanagementsystem.dto.UserDto;
import se.iths.storemanagementsystem.entity.UserEntity;
import se.iths.storemanagementsystem.service.UserService;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private ModelMapper modelMapper;

    boolean isSetup;
    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("user/signup")
    public ResponseEntity<UserDto> createUser(@RequestBody UserEntity user) {
        UserEntity savedUser = userService.addUser(user);
        return new ResponseEntity<>(modelMapper.map(savedUser, UserDto.class), HttpStatus.CREATED);
    }

    @PatchMapping("admin/updaterole/{id}")
    public ResponseEntity<UserDto> updateRole(@PathVariable Long id, @RequestParam String role) {
        Optional<UserEntity> user = userService.updateUserRole(id, role);
        return new ResponseEntity<>(modelMapper.map(user, UserDto.class), HttpStatus.OK);
    }

    @GetMapping("user/setup")
    public void setupDb() {
        if (!userService.getAllUsersAsList().isEmpty()) {
            isSetup = true;
        }

        if (!isSetup) {
            userService.initialSetup();
            isSetup = true;
        }
    }

    @GetMapping("user/{id}")
    public ResponseEntity<UserDto> findUser(@PathVariable Long id) {
        Optional<UserEntity> foundUser = userService.findUserById(id);
        return new ResponseEntity<>(modelMapper.map(foundUser, UserDto.class), HttpStatus.OK);
    }

    @GetMapping("user")
    public ResponseEntity<List<UserDto>> findAllUsers() {
        List<UserDto> foundUsers = userService.getAllUsersAsList().stream()
                .map(user -> modelMapper.map(user, UserDto.class)).toList();

        if (foundUsers == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(foundUsers, HttpStatus.OK);
    }

    @PatchMapping("admin/updateuser/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody Optional<UserEntity> user) {
        Optional<UserEntity> updatedUser = userService.updateUser(id, user);
        return new ResponseEntity<>(modelMapper.map(updatedUser, UserDto.class), HttpStatus.OK);
    }

    @DeleteMapping("admin/deleteuser/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);

        return new ResponseEntity<>("Successfully deleted user with id " + id, HttpStatus.OK);
    }
}
