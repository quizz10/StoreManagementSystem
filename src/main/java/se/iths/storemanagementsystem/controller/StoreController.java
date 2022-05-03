package se.iths.storemanagementsystem.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.iths.storemanagementsystem.entity.StoreEntity;
import se.iths.storemanagementsystem.service.StoreService;

import java.util.Optional;

@RestController
@RequestMapping("store")
public class StoreController {

    StoreService storeService;

    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }


    @PostMapping
    public ResponseEntity<StoreEntity> createStore(@RequestBody StoreEntity store) {
        storeService.addStore(store);
        return new ResponseEntity<>(store, HttpStatus.CREATED);

    }


    @GetMapping("{id}")
    public ResponseEntity<Optional<StoreEntity>> getStoreById(@PathVariable("id") Long id) {
//        notFoundError(id);
        Optional<StoreEntity> store = storeService.findStoreById(id);
        return new ResponseEntity<>(store, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Iterable<StoreEntity>> getAllStores() {
        Iterable<StoreEntity> foundStores = storeService.getAllStores();
        if (foundStores == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(foundStores, HttpStatus.OK);
    }


    @PatchMapping("{id}")
    public ResponseEntity<StoreEntity> updateStore(@PathVariable("id") Long id, @RequestBody StoreEntity store) {
//            notFoundError(id);
        StoreEntity foundStore = storeService.updateStore(id, store);
        return new ResponseEntity<>(foundStore, HttpStatus.OK);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Optional<StoreEntity>> deleteStore(@PathVariable("id") Long id) {
        storeService.deleteStore(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PatchMapping("link/{storeId}/{departmentId}")
    public ResponseEntity<Optional<StoreEntity>> linkStoreToDepartment(@PathVariable("storeId") Long storeId, @PathVariable("departmentId") Long departmentId) {

        Optional<StoreEntity> store = storeService.linkDepartment(storeId, departmentId);

        return new ResponseEntity<>(store, HttpStatus.OK);
    }


    @PatchMapping("unlink/{storeId}/{departmentId}")
    public ResponseEntity<Optional<StoreEntity>> unlinkStoreFromDepartment(@PathVariable("storeId") Long storeId, @PathVariable("departmentId") Long departmentId) {

        Optional<StoreEntity> store = storeService.unlinkDepartment(storeId, departmentId);

        return new ResponseEntity<>(store, HttpStatus.OK);
    }

//    public ResponseEntity<Object> notFoundError(Long id) {
//        if (storeService.findStoreById(id).isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        return null;
//    }
}
