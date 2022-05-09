package se.iths.storemanagementsystem.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import se.iths.storemanagementsystem.dto.ShoppingCartDto;
import se.iths.storemanagementsystem.entity.ShoppingCartEntity;
import se.iths.storemanagementsystem.service.ShoppingCartService;

import java.util.Optional;

@RestController
public class ShoppingCartController {

    @Autowired
    private ModelMapper modelMapper;

    ShoppingCartService shoppingCartService;

    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @GetMapping("user/shoppingcart/{id}")
    public ResponseEntity<ShoppingCartDto> getShoppingCartById(@PathVariable Long id) {
        Optional<ShoppingCartEntity> foundCart = shoppingCartService.findShoppingCartById(id);
        ShoppingCartDto shoppingCartDto = modelMapper.map(foundCart, ShoppingCartDto.class);
        return new ResponseEntity<>(shoppingCartDto, HttpStatus.FOUND);
    }

    @PatchMapping("user/shoppingcart/link/{cartId}/{itemId}")
    public ResponseEntity<ShoppingCartDto> linkItemToShoppingCart(@PathVariable("cartId") Long cartId, @PathVariable("itemId") Long itemId) {

        Optional<ShoppingCartEntity> cart = shoppingCartService.linkItemToShoppingCart(cartId, itemId);
        ShoppingCartDto shoppingCartDto = modelMapper.map(cart, ShoppingCartDto.class);
        return new ResponseEntity<>(shoppingCartDto, HttpStatus.CREATED);
    }

    @PatchMapping("user/shoppingcart/unlink/{cartId}/{itemId}")
    public ResponseEntity<ShoppingCartDto> unlinkItemFromShoppingCart(@PathVariable("cartId") Long cartId, @PathVariable("itemId") Long itemId) {

        Optional<ShoppingCartEntity> cart = shoppingCartService.unlinkItemFromShoppingCart(cartId, itemId);
        ShoppingCartDto shoppingCartDto = modelMapper.map(cart, ShoppingCartDto.class);

        return new ResponseEntity<>(shoppingCartDto, HttpStatus.OK);
    }
}
