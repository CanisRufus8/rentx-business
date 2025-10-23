package vn.rentx.customer.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;
import vn.rentx.customer.domain.model.Gender;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CUSTOMER")
public class CustomerEntity {

    @Id
    @UuidGenerator
    @Column(name = "id", columnDefinition = "UUID")
    private UUID id;

    @Column(name = "username", columnDefinition = "VARCHAR(100)")
    private String username;

    @Column(name = "first_name", columnDefinition = "VARCHAR(50)")
    public String firstName;

    @Column(name = "last_name", columnDefinition = "VARCHAR(50)")
    public String lastName;

    @Column(name = "phone_number", columnDefinition = "VARCHAR(15)")
    public String phoneNumber;

    @Column(name = "date_of_birth")
    public LocalDate dateOfBirth;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "address")
    public String address;

    @Column(name = "registration_date")
    public LocalDate registrationDate;

    @Column(name = "is_verified")
    private Byte isVerified;

}
