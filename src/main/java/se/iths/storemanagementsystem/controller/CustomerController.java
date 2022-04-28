package se.iths.storemanagementsystem.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.iths.storemanagementsystem.entity.Customer;
import se.iths.storemanagementsystem.service.CustomerService;


import java.util.Optional;

@RestController
@RequestMapping("customer")
public class CustomerController {

    CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<Customer> createCustomer(Customer customer) {
        customerService.addCustomer(customer);
        return new ResponseEntity<>(customer, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<Customer>> findCustomer(@PathVariable Long id) {
//        notFoundError(id);
        Optional<Customer> foundCustomer = customerService.findCustomerById(id);
        return new ResponseEntity<>(foundCustomer, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Iterable<Customer>> findAllCustomers() { // får no body istället för exception i insomnia
        Iterable<Customer> foundCustomers = customerService.findAllCustomers();
        if (foundCustomers == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(foundCustomers, HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity<Optional<Customer>> updateCustomer(@PathVariable Long id, Optional<Customer> customer) {
//            notFoundError(id);
            customer = customerService.updateCustomer(id, customer);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
            customerService.deleteCustomer(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    public ResponseEntity<Object> notFoundError(Long id) {
//        if (!customerService.findCustomerById(id).isPresent()) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        return null;
//    }
}
