package se.iths.storemanagementsystem.service;

import org.springframework.stereotype.Service;
import se.iths.storemanagementsystem.entity.DepartmentEntity;
import se.iths.storemanagementsystem.entity.StoreEntity;
import se.iths.storemanagementsystem.repository.DepartmentRepository;
import se.iths.storemanagementsystem.repository.StoreRepository;

import javax.persistence.EntityNotFoundException;
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

    public void addStore(StoreEntity store) {
        storeRepository.save(store);
    }

    public Optional<StoreEntity> findStoreById(Long id) {
        return Optional.ofNullable(storeRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    public Optional<DepartmentEntity> findDepartmentById(Long id) {
        return Optional.ofNullable(departmentRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    public List<StoreEntity> getAllStores() {
        List<StoreEntity> stores = storeRepository.findAll();
        return stores;
    }

    public StoreEntity updateStore(Long id, StoreEntity store) {
        Optional<StoreEntity> foundStore = storeRepository.findById(id);
        if (store.getStoreName() != null) {
            foundStore.get().setStoreName(store.getStoreName());
            storeRepository.save(foundStore.get());
        }
        return foundStore.get();
    }

    public void deleteStore(Long id) {
        Optional<StoreEntity> foundStore = storeRepository.findById(id);
        storeRepository.delete(foundStore.get());
        //TODO fix this!!!!!!!!!!!
    }


    public Optional<StoreEntity> linkDepartmentToStore(Long storeId, Long departmentId) {
        Optional<StoreEntity> foundStore = storeRepository.findById(storeId);
        Optional<DepartmentEntity> foundDepartment = departmentRepository.findById(departmentId);

        foundDepartment.get().setStore(foundStore.get());
        foundStore.get().getDepartmentList().add(foundDepartment.get());
        storeRepository.save(foundStore.get());
        return foundStore;
    }


    public Optional<StoreEntity> unlinkDepartmentFromStore(Long storeId, Long departmentId) {
        Optional<StoreEntity> foundStore = storeRepository.findById(storeId);
        Optional<DepartmentEntity> foundDepartment = departmentRepository.findById(departmentId);

        foundStore.get().getDepartmentList().remove(foundDepartment.get());
        foundDepartment.get().setStore(null);
        storeRepository.save(foundStore.get());
        return foundStore;
    }
}
