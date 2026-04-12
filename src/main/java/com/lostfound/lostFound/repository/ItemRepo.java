package com.lostfound.lostFound.repository;

import java.util.List;

import com.lostfound.lostFound.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepo extends JpaRepository<Item, Long> {
    List<Item> findByStatusFalse();

    // Get all found items (status = true)
    List<Item> findByStatusTrue();

    List<Item> findByStatusTrueAndReturnedDateIsNotNull();

    List<Item> findBySeekerUserId(Long seekerId);

    List<Item> findByOwnerUserId(Long ownerId);

    List<Item> findByItemNameContaining(String keyword);

    List<Item> findByItemLocationContaining(String location);
}
