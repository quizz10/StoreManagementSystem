package se.iths.storemanagementsystem.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.iths.storemanagementsystem.entity.UserEntity;
import se.iths.storemanagementsystem.service.UserService;


import java.util.Optional;

@RestController
public class UserController {

    boolean isSetup;
    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("user/signup")
    public ResponseEntity<UserEntity> createUser(@RequestBody UserEntity user) {
        userService.addUser(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PatchMapping("employee/updaterole/{id}")
    public ResponseEntity<Optional<UserEntity>> updateRole(@PathVariable Long id, @RequestParam String role){
        Optional<UserEntity> user = userService.updateUserRole(id, role);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("user/setup")
    public void setupDb(){
        if (!isSetup){
            userService.addInitialAdmin();
        }
        isSetup = true;
    }

    @GetMapping("user/{id}")
    public ResponseEntity<Optional<UserEntity>> findUser(@PathVariable Long id) {
//        notFoundError(id);
        Optional<UserEntity> foundUser = userService.findUserById(id);
        return new ResponseEntity<>(foundUser, HttpStatus.OK);
    }

    @GetMapping("user")
    public ResponseEntity<Iterable<UserEntity>> findAllUsers() { // får no body istället för exception i insomnia
        Iterable<UserEntity> foundUsers = userService.findAllUsers();
        if (foundUsers == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(foundUsers, HttpStatus.OK);
    }

    @PatchMapping("employee/updateuser/{id}")
    public ResponseEntity<Optional<UserEntity>> updateUser(@PathVariable Long id, @RequestBody Optional<UserEntity> user) {
//            notFoundError(id);
            user = userService.updateUser(id, user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("admin/deleteuser/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

//    public ResponseEntity<Object> notFoundError(Long id) {
//        if (userService.findUserById(id).isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        return null;
//    }
}
