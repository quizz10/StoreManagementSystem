package se.iths.storemanagementsystem.service;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionSystemException;
import se.iths.storemanagementsystem.entity.*;
import se.iths.storemanagementsystem.exceptions.customexceptions.DuplicateEmailException;
import se.iths.storemanagementsystem.exceptions.customexceptions.NotFoundException;
import se.iths.storemanagementsystem.exceptions.customexceptions.ShortPasswordException;
import se.iths.storemanagementsystem.exceptions.customexceptions.WrongEmailFormatException;
import se.iths.storemanagementsystem.jms.sender.Sender;
import se.iths.storemanagementsystem.repository.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
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
    private final Sender jmsSender;

    public UserService(RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder,
                       UserRepository userRepository, ShoppingCartRepository shoppingCartRepository, DepartmentRepository departmentRepository, ItemRepository itemRepository, StoreRepository storeRepository, Sender jmsSender) {
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRepository = userRepository;
        this.shoppingCartRepository = shoppingCartRepository;
        this.departmentRepository = departmentRepository;
        this.itemRepository = itemRepository;
        this.storeRepository = storeRepository;
        this.jmsSender = jmsSender;
    }

    public UserEntity addUser(UserEntity userEntity) {
        if (userEntity.getPassword().length() < 8) {
            throw new ShortPasswordException("Password too short, minimum 8 characters.");
        }

        UserEntity savedUser = null;
        userEntity.setPassword(bCryptPasswordEncoder.encode(userEntity.getPassword()));
        RoleEntity role = roleRepository.findByName("ROLE_USER");
        userEntity.addRole(role);

        ShoppingCartEntity shoppingCart = new ShoppingCartEntity();
        userEntity.setShoppingCart(shoppingCart);
        shoppingCart.setUser(userEntity);
        shoppingCartRepository.save(shoppingCart);
        try {
            savedUser = userRepository.save(userEntity);
        } catch (TransactionSystemException | DataIntegrityViolationException t) {
            if (t instanceof DataIntegrityViolationException) {
                throw new DuplicateEmailException("There already is a user with that email");
            }
            if (t instanceof TransactionSystemException)
                throw new WrongEmailFormatException("Email format is invalid.");
        }

        jmsSender.sendMessage(savedUser);
        return savedUser;
    }

    public Optional<UserEntity> findUserById(Long id) {
        if (userRepository.findById(id).isPresent()) {
            return userRepository.findById(id);
        } else throw new NotFoundException("Could not find user with id " + id);
    }

    public List<UserEntity> getAllUsersAsList() {
        return userRepository.findAll();
    }

    public Optional<UserEntity> updateUser(Long id, Optional<UserEntity> userEntity) {
        Optional<UserEntity> foundUser = findUserById(id);
        setFields(userEntity, foundUser);
        userRepository.save(foundUser.get());
        return foundUser;
    }

    public void deleteUser(Long id) {
        Optional<UserEntity> foundUser = findUserById(id);
        foundUser.get().setShoppingCart(null);
        if (foundUser.get().getRoles().stream().anyMatch(role -> role.getName().equals("ROLE_EMPLOYEE"))) {
            foundUser.get().getDepartment().removeEmployee(foundUser.get());
            foundUser.get().setDepartment(null);
        }
        foundUser.get().setRoles(null);
        userRepository.delete(foundUser.get());
    }

    public Optional<UserEntity> updateUserRole(Long id, String roleName) {
        Optional<UserEntity> foundUser = findUserById(id);
        RoleEntity role = roleRepository.findByName(roleName);
        if (role == null) {
            throw new NotFoundException("Could not find role with name " + roleName);
        }
        foundUser.get().addRole(role);
        userRepository.save(foundUser.get());
        return Optional.ofNullable(foundUser).orElseThrow(EntityNotFoundException::new);
    }

    private void setFields(Optional<UserEntity> userEntity, Optional<UserEntity> foundUser) {
        if (!(userEntity.get().getEmail() == null)) {
            foundUser.get().setEmail(userEntity.get().getEmail());
        }
        if (!(userEntity.get().getPassword() == null)) {
            foundUser.get().setPassword(bCryptPasswordEncoder.encode(userEntity.get().getPassword()));
        }
    }

    public void initialSetup() {
        StoreEntity store = new StoreEntity("ICA");

        DepartmentEntity frukt = new DepartmentEntity("Frukt & Gr??nt");

        ItemEntity citron = new ItemEntity("Citron", 10);
        ItemEntity banan = new ItemEntity("Banan", 29);

        UserEntity admin = new UserEntity("admin@admin.se", bCryptPasswordEncoder.encode("123123123123"));
        UserEntity user = new UserEntity("testuser@ica.se", bCryptPasswordEncoder.encode("123123123123"));
        UserEntity employee = new UserEntity("employee@ica.se", bCryptPasswordEncoder.encode("123123123123"));

        roleRepository.save(new RoleEntity("ROLE_ADMIN"));
        roleRepository.save(new RoleEntity("ROLE_USER"));
        roleRepository.save(new RoleEntity("ROLE_EMPLOYEE"));

        employee.addRole(roleRepository.findByName("ROLE_EMPLOYEE"));
        admin.addRole(roleRepository.findByName("ROLE_ADMIN"));
        user.addRole(roleRepository.findByName("ROLE_USER"));

        ShoppingCartEntity shoppingCart = new ShoppingCartEntity();
        shoppingCart.setUser(user);
        user.setShoppingCart(shoppingCart);
        shoppingCartRepository.save(shoppingCart);

        store.addDepartment(frukt);
        frukt.addEmployee(employee);
        frukt.addItem(citron);
        frukt.addItem(banan);

        storeRepository.save(store);
        departmentRepository.save(frukt);
        itemRepository.save(citron);
        itemRepository.save(banan);
        userRepository.save(user);
        userRepository.save(admin);
        userRepository.save(employee);
    }
}
