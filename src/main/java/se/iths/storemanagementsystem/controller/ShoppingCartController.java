package se.iths.storemanagementsystem.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.iths.storemanagementsystem.entity.ShoppingCart;
import se.iths.storemanagementsystem.service.ShoppingCartService;

import java.util.Optional;

@RestController
@RequestMapping("shoppingcart")
public class ShoppingCartController {
    ShoppingCartService shoppingCartService;


    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @PostMapping("")
    public ResponseEntity<Optional<ShoppingCart>> createShoppingCart(@RequestBody ShoppingCart shoppingCart) {
        Optional<ShoppingCart> newShoppingCart;
            newShoppingCart = shoppingCartService.createShoppingCart(shoppingCart);
        return new ResponseEntity<>(newShoppingCart, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<ShoppingCart>> getShoppingCartById(@PathVariable Long id) {
        Optional<ShoppingCart> foundCart = shoppingCartService.findShoppingCartById(id);

        return new ResponseEntity<>(foundCart, HttpStatus.FOUND);
    }

    @PatchMapping("link/{cartId}/{itemId}")
    public ResponseEntity<Optional<ShoppingCart>> linkItemToShoppingCart(@PathVariable("cartId") Long cartId, @PathVariable("itemId") Long itemId) {

            Optional<ShoppingCart> cart = shoppingCartService.linkItemToShoppingCart(cartId, itemId);

            return new ResponseEntity<>(cart, HttpStatus.CREATED);
    }

    @PatchMapping("unlink/{cartId}/{itemId}")
    public ResponseEntity<Optional<ShoppingCart>> unlinkItemFromShoppingCart(@PathVariable("cartId") Long cartId, @PathVariable("itemId") Long itemId) {



            Optional<ShoppingCart> cart = shoppingCartService.unlinkItemFromShoppingCart(cartId, itemId);

        return new ResponseEntity<>(cart, HttpStatus.OK);
    }
}
