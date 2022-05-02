package se.iths.storemanagementsystem.service;

import org.springframework.stereotype.Service;
import se.iths.storemanagementsystem.entity.Item;
import se.iths.storemanagementsystem.entity.ShoppingCart;
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

    public Optional<ShoppingCart> createShoppingCart(ShoppingCart shoppingCart) {
        ShoppingCart cart = shoppingCartRepository.save(shoppingCart);
        return Optional.ofNullable(cart);
    }

    public void deleteShoppingCart(Long id) {
        Optional<ShoppingCart> foundCart = shoppingCartRepository.findById(id);
        for(Item item : foundCart.get().getItems()) {
            foundCart.get().removeItem(item);
        }
        foundCart.get().removeUser(foundCart.get().getUser());
    }

    public Optional<ShoppingCart> findShoppingCartById(Long id) {
        return Optional.ofNullable(shoppingCartRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    public Optional<Item> findItemById(Long id) {
        return Optional.ofNullable(itemRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    public Optional<ShoppingCart> linkItemToShoppingCart(Long cartId, Long itemId) {
        Optional<ShoppingCart> foundCart = shoppingCartRepository.findById(cartId);
        Optional<Item> foundItem = itemRepository.findById(itemId);

            foundCart.get().addItem(Optional.of(foundItem.get()));
            return foundCart;

    }

    public Optional<ShoppingCart> unlinkItemFromShoppingCart(Long cartId, Long itemId) {
        Optional<ShoppingCart> foundCart = shoppingCartRepository.findById(cartId);
        Optional<Item> foundItem = itemRepository.findById(itemId);

            foundCart.get().removeItem(foundItem.get());
            return foundCart;

    }
}
