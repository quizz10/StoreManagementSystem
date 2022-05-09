package se.iths.storemanagementsystem.service;

import org.springframework.stereotype.Service;
import se.iths.storemanagementsystem.entity.ItemEntity;
import se.iths.storemanagementsystem.entity.UserEntity;
import se.iths.storemanagementsystem.repository.ItemRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

import se.iths.storemanagementsystem.exceptions.customexceptions.NotFoundException;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public void addItem(ItemEntity item) {
        itemRepository.save(item);
    }

    public Optional<ItemEntity> findItemById(Long id) {
        if (itemRepository.findById(id).isPresent()) {
            return itemRepository.findById(id);
        } else throw new NotFoundException("Could not find item with id " + id);

    }

    public Iterable<ItemEntity> getAllItems() {
        return itemRepository.findAll();
    }

    public List<ItemEntity> getAllItemsAsList() {
        return itemRepository.findAll();
    }


    public Optional<ItemEntity> updateItem(Long id, Optional<ItemEntity> item) {
        Optional<ItemEntity> foundItem = itemRepository.findById(id);


        if (foundItem.isPresent()) {
            setFields(item, foundItem);
        } else {
            throw new RuntimeException("Could not find");
        }//        if (foundItem.isPresent()){
//            foundItem.get().setName(item.get().getName());
//            foundItem.get().setPrice(item.get().getPrice());
//        } else {
//            // insert exception here
//        }
        itemRepository.save(foundItem.get());
        return foundItem;
    }

    private void setFields(Optional<ItemEntity> item, Optional<ItemEntity> foundItem) {
        if (!(item.get().getName() == null)) {
            foundItem.get().setName(item.get().getName());
        }
        if (!(item.get().getPrice() == 0)) {
            foundItem.get().setPrice(item.get().getPrice());
        }
    }

    public void deleteItem(Long id) {
        Optional<ItemEntity> foundItem = findItemById(id);

        itemRepository.delete(foundItem.get());
    }


}
