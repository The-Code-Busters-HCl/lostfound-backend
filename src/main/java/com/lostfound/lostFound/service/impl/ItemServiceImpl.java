package com.lostfound.lostFound.service.impl;

import com.lostfound.lostFound.model.Item;
import com.lostfound.lostFound.repository.ItemRepo;
import com.lostfound.lostFound.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepo itemRepository;

    @Override
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    @Override
    public Item getItemById(Long id) {
        return itemRepository.findById(id).orElseThrow();
    }

    @Override
    public Item createItem(Item item) {
        return itemRepository.save(item);
    }

    @Override
    public Item updateItem(Long id, Item updatedItem) {
        Item item = getItemById(id);

        item.setItemName(updatedItem.getItemName());
        item.setItemLocation(updatedItem.getItemLocation());
        item.setStatus(updatedItem.getStatus());

        return itemRepository.save(item);
    }

    @Override
    public void deleteItem(Long id) {
        itemRepository.deleteById(id);
    }
}