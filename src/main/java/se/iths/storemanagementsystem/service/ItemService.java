package se.iths.storemanagementsystem.service;

import org.springframework.stereotype.Service;
import se.iths.storemanagementsystem.entity.Item;
import se.iths.storemanagementsystem.repository.ItemRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public void addItem(Item item) {
        itemRepository.save(item);
    }

    public Optional<Item> findItemById(Long id) {
        return Optional.ofNullable(itemRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

   /* public List<Item> getAllItems() {
        return itemRepository.createQuery("SELECT a from Item a", Item.class).getResultList();
    }*/

    public Optional<Item> updateItem(Long id, Optional<Item> item) {
        Optional<Item> foundItem = itemRepository.findById(id);

        if (foundItem.isPresent()){
            foundItem.get().setName(item.get().getName());
            foundItem.get().setPrice(item.get().getPrice());
        } else {
            // insert exception here
        }
        itemRepository.save(foundItem.get());
        return foundItem;
    }

    public void deleteItem(Long id) {
        Optional<Item> foundItem = findItemById(id);

        itemRepository.delete(foundItem.get());
    }


}
