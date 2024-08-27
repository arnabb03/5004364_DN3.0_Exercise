package com.example.BookStoreApi.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CustomerDTO {
    private Long id;

    @JsonProperty("customer_name")
    private String name;

    @JsonProperty("customer_email")
    private String email;

    @JsonIgnore // Exclude from JSON serialization
    private String phoneNumber;

    // Constructors, getters, and setters
}
