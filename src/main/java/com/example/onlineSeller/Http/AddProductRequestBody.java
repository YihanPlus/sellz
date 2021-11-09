package com.example.onlineSeller.Http;

import com.example.onlineSeller.Entity.Customer;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddProductRequestBody {
    private String sku;
    private String name;

    private String userEmail;
}
