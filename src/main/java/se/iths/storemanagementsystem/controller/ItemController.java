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


    //TODO: Fortsätt härifrån, vi ska göra om alla returns till DTOer med
    // hjälp av modelmapper och hjälpklasser som exempelvis ItemDto.
    @GetMapping("general/item/{id}")
    public ResponseEntity<ItemDto> getItemById(@PathVariable("id") Long id) {
        //  notFoundError(id); // byta till spring-kompatibel
//        Optional<ItemEntity> foundItem = itemService.findItemById(id);
//        return new ResponseEntity<>(foundItem, HttpStatus.OK);
        Optional<ItemEntity> foundItem = itemService.findItemById(id);
        return ResponseEntity.ok().body(modelMapper.map(foundItem, ItemDto.class));
    }


    @GetMapping("general/item")
    public ResponseEntity<List<ItemDto>> getAllItems() {
//          Iterable<ItemEntity> foundItems = itemService.getAllItems();
//          if (foundItems == null) {
//              return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//          }
//          return new ResponseEntity<>(foundItems, HttpStatus.OK);
        List<ItemDto> itemDtoList = itemService.getAllItemsAsList().stream()
                .map(item -> modelMapper.map(item, ItemDto.class)).toList();

        return new ResponseEntity<>(itemDtoList, HttpStatus.OK);
    }

    @PatchMapping("employee/item/{id}")
    public ResponseEntity<ItemDto> updateItem(@PathVariable("id") Long id, @RequestBody Optional<ItemEntity> item) {
        // notFoundError(id);
       Optional<ItemEntity> updatedItem = itemService.updateItem(id, item);

        return new ResponseEntity<>(modelMapper.map(updatedItem, ItemDto.class), HttpStatus.OK);
    }

    @DeleteMapping("employee/item/{id}")
    public ResponseEntity<Optional<ItemEntity>> deleteItem(@PathVariable("id") Long id) {
        itemService.deleteItem(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

/*
    public void notFoundError(Long id) {

        if (!itemService.findItemById(id).isPresent()) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND).entity(new JsonFormatter(Response.Status.NOT_FOUND.getStatusCode(), "There is no item with the id: " + id)).build());
        }
    }

 */
}
