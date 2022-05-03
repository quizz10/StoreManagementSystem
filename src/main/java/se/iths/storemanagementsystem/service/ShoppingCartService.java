package se.iths.storemanagementsystem.service;

import org.springframework.stereotype.Service;
import se.iths.storemanagementsystem.entity.ItemEntity;
import se.iths.storemanagementsystem.entity.ShoppingCartEntity;
import se.iths.storemanagementsystem.repository.ItemRepository;
import se.iths.storemanagementsystem.repository.ShoppingCartRepository;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;
    private final ItemRepository itemRepository;


    public ShoppingCartService(ShoppingCartRepository shoppingCartRepository, ItemRepository itemRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.itemRepository = itemRepository;
    }

    public Optional<ShoppingCartEntity> createShoppingCart(ShoppingCartEntity shoppingCart) {
        ShoppingCartEntity cart = shoppingCartRepository.save(shoppingCart);
        return Optional.ofNullable(cart);
    }

    public Optional<ShoppingCartEntity> findShoppingCartById(Long id) {
        return Optional.ofNullable(shoppingCartRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    public Optional<ItemEntity> findItemById(Long id) {
        return Optional.ofNullable(itemRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    public Optional<ShoppingCartEntity> linkItemToShoppingCart(Long cartId, Long itemId) {
        Optional<ShoppingCartEntity> foundCart = shoppingCartRepository.findById(cartId);
        Optional<ItemEntity> foundItem = itemRepository.findById(itemId);

            foundCart.get().addItem(Optional.of(foundItem.get()));
            shoppingCartRepository.save(foundCart.get());
            return foundCart;

    }

    public Optional<ShoppingCartEntity> unlinkItemFromShoppingCart(Long cartId, Long itemId) {
        Optional<ShoppingCartEntity> foundCart = shoppingCartRepository.findById(cartId);
        Optional<ItemEntity> foundItem = itemRepository.findById(itemId);

            foundCart.get().removeItem(foundItem);
            shoppingCartRepository.save(foundCart.get());
            return foundCart;

    }
}
