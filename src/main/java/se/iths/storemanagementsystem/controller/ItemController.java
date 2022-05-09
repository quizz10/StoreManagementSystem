package se.iths.storemanagementsystem.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.iths.storemanagementsystem.dto.ItemDto;
import se.iths.storemanagementsystem.entity.ItemEntity;
import se.iths.storemanagementsystem.service.ItemService;

import java.util.List;
import java.util.Optional;


@RestController
public class ItemController {

    @Autowired
    private ModelMapper modelMapper;

    ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping("employee/item")
    public ResponseEntity<ItemEntity> createItem(@RequestBody ItemEntity item) {
        itemService.addItem(item);
        return new ResponseEntity<>(item, HttpStatus.CREATED);
    }


    @GetMapping("general/item/{id}")
    public ResponseEntity<ItemDto> getItemById(@PathVariable("id") Long id) {
        Optional<ItemEntity> foundItem = itemService.findItemById(id);
        return ResponseEntity.ok().body(modelMapper.map(foundItem, ItemDto.class));
    }


    @GetMapping("general/item")
    public ResponseEntity<List<ItemDto>> getAllItems() {
        List<ItemDto> itemDtoList = itemService.getAllItemsAsList().stream()
                .map(item -> modelMapper.map(item, ItemDto.class)).toList();

        return new ResponseEntity<>(itemDtoList, HttpStatus.OK);
    }

    @PatchMapping("employee/item/{id}")
    public ResponseEntity<ItemDto> updateItem(@PathVariable("id") Long id, @RequestBody Optional<ItemEntity> item) {
        Optional<ItemEntity> updatedItem = itemService.updateItem(id, item);

        return new ResponseEntity<>(modelMapper.map(updatedItem, ItemDto.class), HttpStatus.OK);
    }

    @DeleteMapping("employee/item/{id}")
    public ResponseEntity<Optional<ItemEntity>> deleteItem(@PathVariable("id") Long id) {
        itemService.deleteItem(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
