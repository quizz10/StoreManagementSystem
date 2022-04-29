package se.iths.storemanagementsystem.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import se.iths.storemanagementsystem.entity.RoleEntity;
import se.iths.storemanagementsystem.entity.UserEntity;
//import se.iths.storemanagementsystem.entity.ShoppingCart;
import se.iths.storemanagementsystem.repository.RoleRepository;
import se.iths.storemanagementsystem.repository.ShoppingCartRepository;
import se.iths.storemanagementsystem.repository.UserRepository;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;
    private final ShoppingCartRepository shoppingCartRepository;
    public UserService(RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder,
                       UserRepository userRepository, ShoppingCartRepository shoppingCartRepository) {
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRepository = userRepository;
        this.shoppingCartRepository = shoppingCartRepository;
    }


    public void addUser(UserEntity userEntity){

        userEntity.setPassword(bCryptPasswordEncoder.encode(userEntity.getPassword()));
//        RoleEntity role = roleRepository.findByName(userEntity.getRole().getName());
//        userEntity.setRole(role);

//        ShoppingCart shoppingCart = new ShoppingCart();
//        userEntity.setShoppingCart(shoppingCart);
//        shoppingCart.setCustomer(userEntity);
//        shoppingCartRepository.save(shoppingCart);
        userRepository.save(userEntity);
    }

    public Optional<UserEntity> findUserById(Long id) {
        return Optional.ofNullable(userRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    public Iterable<UserEntity> findAllUsers() {
        return userRepository.findAll();
    }

    public Optional<UserEntity> updateCustomer(Long id, Optional<UserEntity> userEntity) {
        Optional<UserEntity> foundCustomer = userRepository.findById(id);

        foundCustomer.get().setUsername(userEntity.get().getUsername());
        return foundCustomer;
    }

    public void deleteUser(Long id) {
        Optional<UserEntity> foundCustomer = findUserById(id);
        userRepository.delete(foundCustomer.get());
    }
}
