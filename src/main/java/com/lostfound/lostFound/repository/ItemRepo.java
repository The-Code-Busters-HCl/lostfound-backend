package com.lostfound.lostFound.repository;



import com.lostfound.lostFound.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepo extends JpaRepository<Item, Long> {
    List<Item> findByStatus(Boolean status);
}
