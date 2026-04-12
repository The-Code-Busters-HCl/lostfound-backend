package com.lostfound.lostFound.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;

    private String itemName;
    private String itemLocation;

    private Boolean status;

    private LocalDateTime foundDate;
    private LocalDateTime returnedDate;

    @ManyToOne
    private User owner;

    @ManyToOne
    private User seeker;

    @PrePersist
    public void prePersist() {
        if (status == null) status = false;
        if (foundDate == null) foundDate = LocalDateTime.now();
    }
}
