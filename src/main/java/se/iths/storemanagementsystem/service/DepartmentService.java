package se.iths.storemanagementsystem.service;

import org.springframework.stereotype.Service;
import se.iths.storemanagementsystem.entity.Department;
import se.iths.storemanagementsystem.entity.Item;
import se.iths.storemanagementsystem.entity.UserEntity;
import se.iths.storemanagementsystem.repository.DepartmentRepository;
import se.iths.storemanagementsystem.repository.ItemRepository;
import se.iths.storemanagementsystem.repository.UserRepository;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;

    public DepartmentService(DepartmentRepository departmentRepository, UserRepository userRepository, ItemRepository itemRepository) {
        this.departmentRepository = departmentRepository;
        this.userRepository = userRepository;
        this.itemRepository = itemRepository;
    }

    public void addDepartment(Department department) {
        departmentRepository.save(department);
    }

    public Optional<Department> findDepartmentById(Long id) {
        return Optional.ofNullable(departmentRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    public Iterable<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public Optional<Department> updateDepartment(Long id, String departmentName) {
        Optional<Department> foundDepartment = departmentRepository.findById(id);

        if (foundDepartment.isPresent()) {
            setFields(departmentName, foundDepartment);
        } else {
            throw new RuntimeException("Could not find");
        }
        departmentRepository.save(foundDepartment.get());
        return foundDepartment;
    }

    public void deleteDepartment(Long id) {
        Optional<Department> foundDepartment = findDepartmentById(id);
        departmentRepository.delete(foundDepartment.get());
    }

    public Optional<UserEntity> findUserById(Long id) {
        return Optional.ofNullable(userRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    public Optional<UserEntity> linkEmployeeToDepartment(Long departmentId, Long userId) {

        Optional<Department> foundDepartment = findDepartmentById(departmentId);
        Optional<UserEntity> foundUser = findUserById(userId);
        if (foundUser.get().getRole().getName().equals("EMPLOYEE")) {
            foundDepartment.get().addEmployee(foundUser.get());
            departmentRepository.save(foundDepartment.get());
        }
        return foundUser;
    }

    public Optional<UserEntity> unlinkEmployeeToDepartment(Long departmentId, Long userId) {
        Optional<Department> foundDepartment = findDepartmentById(departmentId);
        Optional<UserEntity> foundUser = findUserById(userId);
        foundDepartment.get().removeEmployee(foundUser.get());
        return foundUser;
    }

    private void setFields(String departmentName, Optional<Department> foundDepartment) {
        if (!(departmentName == null)) {
            foundDepartment.get().setDepartmentName(departmentName);
        }
    }

    public Optional<Department> linkItemToDepartment(Long departmentId, Long itemId) {
        Optional<Department> foundDepartment = findDepartmentById(departmentId);
        Optional<Item> foundItem = itemRepository.findById(itemId);

        foundDepartment.get().addItem(foundItem.get());

        return foundDepartment;
    }

    public Optional<Department> unLinkItemFromDepartment(Long departmentId, Long itemId) {
        Optional<Department> foundDepartment = findDepartmentById(departmentId);
        Optional<Item> foundItem = itemRepository.findById(itemId);

        foundDepartment.get().removeItem(foundItem.get());

        return foundDepartment;
    }
}