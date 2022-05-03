package se.iths.storemanagementsystem.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.iths.storemanagementsystem.entity.ItemEntity;
import se.iths.storemanagementsystem.service.ItemService;

import java.util.Optional;


@RestController
@RequestMapping("item")
public class ItemController {

    ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping
    public ResponseEntity<ItemEntity> createItem(@RequestBody ItemEntity item) {
        itemService.addItem(item);
        return new ResponseEntity<>(item, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<ItemEntity>> getItemById(@PathVariable("id") Long id) {
      //  notFoundError(id); // byta till spring-kompatibel
        Optional<ItemEntity> foundItem = itemService.findItemById(id);
        return new ResponseEntity<>(foundItem, HttpStatus.OK);
    }


      @GetMapping
      public ResponseEntity<Iterable<ItemEntity>> getAllItems() {
          Iterable<ItemEntity> foundItems = itemService.getAllItems();
          if (foundItems == null) {
              return new ResponseEntity<>(HttpStatus.NO_CONTENT);
          }
          return new ResponseEntity<>(foundItems, HttpStatus.OK);
      }

    @PatchMapping("{id}")
    public ResponseEntity<Optional<ItemEntity>> updateItem(@PathVariable("id") Long id, @RequestBody Optional<ItemEntity> item) {
        // notFoundError(id);
        item = itemService.updateItem(id, item);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
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
