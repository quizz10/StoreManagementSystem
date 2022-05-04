package se.iths.storemanagementsystem.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.iths.storemanagementsystem.entity.ShoppingCartEntity;
import se.iths.storemanagementsystem.service.ShoppingCartService;

import java.util.Optional;

@RestController
public class ShoppingCartController {
    ShoppingCartService shoppingCartService;


    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @PostMapping("shoppingcart") // Todo: onödig?
    public ResponseEntity<Optional<ShoppingCartEntity>> createShoppingCart(@RequestBody ShoppingCartEntity shoppingCart) {
        Optional<ShoppingCartEntity> newShoppingCart;
            newShoppingCart = shoppingCartService.createShoppingCart(shoppingCart);
        return new ResponseEntity<>(newShoppingCart, HttpStatus.CREATED);
    }

    @GetMapping("{id}") //                      ToDo: onödig?
    public ResponseEntity<Optional<ShoppingCartEntity>> getShoppingCartById(@PathVariable Long id) {
        Optional<ShoppingCartEntity> foundCart = shoppingCartService.findShoppingCartById(id);

        return new ResponseEntity<>(foundCart, HttpStatus.FOUND);
    }

    @PatchMapping("user/shoppingcart/link/{cartId}/{itemId}")
    public ResponseEntity<Optional<ShoppingCartEntity>> linkItemToShoppingCart(@PathVariable("cartId") Long cartId, @PathVariable("itemId") Long itemId) {

            Optional<ShoppingCartEntity> cart = shoppingCartService.linkItemToShoppingCart(cartId, itemId);

            return new ResponseEntity<>(cart, HttpStatus.CREATED);
    }

    @PatchMapping("user/shoppingcart/unlink/{cartId}/{itemId}")
    public ResponseEntity<Optional<ShoppingCartEntity>> unlinkItemFromShoppingCart(@PathVariable("cartId") Long cartId, @PathVariable("itemId") Long itemId) {

            Optional<ShoppingCartEntity> cart = shoppingCartService.unlinkItemFromShoppingCart(cartId, itemId);

        return new ResponseEntity<>(cart, HttpStatus.OK);
    }
}
