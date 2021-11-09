package com.example.onlineSeller.Http;

import com.example.onlineSeller.Entity.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateProductRequestBody {
    private String sku;
    private String name;

    private String userEmail;
}
