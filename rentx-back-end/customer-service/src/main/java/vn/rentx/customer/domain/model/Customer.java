package vn.rentx.customer.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;
import java.util.UUID;

public class Customer {

    private UUID id;

    private String username;

    @JsonProperty("first_name")
    public String firstName;

    @JsonProperty("last_name")
    public String lastName;

    @JsonProperty("phone_number")
    public String phoneNumber;

    @JsonProperty("date_of_birth")
    public LocalDate dateOfBirth;

    @JsonProperty("gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @JsonProperty("address")
    public String address;

    @JsonProperty("registration_date")
    public LocalDate registrationDate;

    @JsonProperty("is_verified")
    private Byte isVerified;

}
