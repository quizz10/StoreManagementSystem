package se.iths.storemanagementsystem.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.iths.storemanagementsystem.entity.UserEntity;
import se.iths.storemanagementsystem.service.UserService;


import java.util.Optional;

@RestController
@RequestMapping("customer")
public class UserController {

    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserEntity> createCustomer(UserEntity customer) {
        userService.addCustomer(customer);
        return new ResponseEntity<>(customer, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<UserEntity>> findCustomer(@PathVariable Long id) {
//        notFoundError(id);
        Optional<UserEntity> foundCustomer = userService.findCustomerById(id);
        return new ResponseEntity<>(foundCustomer, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Iterable<UserEntity>> findAllCustomers() { // får no body istället för exception i insomnia
        Iterable<UserEntity> foundCustomers = userService.findAllCustomers();
        if (foundCustomers == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(foundCustomers, HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity<Optional<UserEntity>> updateCustomer(@PathVariable Long id, Optional<UserEntity> customer) {
//            notFoundError(id);
            customer = userService.updateCustomer(id, customer);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
            userService.deleteCustomer(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    public ResponseEntity<Object> notFoundError(Long id) {
//        if (!customerService.findCustomerById(id).isPresent()) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        return null;
//    }
}
