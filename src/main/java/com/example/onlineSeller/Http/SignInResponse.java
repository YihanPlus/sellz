package com.example.onlineSeller.Http;

import com.example.onlineSeller.Entity.Customer;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
@ToString
public class SignInResponse {
    private String status;
    private String Message;
    private Customer customer;
}
