package com.lostfound.lostFound.service.impl;

import com.lostfound.lostFound.model.Item;
import com.lostfound.lostFound.repository.UserRepo;
import com.lostfound.lostFound.model.User;
import com.lostfound.lostFound.repository.ItemRepo;
import com.lostfound.lostFound.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//authentication lib
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepo itemRepository;
    @Autowired
    private UserRepo userRepo;

    private User getLoggedInUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        return userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

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
        User user = getLoggedInUser();
        item.setOwner(user);
        return itemRepository.save(item);
    }

    @Override
    public Item updateItem(Long id, Item updatedItem) {
        Item item = getItemById(id);
        User user = getLoggedInUser();

        if (!item.getOwner().getUserId().equals(user.getUserId())) {
            throw new RuntimeException("Unauthorized");
        }

        item.setItemName(updatedItem.getItemName());
        item.setItemLocation(updatedItem.getItemLocation());
        item.setStatus(updatedItem.getStatus());

        return itemRepository.save(item);
    }

    @Override
    public void deleteItem(Long id) {
        Item item = getItemById(id);
        User user = getLoggedInUser();

        if (!item.getOwner().getUserId().equals(user.getUserId())) {
            throw new RuntimeException("Unauthorized");
        }

        itemRepository.deleteById(id);
    }
}
