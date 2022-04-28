//package se.iths.storemanagementsystem.service;
//
//import org.springframework.stereotype.Service;
//import se.iths.storemanagementsystem.entity.Department;
//import se.iths.storemanagementsystem.entity.Store;
//import se.iths.storemanagementsystem.repository.DepartmentRepository;
//import se.iths.storemanagementsystem.repository.StoreRepository;
//
//import javax.persistence.EntityNotFoundException;
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class StoreService {
//
//    private final StoreRepository storeRepository;
//    private final DepartmentRepository departmentRepository;
//
//    public StoreService(StoreRepository storeRepository, DepartmentRepository departmentRepository) {
//        this.storeRepository = storeRepository;
//        this.departmentRepository = departmentRepository;
//    }
//
//    public void addStore(Store store){.save(store);}
//
//    public Optional<Store> findStoreById(Long id) {
//        return Optional.ofNullable(storeRepository.findById(id).orElseThrow(EntityNotFoundException::new));
//    }
//
//    public Optional<Department> findDepartmentById(Long id) {
//        return Optional.ofNullable(departmentRepository.findById(id).orElseThrow(EntityNotFoundException::new));
//    }
//
//    public List<Store> getAllStores(){
//        return .createQuery("SELECT a from Store a", Store.class).getResultList();
//    }
//
//    public Store updateStore(Long id, Store store){
//        Store foundStore = .find(Store.class, id);
//        foundStore.setStoreName(store.getStoreName());
//        return foundStore;
//    }
//
//    public void deleteStore(Long id){
//        Store foundStore = .find(Store.class, id);
//        .remove(foundStore);
//    }
//
//
//    public void linkDepartment(Long storeId, Long departmentId) {
//        Store foundStore = .find(Store.class, storeId);
//        Department foundDepartment = .find(Department.class, departmentId);
//
//        foundDepartment.setStore(foundStore);
//        foundStore.getDepartmentList().add(foundDepartment);
//    }
//
//
//    public void unlinkDepartment(Long storeId, Long departmentId) {
//        Store foundStore = .find(Store.class, storeId);
//        Department foundDepartment = .find(Department.class, departmentId);
//
//        foundStore.getDepartmentList().remove(foundDepartment);
//        foundDepartment.setStore(null);
//    }
//}
