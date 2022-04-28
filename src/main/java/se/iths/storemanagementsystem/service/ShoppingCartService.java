//package se.iths.storemanagementsystem.service;
//
//import org.springframework.stereotype.Service;
//import se.iths.storemanagementsystem.entity.Item;
//import se.iths.storemanagementsystem.entity.ShoppingCart;
//import se.iths.storemanagementsystem.repository.ItemRepository;
//import se.iths.storemanagementsystem.repository.ShoppingCartRepository;
//
//import javax.persistence.EntityNotFoundException;
//import java.util.Optional;
//
//@Service
//public class ShoppingCartService {
//
//    private final ShoppingCartRepository shoppingCartRepository;
//    private final ItemRepository itemRepository;
//
//    public ShoppingCartService(ShoppingCartRepository shoppingCartRepository, ItemRepository itemRepository) {
//        this.shoppingCartRepository = shoppingCartRepository;
//        this.itemRepository = itemRepository;
//    }
//
//    public void createShoppingCart(ShoppingCart shoppingCart) {
//        .save(shoppingCart);
//    }
//
//    public Optional<ShoppingCart> findShoppingCartById(Long id) {
//        return Optional.ofNullable(shoppingCartRepository.findById(id).orElseThrow(EntityNotFoundException::new));
//    }
//
//    public Optional<Item> findItemById(Long id) {
//        return Optional.ofNullable(itemRepository.findById(id).orElseThrow(EntityNotFoundException::new));
//    }
//
//    public void linkItemToShoppingCart(Long cartId, Long itemId) {
//        ShoppingCart foundCart = .find(ShoppingCart.class, cartId);
//        Item foundItem = .find(Item.class, itemId);
//
//        foundCart.addItem(foundItem);
//    }
//
//    public void unlinkItemFromShoppingCart(Long cartId, Long itemId) {
//        ShoppingCart foundCart = .find(ShoppingCart.class, cartId);
//        Item foundItem = .find(Item.class, itemId);
//
//        foundCart.removeItem(foundItem);
//    }
//}
