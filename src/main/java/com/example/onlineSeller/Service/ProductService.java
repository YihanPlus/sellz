package com.example.onlineSeller.Service;

import com.example.onlineSeller.Dao.CustomerDao;
import com.example.onlineSeller.Dao.ProductDao;
import com.example.onlineSeller.Dao.SellerDao;
import com.example.onlineSeller.Entity.Customer;
import com.example.onlineSeller.Entity.Product;
import com.example.onlineSeller.Entity.Seller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductDao productDao;
    @Autowired
    private SellerDao sellerDao;
    @Autowired
    private CustomerDao customerDao;

    public void createProduct(Product product) {
        Date date = new Date();
        product.setCreateDate(date);

        productDao.createProduct(product);
    }

    public void updateProduct(Product newProduct, String email) {
        String sku = newProduct.getSKU();

        Product oldProduct = productDao.getProduct(sku);
        Customer customer = customerDao.getCustomer(email);
        // reset if null
        newProduct.setCustomer(customer);
        newProduct.setCreateDate(oldProduct.getCreateDate());

        Double newLowestPrice = Double.MAX_VALUE;
        List<Seller> sellerList = sellerDao.getAllSellers(newProduct.getSKU());
        if (sellerList != null && !sellerList.isEmpty()) {
            for (Seller seller : sellerList) {
                newLowestPrice = Math.min(newLowestPrice, seller.getPrice());
            }
            newProduct.setLowestPrice(newLowestPrice);
        }
        productDao.updateProduct(newProduct);
    }

    public void deleteProduct(Product product) {
//        List<Seller> sellerList = sellerDao.getAllSellers(product.getSKU());
//        System.out.println(sellerList.size());
//        if (sellerList != null && !sellerList.isEmpty()) {
//            for (Seller seller : sellerList) {
//                sellerDao.deleteSeller(seller);
//            }
//        }
        productDao.deleteProduct(product);
    }

    public List<Product> getAllProducts(String email) {
        List<Product> products = productDao.getAllProducts(email);
        List<Product> productList = new ArrayList<>();
        for (Product product : products) {
            List<Seller> sellerList = sellerDao.getAllSellers(product.getSKU());
            // TODO: sort seller list in increasing order by each seller's price
            product.setSellerList(sellerList);
            productList.add(product);
        }
        return productList;
    }

    public Product getProduct(String sku) {
        return productDao.getProduct(sku);
    }

}
