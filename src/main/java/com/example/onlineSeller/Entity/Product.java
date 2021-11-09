package com.example.onlineSeller.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name="product")
public class Product implements Serializable {
    private static final long serialVersionUID = 2455760938054036111L;

    @Id
    private String SKU;

    private String name;
    private Date createDate;

    private double lowestPrice;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Seller> sellerList;

    @ManyToOne
    private Customer customer;

    public Product(String SKU, String name) {
        this.SKU = SKU;
        this.name = name;
    }

}
