package se.iths.storemanagementsystem.service;

import org.springframework.stereotype.Service;
import se.iths.storemanagementsystem.entity.DepartmentEntity;
import se.iths.storemanagementsystem.entity.StoreEntity;
import se.iths.storemanagementsystem.exceptions.customexceptions.NotFoundException;
import se.iths.storemanagementsystem.repository.DepartmentRepository;
import se.iths.storemanagementsystem.repository.StoreRepository;

import java.util.List;
import java.util.Optional;

@Service
public class StoreService {

    private final StoreRepository storeRepository;
    private final DepartmentRepository departmentRepository;

    public StoreService(StoreRepository storeRepository, DepartmentRepository departmentRepository) {
        this.storeRepository = storeRepository;
        this.departmentRepository = departmentRepository;
    }

    public StoreEntity addStore(StoreEntity store) {
        return storeRepository.save(store);
    }

    public Optional<StoreEntity> findStoreById(Long id) {
        if (storeRepository.findById(id).isPresent()) {
            return storeRepository.findById(id);
        } else throw new NotFoundException("Could not find store with id " + id);
    }

    public Optional<DepartmentEntity> findDepartmentById(Long id) {
        if (departmentRepository.findById(id).isPresent()) {
            return departmentRepository.findById(id);
        } else throw new NotFoundException("Could not find department with id " + id);
    }

    public List<StoreEntity> getAllStores() {
        List<StoreEntity> stores = storeRepository.findAll();
        return stores;
    }

    public StoreEntity updateStore(Long id, StoreEntity store) {
        Optional<StoreEntity> foundStore = findStoreById(id);
        if (store.getStoreName() != null) {
            foundStore.get().setStoreName(store.getStoreName());
            storeRepository.save(foundStore.get());
        }
        return foundStore.get();
    }

    public void deleteStore(Long id) {
        Optional<StoreEntity> foundStore = findStoreById(id);

        List<DepartmentEntity> departments = foundStore.get().getDepartmentList();
        if (departments != null) {
            for (DepartmentEntity department : departments) {
                department.setStore(null);
            }
            foundStore.get().setDepartmentList(null);
        }
        storeRepository.delete(foundStore.get());
    }


    public Optional<StoreEntity> linkDepartmentToStore(Long storeId, Long departmentId) {
        Optional<StoreEntity> foundStore = findStoreById(storeId);
        Optional<DepartmentEntity> foundDepartment = findDepartmentById(departmentId);

        foundDepartment.get().setStore(foundStore.get());
        foundStore.get().getDepartmentList().add(foundDepartment.get());
        storeRepository.save(foundStore.get());
        return foundStore;
    }


    public Optional<StoreEntity> unlinkDepartmentFromStore(Long storeId, Long departmentId) {
        Optional<StoreEntity> foundStore = storeRepository.findById(storeId);
        Optional<DepartmentEntity> foundDepartment = findDepartmentById(departmentId);

        foundStore.get().getDepartmentList().remove(foundDepartment.get());
        foundDepartment.get().setStore(null);
        storeRepository.save(foundStore.get());
        return foundStore;
    }
}
