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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String licenceNumber;


    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id",nullable = true)
    private List< @Valid Product>product;

    private boolean verification;
    @OneToOne
    @JoinColumn(nullable = true)
    private Role role;
    //added

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn
    @Valid
    private Credential credential;

    @OneToOne
    private Address address;





}
