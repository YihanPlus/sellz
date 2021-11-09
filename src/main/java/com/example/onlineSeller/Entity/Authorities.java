package com.example.onlineSeller.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name="authorities")
public class Authorities implements Serializable {
    private static final long serialVersionUID = 8734140534986494039L;

    @Id
    private String email;

    private String authorities;
}