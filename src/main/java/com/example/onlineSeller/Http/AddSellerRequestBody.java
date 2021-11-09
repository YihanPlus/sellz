package com.example.onlineSeller.Http;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class AddSellerRequestBody {
    private String sku;
    private String name;
    private double price;
    private int inventoryAmount;
}
