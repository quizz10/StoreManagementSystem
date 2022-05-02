package se.iths.storemanagementsystem.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.iths.storemanagementsystem.entity.Item;
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
    public ResponseEntity<Item> createItem(@RequestBody Item item) {
        itemService.addItem(item);
        return new ResponseEntity<>(item, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<Item>> getItemById(@PathVariable("id") Long id) {
      //  notFoundError(id); // byta till spring-kompatibel
        Optional<Item> foundItem = itemService.findItemById(id);
        return new ResponseEntity<>(foundItem, HttpStatus.OK);
    }

    /*
      @GetMapping
      public ResponseEntity<Iterable<Item>> getAllItems() {
          Iterable<Item> foundItems = itemService.getAllItems();
          if (foundItems.isEmpty()) {
              return Response.status(Response.Status.NO_CONTENT).entity(new JsonFormatter(204, "There are no items added yet.")).build();
          }
          return Response.ok(foundItems).build();
      }
  */
    @PatchMapping
    public ResponseEntity<Optional<Item>> updateItem(@PathVariable("id") Long id, @RequestBody Optional<Item> item) {
        // notFoundError(id);
        item = itemService.updateItem(id, item);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Optional<Item>> deleteItem(@PathVariable("id") Long id) {
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
