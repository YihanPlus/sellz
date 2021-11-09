package com.example.onlineSeller.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name="customer")
public class Customer implements Serializable {
    private static final long serialVersionUID = 2652327633296064143L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    private String email;
    private String password;
    private boolean enabled;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Product> products;

    public Customer(String email, String password) {
        this.email = email;
        this.password = password;
        this.enabled = true;
    }

    public boolean getEnabled() {
        return this.enabled;
    }
}
