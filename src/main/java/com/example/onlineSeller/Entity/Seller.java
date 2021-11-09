package com.example.onlineSeller.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name="seller")
public class Seller implements Serializable {
    private static final long serialVersionUID = 7551999649936522523L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private double price;
    private int inventoryAmount;

    public Seller(String name, double price, int inventoryAmount) {
        this.name = name;
        this.price = price;
        this.inventoryAmount = inventoryAmount;
    }

    @ManyToOne
    @JsonIgnore
    private Product product;
}
