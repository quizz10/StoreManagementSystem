package se.iths.storemanagementsystem.service;

import org.springframework.stereotype.Service;
import se.iths.storemanagementsystem.entity.ItemEntity;
import se.iths.storemanagementsystem.entity.ShoppingCartEntity;
import se.iths.storemanagementsystem.exceptions.customexceptions.NotFoundException;
import se.iths.storemanagementsystem.repository.ItemRepository;
import se.iths.storemanagementsystem.repository.ShoppingCartRepository;

import java.util.Optional;

@Service
public class ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;
    private final ItemRepository itemRepository;


    public ShoppingCartService(ShoppingCartRepository shoppingCartRepository, ItemRepository itemRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.itemRepository = itemRepository;
    }

    public Optional<ShoppingCartEntity> findShoppingCartById(Long id) {
        if (shoppingCartRepository.findById(id).isPresent()) {
            return shoppingCartRepository.findById(id);
        } else throw new NotFoundException("Could not find shopping cart with id " + id);
    }

    public Optional<ItemEntity> findItemById(Long id) {
        if (itemRepository.findById(id).isPresent()) {
            return itemRepository.findById(id);
        } else throw new NotFoundException("Could not find item with id " + id);

    }

    public Optional<ShoppingCartEntity> linkItemToShoppingCart(Long cartId, Long itemId) {
        Optional<ShoppingCartEntity> foundCart = findShoppingCartById(cartId);
        Optional<ItemEntity> foundItem = findItemById(itemId);

        foundCart.get().addItem(foundItem.get());
        shoppingCartRepository.save(foundCart.get());
        return foundCart;

    }

    public Optional<ShoppingCartEntity> unlinkItemFromShoppingCart(Long cartId, Long itemId) {
        Optional<ShoppingCartEntity> foundCart = findShoppingCartById(cartId);
        Optional<ItemEntity> foundItem = findItemById(itemId);

        foundCart.get().removeItem(foundItem);
        shoppingCartRepository.save(foundCart.get());
        return foundCart;

    }
}
