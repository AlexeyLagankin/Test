package com.example.petstore.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "pet_id")
    private int petId;

    private int quantity;

    @Column(name = "ship_date")
    private LocalDateTime shipDate;

    private OrderStatus status;

    private boolean complete;
}
