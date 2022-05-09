package se.iths.storemanagementsystem.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.iths.storemanagementsystem.dto.StoreDto;
import se.iths.storemanagementsystem.entity.StoreEntity;
import se.iths.storemanagementsystem.service.StoreService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("admin")
public class StoreController {

    @Autowired
    private ModelMapper modelMapper;

    StoreService storeService;

    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }


    @PostMapping("store")
    public ResponseEntity<StoreDto> createStore(@RequestBody StoreEntity store) {
        StoreEntity savedStore = storeService.addStore(store);
        return new ResponseEntity<>(modelMapper.map(savedStore, StoreDto.class), HttpStatus.CREATED);

    }


    @GetMapping("store/{id}")
    public ResponseEntity<StoreDto> getStoreById(@PathVariable("id") Long id) {
        Optional<StoreEntity> store = storeService.findStoreById(id);
        return new ResponseEntity<>(modelMapper.map(store, StoreDto.class), HttpStatus.OK);
    }

    @GetMapping("store")
    public ResponseEntity<List<StoreDto>> getAllStores() {
        List<StoreDto> foundStores = storeService.getAllStores()
                .stream().map(store -> modelMapper.map(store, StoreDto.class)).collect(Collectors.toList());
        if (foundStores.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(foundStores, HttpStatus.OK);
    }


    @PatchMapping("store/{id}")
    public ResponseEntity<StoreDto> updateStore(@PathVariable("id") Long id, @RequestBody StoreEntity store) {
        StoreEntity updatedStore = storeService.updateStore(id, store);
        return new ResponseEntity<>(modelMapper.map(updatedStore, StoreDto.class), HttpStatus.OK);
    }


    @DeleteMapping("store/{id}")
    public ResponseEntity<Void> deleteStore(@PathVariable("id") Long id) {
        storeService.deleteStore(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PatchMapping("store/link/{storeId}/{departmentId}")
    public ResponseEntity<StoreDto> linkDepartmentToStore(@PathVariable("storeId") Long storeId, @PathVariable("departmentId") Long departmentId) {

        Optional<StoreEntity> store = storeService.linkDepartmentToStore(storeId, departmentId);

        return new ResponseEntity<>(modelMapper.map(store, StoreDto.class), HttpStatus.OK);
    }


    @PatchMapping("store/unlink/{storeId}/{departmentId}")
    public ResponseEntity<StoreDto> unlinkDepartmentFromStore(@PathVariable("storeId") Long storeId, @PathVariable("departmentId") Long departmentId) {

        Optional<StoreEntity> store = storeService.unlinkDepartmentFromStore(storeId, departmentId);

        return new ResponseEntity<>(modelMapper.map(store, StoreDto.class), HttpStatus.OK);
    }
}
