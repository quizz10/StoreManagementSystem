package se.iths.storemanagementsystem.service;

import org.springframework.stereotype.Service;
import se.iths.storemanagementsystem.entity.DepartmentEntity;
import se.iths.storemanagementsystem.entity.ItemEntity;
import se.iths.storemanagementsystem.entity.StoreEntity;
import se.iths.storemanagementsystem.entity.UserEntity;
import se.iths.storemanagementsystem.repository.DepartmentRepository;
import se.iths.storemanagementsystem.repository.ItemRepository;
import se.iths.storemanagementsystem.repository.UserRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
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

    public DepartmentEntity addDepartment(DepartmentEntity department) {
        DepartmentEntity savedDepartment = departmentRepository.save(department);
        return savedDepartment;
    }

    public Optional<DepartmentEntity> findDepartmentById(Long id) {
        return Optional.ofNullable(departmentRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    public List<DepartmentEntity> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public Optional<DepartmentEntity> updateDepartment(Long id, String departmentName) {
        Optional<DepartmentEntity> foundDepartment = departmentRepository.findById(id);

        if (foundDepartment.isPresent()) {
            setFields(departmentName, foundDepartment);
        } else {
            throw new RuntimeException("Could not find");
        }
        departmentRepository.save(foundDepartment.get());
        return foundDepartment;
    }

    public void deleteDepartment(Long id) {
        Optional<DepartmentEntity> foundDepartment = findDepartmentById(id);
        StoreEntity store = foundDepartment.get().getStore();
        if (store != null) {
            store.removeDepartment(foundDepartment.get());
        }
        for(UserEntity user : foundDepartment.get().getEmployeeList()) {
            user.setDepartment(null);
        }
        foundDepartment.get().setEmployeeList(null);
        for(ItemEntity item : foundDepartment.get().getItemList()) {
            item.setDepartment(null);
        }

        foundDepartment.get().setItemList(null);
        departmentRepository.delete(foundDepartment.get());

    }

    public Optional<UserEntity> findUserById(Long id) {
        return Optional.ofNullable(userRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }


    public Optional<UserEntity> linkEmployeeToDepartment(Long departmentId, Long userId) {

        Optional<DepartmentEntity> foundDepartment = findDepartmentById(departmentId);
        Optional<UserEntity> foundUser = findUserById(userId);
        if (foundUser.get().getRoles().contains("EMPLOYEE")) {
            foundDepartment.get().addEmployee(foundUser.get());
            departmentRepository.save(foundDepartment.get());
        }
        return foundUser;
    }

    public Optional<UserEntity> unlinkEmployeeToDepartment(Long departmentId, Long userId) {
        Optional<DepartmentEntity> foundDepartment = findDepartmentById(departmentId);
        Optional<UserEntity> foundUser = findUserById(userId);
        foundDepartment.get().removeEmployee(foundUser.get());
        departmentRepository.save(foundDepartment.get());
        return foundUser;
    }

    private void setFields(String departmentName, Optional<DepartmentEntity> foundDepartment) {
        if (!(departmentName == null)) {
            foundDepartment.get().setDepartmentName(departmentName);
        }
    }

    public Optional<DepartmentEntity> linkItemToDepartment(Long departmentId, Long itemId) {
        Optional<DepartmentEntity> foundDepartment = findDepartmentById(departmentId);
        Optional<ItemEntity> foundItem = itemRepository.findById(itemId);

        foundDepartment.get().addItem(foundItem.get());
        departmentRepository.save(foundDepartment.get());
        return foundDepartment;
    }

    public Optional<DepartmentEntity> unLinkItemFromDepartment(Long departmentId, Long itemId) {
        Optional<DepartmentEntity> foundDepartment = findDepartmentById(departmentId);
        Optional<ItemEntity> foundItem = itemRepository.findById(itemId);

        foundDepartment.get().removeItem(foundItem.get());
        departmentRepository.save(foundDepartment.get());
        return foundDepartment;
    }
}