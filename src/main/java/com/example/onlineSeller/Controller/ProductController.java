package com.example.onlineSeller.Controller;

import com.example.onlineSeller.Entity.Customer;
import com.example.onlineSeller.Entity.Product;
import com.example.onlineSeller.Http.AddProductRequestBody;
import com.example.onlineSeller.Http.UpdateProductRequestBody;
import com.example.onlineSeller.Service.CustomerService;
import com.example.onlineSeller.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private CustomerService customerService;

    @GetMapping("/products/{userEmail}/product")
    public List<Product> getAllProducts(@PathVariable(value="userEmail") String userEmail) {
        // System.out.println("email from controller ->" + userEmail);
        return productService.getAllProducts(userEmail);
    }

    @PostMapping("/create")
    public void createNewSKU(@RequestBody AddProductRequestBody addProductRequestBody) {
        // System.out.println("product data received by controller -> " + addProductRequestBody.toString());
        Customer customer = customerService.getCustomer(addProductRequestBody.getUserEmail());
        Product product = new Product(addProductRequestBody.getSku(), addProductRequestBody.getName());
        product.setCustomer(customer);
        productService.createProduct(product);
    }

    @PostMapping("/update")
    public void updateSKU(@RequestBody UpdateProductRequestBody updateProductRequestBody) {
        Product product = productService.getProduct(updateProductRequestBody.getSku());
        product.setName(updateProductRequestBody.getName());
//        System.out.println("aaaaa -> " + product);
        productService.updateProduct(product, updateProductRequestBody.getUserEmail());
    }

    @DeleteMapping("/delete")
    public void deleteSKU(@RequestBody Product product) {
        System.out.println(product.getSKU());
        productService.deleteProduct(product);
    }
}
