package com.example.BookStoreApi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        Customer savedCustomer = customerService.saveCustomer(customer);
        return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerCustomer(@RequestParam String name,
                                                   @RequestParam String email,
                                                   @RequestParam String phoneNumber) {
        // Create a new Customer object
        Customer customer = new Customer(null, name, email, phoneNumber);

        // Save customer
        customerService.saveCustomer(customer);

        return new ResponseEntity<>("Customer registered successfully!", HttpStatus.CREATED);
    }
}
