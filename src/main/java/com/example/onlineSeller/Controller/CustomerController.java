package com.example.onlineSeller.Controller;

import com.example.onlineSeller.Entity.Customer;
import com.example.onlineSeller.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/signup")
    public void signUp(@RequestBody Customer customer) {
        System.out.println("Customer creating");
        customerService.signUp(customer);
    }

    @PostMapping("/signin")
    public void signIn(@RequestParam("email") String email,
                                           @RequestParam("password") String password, HttpServletRequest request, HttpServletResponse response) {
//        System.out.println(email + "  " + password);
        Customer c = customerService.validate(email, password);
        if (c != null) {
            HttpSession session = request.getSession();
            session.setAttribute("email", email);
            response.setStatus(HttpStatus.OK.value());
//            response.setMessage("Successfully signed in!");
        } else {
//            System.out.println("invalid");
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
//            response.setMessage("Cannot signed in!");
        }
    }
}
