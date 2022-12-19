package com.example.petstore.model;

public enum OrderStatus {
    PLACED("placed"),
    APPROVED("approved"),
    DELIVERED("delivered");

    private String representation;
    OrderStatus(String representation) {
        this.representation = representation;
    }

    @Override
    public String toString() {
        return representation;
    }
}
