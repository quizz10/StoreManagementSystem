package se.iths.storemanagementsystem.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.iths.storemanagementsystem.entity.UserEntity;
import se.iths.storemanagementsystem.service.UserService;


import java.util.Optional;

@RestController
@RequestMapping("user")
public class UserController {

    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("signup")
    public ResponseEntity<UserEntity> createUser(@RequestBody UserEntity user) {
        userService.addUser(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<UserEntity>> findUser(@PathVariable Long id) {
//        notFoundError(id);
        Optional<UserEntity> foundUser = userService.findUserById(id);
        return new ResponseEntity<>(foundUser, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Iterable<UserEntity>> findAllUsers() { // får no body istället för exception i insomnia
        Iterable<UserEntity> foundUsers = userService.findAllUsers();
        if (foundUsers == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(foundUsers, HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity<Optional<UserEntity>> updateUser(@PathVariable Long id, Optional<UserEntity> user) {
//            notFoundError(id);
            user = userService.updateCustomer(id, user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
            userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    public ResponseEntity<Object> notFoundError(Long id) {
//        if (!customerService.findUserById(id).isPresent()) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        return null;
//    }
}