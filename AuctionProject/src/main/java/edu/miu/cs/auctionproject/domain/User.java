package edu.miu.cs.auctionproject.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Getter
@Setter
//@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "FirstName required!")
    private String firstName;
    @NotBlank(message = "LastName required!")
    private String lastName;
    @NotBlank(message = "Email required!")
    private String email;
    @NotBlank(message = "LicenceNumber required!")
    private String licenceNumber;


    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id",nullable = true)
    private List<Product>product;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Product>wonProducts;

    private boolean verification;
    @ManyToOne
//    @JoinColumn(nullable = true)
    private Role role;
    //added

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn
    @Valid
    private Credential credential;

    @OneToOne
    private Address address;

    //added
    private boolean pinCode;





}
