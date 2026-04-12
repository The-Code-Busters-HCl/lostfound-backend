package com.lostfound.lostFound.service.impl;

import com.lostfound.lostFound.model.Item;
import com.lostfound.lostFound.repository.ItemRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ItemServiceImpl {
    @Autowired
    private ItemRepo itemRepository;

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public Item getItemById(Long id) {
        return itemRepository.findById(id).orElseThrow();
    }

    public Item createItem(Item item) {
        return itemRepository.save(item);
    }

    public Item updateItem(Long id, Item updatedItem) {
        Item item = getItemById(id);
        item.setItemName(updatedItem.getItemName());

        item.setItemLocation(updatedItem.getItemLocation());
        item.setStatus(updatedItem.getStatus());
        return itemRepository.save(item);
    }

    public void deleteItem(Long id) {
        itemRepository.deleteById(id);
    }
}
