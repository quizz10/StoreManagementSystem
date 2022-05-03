package se.iths.storemanagementsystem.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import se.iths.storemanagementsystem.entity.*;
import se.iths.storemanagementsystem.repository.*;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class UserService {

    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;
    private final ShoppingCartRepository shoppingCartRepository;
    private final DepartmentRepository departmentRepository;
    private final ItemRepository itemRepository;
    private final StoreRepository storeRepository;

    public UserService(RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder,
                       UserRepository userRepository, ShoppingCartRepository shoppingCartRepository, DepartmentRepository departmentRepository, ItemRepository itemRepository, StoreRepository storeRepository) {
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRepository = userRepository;
        this.shoppingCartRepository = shoppingCartRepository;
        this.departmentRepository = departmentRepository;
        this.itemRepository = itemRepository;
        this.storeRepository = storeRepository;
    }


    public void addUser(UserEntity userEntity) {
        userEntity.setPassword(bCryptPasswordEncoder.encode(userEntity.getPassword()));
        RoleEntity role = roleRepository.findByName("CUSTOMER");
        userEntity.setRole(role);

        ShoppingCartEntity shoppingCart = new ShoppingCartEntity();
        userEntity.setShoppingCart(shoppingCart);
        shoppingCart.setUser(userEntity);
        shoppingCartRepository.save(shoppingCart);
        userRepository.save(userEntity);
    }

    // Method for adding an admin and setting up admin+customer roles, is run only once.
    public void addInitialAdmin() {
        StoreEntity store = new StoreEntity("ICA");
        DepartmentEntity frukt = new DepartmentEntity("Frukt & Grönt");
        UserEntity admin = new UserEntity("Admin", "admin@admin.se", bCryptPasswordEncoder.encode("123"));
        UserEntity customer = new UserEntity("Customer", "testuser@ica.se", bCryptPasswordEncoder.encode("123"));
        UserEntity employee = new UserEntity("Employee", "employee@ica.se", bCryptPasswordEncoder.encode("123"));
        ItemEntity citron = new ItemEntity("Citron", 10);
        ItemEntity banan = new ItemEntity("Banan", 29);

        roleRepository.save(new RoleEntity("ADMIN"));
        roleRepository.save(new RoleEntity("CUSTOMER"));
        RoleEntity employeeRole = roleRepository.save(new RoleEntity("EMPLOYEE"));

        employee.setRole(employeeRole);
        admin.setRole(roleRepository.findByName("ADMIN"));
        addUser(customer);
        store.addDepartment(frukt);
        frukt.addEmployee(employee);
        frukt.addItem(citron);
        frukt.addItem(banan);
        storeRepository.save(store);
        departmentRepository.save(frukt);
        itemRepository.save(citron);
        itemRepository.save(banan);
        userRepository.save(admin);
        userRepository.save(employee);
    }

    public Optional<UserEntity> updateUserRole(Long id, String roleName) {
        Optional<UserEntity> foundUser = userRepository.findById(id);
        RoleEntity role = roleRepository.findByName(roleName);
        if (role == null) {
            // throw new WebApplicationException()
            //Todo: Add custom exception for null role ^
        }
        foundUser.get().setRole(role);


        return Optional.ofNullable(foundUser).orElseThrow(EntityNotFoundException::new);
    }

    public Optional<UserEntity> findUserById(Long id) {
        return Optional.ofNullable(userRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    public Iterable<UserEntity> findAllUsers() {
        return userRepository.findAll();
    }

    public Optional<UserEntity> updateUser(Long id, Optional<UserEntity> userEntity) {
        Optional<UserEntity> foundUser = userRepository.findById(id);

        if (foundUser.isPresent()) {
            setFields(userEntity, foundUser);
        } else {
            throw new RuntimeException("Could not find");
        }
        userRepository.save(foundUser.get());
        return foundUser;
    }

    public void deleteUser(Long id) {
        Optional<UserEntity> foundCustomer = findUserById(id);
        foundCustomer.get().setShoppingCart(null);
        foundCustomer.get().setRole(null);
        userRepository.delete(foundCustomer.get());
    }


    private void setFields(Optional<UserEntity> userEntity, Optional<UserEntity> foundUser) {
        if (!(userEntity.get().getUsername() == null)) {
            foundUser.get().setUsername(userEntity.get().getUsername());
        }
        if (!(userEntity.get().getEmail() == null)) {
            foundUser.get().setEmail(userEntity.get().getEmail());
        }
        if (!(userEntity.get().getPassword() == null)) {
            foundUser.get().setPassword(bCryptPasswordEncoder.encode(userEntity.get().getPassword()));
        }
    }
}
