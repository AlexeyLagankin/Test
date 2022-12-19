package com.example.petstore.model;

public enum PetStatus {
    AVAILABLE("available"),
    PENDING("pending"),
    SOLD("sold");

    private String representation;
    PetStatus(String representation) {
        this.representation = representation;
    }

    @Override
    public String toString() {
        return representation;
    }
}
