package com.example.onlineSeller.Controller;

import com.example.onlineSeller.Entity.Customer;
import com.example.onlineSeller.Entity.Product;
import com.example.onlineSeller.Entity.Seller;
import com.example.onlineSeller.Http.AddSellerRequestBody;
import com.example.onlineSeller.Service.CustomerService;
import com.example.onlineSeller.Service.ProductService;
import com.example.onlineSeller.Service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class SellerController {
    @Autowired
    private SellerService sellerService;

    @Autowired
    private ProductService productService;

    @Autowired
    private CustomerService customerService;

    @PostMapping("/update/createSeller")
    public void createSeller(@RequestBody AddSellerRequestBody addSellerRequestBody) {
        Product product = productService.getProduct(addSellerRequestBody.getSku());
        Seller seller = new Seller(addSellerRequestBody.getName(), addSellerRequestBody.getPrice(),
                addSellerRequestBody.getInventoryAmount());
        seller.setProduct(product);
        sellerService.createSeller(seller);
        double currentLowestPrice = product.getLowestPrice();
        if ( currentLowestPrice == 0 || seller.getPrice() < currentLowestPrice) {
            product.setLowestPrice(currentLowestPrice);
            Customer customer = product.getCustomer();
            productService.updateProduct(product, customer.getEmail());
        }
    }

    @PostMapping("/update/{productId}/updateSeller")
    public void updateSeller(@RequestBody Seller seller, @PathVariable String productSKU) {
        System.out.println("Seller updating");
        sellerService.updateSeller(seller);
    }

    @DeleteMapping("/update/{productId}/deleteSeller")
    public void deleteSeller(@RequestBody Seller seller, @PathVariable String productSKU) {
        System.out.println("Seller deleting");
        sellerService.deleteSeller(seller);
    }


}
