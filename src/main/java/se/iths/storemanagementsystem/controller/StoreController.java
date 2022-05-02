package se.iths.storemanagementsystem.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.iths.storemanagementsystem.entity.Department;
import se.iths.storemanagementsystem.entity.Store;
import se.iths.storemanagementsystem.service.StoreService;

import java.util.Optional;

@RestController
@RequestMapping("store")
public class StoreController {

    StoreService storeService;

    public StoreController(StoreService storeService){
        this.storeService = storeService;
    }


    @PostMapping
    public ResponseEntity<Store> createStore(@RequestBody Store store) {
            storeService.addStore(store);
        return new ResponseEntity<>(store, HttpStatus.CREATED);

    }


    @GetMapping("{id}")
    public ResponseEntity<Optional<Store>> getStoreById(@PathVariable("id") Long id) {
        notFoundError(id);
        Optional<Store> store = storeService.findStoreById(id);
        return new ResponseEntity<>(store, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Iterable<Store>> getAllStores(){
        Iterable<Store> foundStores = storeService.getAllStores();
        if(foundStores == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(foundStores, HttpStatus.OK);
    }


    @PatchMapping("{id}")
    public ResponseEntity<Store> updateStore(@PathVariable("id") Long id, @RequestBody Store store){
            notFoundError(id);
           Store foundStore = storeService.updateStore(id, store);
        return new ResponseEntity<>(foundStore, HttpStatus.OK);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Optional<Store>> deleteStore(@PathVariable("id") Long id){
            storeService.deleteStore(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PatchMapping("link")
    public ResponseEntity<Optional<Store>> linkStoreToDepartment(@RequestParam("storeId") Long storeId, @RequestParam("departmentId") Long departmentId){
        Optional<Store> foundStore = storeService.findStoreById(storeId);
        Optional<Department> foundDepartment = storeService.findDepartmentById(departmentId);

            storeService.linkDepartment(storeId, departmentId);

        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PatchMapping("unlink")
    public ResponseEntity<Optional<Store>> unlinkStoreFromDepartment(@RequestParam("storeId") Long storeId, @RequestParam("departmentId") Long departmentId){
        Optional<Store> foundStore = storeService.findStoreById(storeId);
        Optional<Department> foundDepartment = storeService.findDepartmentById(departmentId);
            storeService.unlinkDepartment(storeId, departmentId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<Object> notFoundError(Long id) {
        if (storeService.findStoreById(id).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return null;
    }
}
