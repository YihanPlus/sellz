package com.example.onlineSeller.Service;

import com.example.onlineSeller.Dao.CustomerDao;
import com.example.onlineSeller.Dao.SellerDao;
import com.example.onlineSeller.Entity.Product;
import com.example.onlineSeller.Entity.Seller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellerService {
    @Autowired
    private SellerDao sellerDao;

    public void createSeller(Seller seller) {
        sellerDao.createSeller(seller);
    }

    public void updateSeller(Seller seller) {
        sellerDao.updateSeller(seller);
    }
    public void deleteSeller(Seller seller) {
        sellerDao.deleteSeller(seller);
    }

    public List<Seller> getSellers(String SKU) {
        return sellerDao.getAllSellers(SKU);
    }
}
