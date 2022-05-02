package se.iths.storemanagementsystem.service;

import org.springframework.stereotype.Service;
import se.iths.storemanagementsystem.entity.Department;
import se.iths.storemanagementsystem.entity.Store;
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

    public void addStore(Store store){storeRepository.save(store);}

    public Optional<Store> findStoreById(Long id) {
        return Optional.ofNullable(storeRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    public Optional<Department> findDepartmentById(Long id) {
        return Optional.ofNullable(departmentRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    public List<Store> getAllStores(){
        List<Store> stores = storeRepository.findAll();
        return stores;
    }

    public Store updateStore(Long id, Store store){
        Optional<Store> foundStore = storeRepository.findById(id);
        foundStore.get().setStoreName(store.getStoreName());
        return foundStore.get();
    }

    public void deleteStore(Long id){
        Optional<Store> foundStore = storeRepository.findById(id);
        storeRepository.delete(foundStore.get());
    }


    public void linkDepartment(Long storeId, Long departmentId) {
        Optional<Store> foundStore = storeRepository.findById(storeId);
        Optional<Department> foundDepartment = departmentRepository.findById(departmentId);

        foundDepartment.get().setStore(foundStore.get());
        foundStore.get().getDepartmentList().add(foundDepartment.get());
    }


    public void unlinkDepartment(Long storeId, Long departmentId) {
        Optional<Store> foundStore = storeRepository.findById(storeId);
        Optional<Department> foundDepartment = departmentRepository.findById(departmentId);

        foundStore.get().getDepartmentList().remove(foundDepartment.get());
        foundDepartment.get().setStore(null);
    }
}
