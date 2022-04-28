package se.iths.storemanagementsystem.service;

import org.springframework.stereotype.Service;
import se.iths.storemanagementsystem.entity.Customer;
import se.iths.storemanagementsystem.entity.ShoppingCart;
import se.iths.storemanagementsystem.repository.CustomerRepository;
import se.iths.storemanagementsystem.repository.ShoppingCartRepository;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final ShoppingCartRepository shoppingCartRepository;
    public CustomerService(CustomerRepository customerRepository, ShoppingCartRepository shoppingCartRepository) {
        this.customerRepository = customerRepository;
        this.shoppingCartRepository = shoppingCartRepository;
    }


    public void addCustomer(Customer customer){
        ShoppingCart shoppingCart = new ShoppingCart();
        customer.setShoppingCart(shoppingCart);
        shoppingCart.setCustomer(customer);
        shoppingCartRepository.save(shoppingCart);
        customerRepository.save(customer);
    }

    public Optional<Customer> findCustomerById(Long id) {
        return Optional.ofNullable(customerRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    public Iterable<Customer> findAllCustomers() {
        return customerRepository.findAll();
    }

    public Optional<Customer> updateCustomer(Long id, Optional<Customer> customer) {
        Optional<Customer> foundCustomer = customerRepository.findById(id);

        foundCustomer.get().setFirstName(customer.get().getFirstName());
        foundCustomer.get().setLastName(customer.get().getLastName());
        return foundCustomer;
    }

    public void deleteCustomer(Long id) {
        Optional<Customer> foundCustomer = findCustomerById(id);
        customerRepository.delete(foundCustomer.get());
    }
}
