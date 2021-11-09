package com.example.onlineSeller.Service;

import com.example.onlineSeller.Dao.CustomerDao;
import com.example.onlineSeller.Entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CustomerService {
    @Autowired
    private CustomerDao customerDao;

    public void signUp(Customer customer) {
        customer.setEnabled(true);
        customerDao.signUp(customer);
    }

    public Customer validate(String email, String password) {
        return customerDao.validateCustomer(email, password);
    }

    public Customer getCustomer(String email) {
        return customerDao.getCustomer(email);
    }
}
