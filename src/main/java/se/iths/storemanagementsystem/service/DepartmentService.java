package se.iths.storemanagementsystem.service;

import org.springframework.stereotype.Service;
import se.iths.storemanagementsystem.entity.DepartmentEntity;
import se.iths.storemanagementsystem.entity.ItemEntity;
import se.iths.storemanagementsystem.entity.StoreEntity;
import se.iths.storemanagementsystem.entity.UserEntity;
import se.iths.storemanagementsystem.exceptions.customexceptions.AlreadyLinkedException;
import se.iths.storemanagementsystem.exceptions.customexceptions.NotFoundException;
import se.iths.storemanagementsystem.repository.DepartmentRepository;
import se.iths.storemanagementsystem.repository.ItemRepository;
import se.iths.storemanagementsystem.repository.UserRepository;

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

    public List<DepartmentEntity> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public Optional<DepartmentEntity> findDepartmentById(Long id) {
        if (departmentRepository.findById(id).isPresent()) {
            return departmentRepository.findById(id);
        } else throw new NotFoundException("Could not find department with id " + id);
    }

    public Optional<UserEntity> findUserById(Long id) {
        if (userRepository.findById(id).isPresent()) {
            return userRepository.findById(id);
        } else throw new NotFoundException("Could not find user with id " + id);
    }

    public Optional<ItemEntity> findItemById(Long id) {
        if (itemRepository.findById(id).isPresent()) {
            return itemRepository.findById(id);
        } else throw new NotFoundException("Could not find item with id " + id);

    }

    public Optional<DepartmentEntity> updateDepartment(Long id, String departmentName) {
        Optional<DepartmentEntity> foundDepartment = findDepartmentById(id);

        setFields(departmentName, foundDepartment);

        departmentRepository.save(foundDepartment.get());
        return foundDepartment;
    }

    public Optional<UserEntity> linkEmployeeToDepartment(Long departmentId, Long userId) {

        Optional<DepartmentEntity> foundDepartment = findDepartmentById(departmentId);
        Optional<UserEntity> foundUser = findUserById(userId);

        if (foundUser.get().getRoles().stream().anyMatch(role -> role.getName().equals("ROLE_EMPLOYEE")) && !foundDepartment.get().getEmployeeList().contains(foundUser.get())) {
            foundDepartment.get().addEmployee(foundUser.get());
            departmentRepository.save(foundDepartment.get());
        } else throw new AlreadyLinkedException("The entity that you are trying to link is already linked.");
        return foundUser;
    }

    public Optional<UserEntity> unlinkEmployeeToDepartment(Long departmentId, Long userId) {
        Optional<DepartmentEntity> foundDepartment = findDepartmentById(departmentId);
        Optional<UserEntity> foundUser = findUserById(userId);
        foundDepartment.get().removeEmployee(foundUser.get());
        departmentRepository.save(foundDepartment.get());
        return foundUser;
    }

    public Optional<DepartmentEntity> linkItemToDepartment(Long departmentId, Long itemId) {
        Optional<DepartmentEntity> foundDepartment = findDepartmentById(departmentId);
        Optional<ItemEntity> foundItem = findItemById(itemId);

        if (!foundDepartment.get().getItemList().contains(foundItem.get())) {
            foundDepartment.get().addItem(foundItem.get());
            departmentRepository.save(foundDepartment.get());
        } else throw new AlreadyLinkedException("The entity that you are trying to link is already linked.");

        return foundDepartment;
    }

    public Optional<DepartmentEntity> unLinkItemFromDepartment(Long departmentId, Long itemId) {
        Optional<DepartmentEntity> foundDepartment = findDepartmentById(departmentId);
        Optional<ItemEntity> foundItem = findItemById(itemId);

        foundDepartment.get().removeItem(foundItem.get());
        departmentRepository.save(foundDepartment.get());
        return foundDepartment;
    }

    public void deleteDepartment(Long id) {
        Optional<DepartmentEntity> foundDepartment = findDepartmentById(id);
        StoreEntity store = foundDepartment.get().getStore();
        if (store != null) {
            store.removeDepartment(foundDepartment.get());
        }
        for (UserEntity user : foundDepartment.get().getEmployeeList()) {
            user.setDepartment(null);
        }
        foundDepartment.get().setEmployeeList(null);
        for (ItemEntity item : foundDepartment.get().getItemList()) {
            item.setDepartment(null);
        }

        foundDepartment.get().setItemList(null);
        departmentRepository.delete(foundDepartment.get());

    }


    private void setFields(String departmentName, Optional<DepartmentEntity> foundDepartment) {
        if (!(departmentName == null)) {
            foundDepartment.get().setDepartmentName(departmentName);
        }
    }
}