package com.example.recipebook.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class ContactInformation {

    @Column(name = "phone_number", length = 30, unique = true)
    private String phoneNumber;

    @Column(name = "email_address", length = 100, unique = true, nullable = false)
    private String emailAddress;

    @Column(name = "street_address", length = 150)
    private String streetAddress;

    @Column(length = 50, nullable = false)
    private String city;

    @Column(length = 50, nullable = false)
    private String state;

    @Column(name = "zip_code", length = 20, nullable = false)
    private String zipCode;

    @Column(length = 50, nullable = false)
    private String country;
}
