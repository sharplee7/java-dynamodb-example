package com.example.dynamodbdemo.controller;

import com.example.dynamodbdemo.domain.Customer;
import com.example.dynamodbdemo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/customer")
    public Customer saveCustomer(@RequestBody Customer customer) {
        return customerService.saveCustomer(customer);
    }

    @GetMapping("/customer/{id}")
    public Customer getCustomerById(@PathVariable("id") String customerId) {
        return customerService.getCustomerById(customerId);
    }

    @DeleteMapping("/customer/{id}")
    public String deleteCustomerById(@PathVariable("id") String customerId) {
        return  customerService.deleteCustomerById(customerId);
    }

    @PutMapping("/customer/{id}")
    public String updateCustomer(@RequestBody Customer customer) {
        return customerService.updateCustomer(customer);
    }
}