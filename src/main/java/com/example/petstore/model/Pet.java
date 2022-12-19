package com.example.petstore.model;


import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.List;

@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name="pet")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToOne
    private Category category;

    private String name;

    @OneToMany
    private List<Tag> tags;

    private PetStatus status;
}
