package se.iths.storemanagementsystem.service;

import org.springframework.stereotype.Service;
import se.iths.storemanagementsystem.entity.UserEntity;
//import se.iths.storemanagementsystem.entity.ShoppingCart;
import se.iths.storemanagementsystem.repository.ShoppingCartRepository;
import se.iths.storemanagementsystem.repository.UserRepository;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ShoppingCartRepository shoppingCartRepository;
    public UserService(UserRepository userRepository, ShoppingCartRepository shoppingCartRepository) {
        this.userRepository = userRepository;
        this.shoppingCartRepository = shoppingCartRepository;
    }


    public void addCustomer(UserEntity userEntity){
//        ShoppingCart shoppingCart = new ShoppingCart();
//        userEntity.setShoppingCart(shoppingCart);
//        shoppingCart.setCustomer(userEntity);
//        shoppingCartRepository.save(shoppingCart);
        userRepository.save(userEntity);
    }

    public Optional<UserEntity> findCustomerById(Long id) {
        return Optional.ofNullable(userRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    public Iterable<UserEntity> findAllCustomers() {
        return userRepository.findAll();
    }

    public Optional<UserEntity> updateCustomer(Long id, Optional<UserEntity> userEntity) {
        Optional<UserEntity> foundCustomer = userRepository.findById(id);

        foundCustomer.get().setUsername(userEntity.get().getUsername());
        return foundCustomer;
    }

    public void deleteCustomer(Long id) {
        Optional<UserEntity> foundCustomer = findCustomerById(id);
        userRepository.delete(foundCustomer.get());
    }
}
