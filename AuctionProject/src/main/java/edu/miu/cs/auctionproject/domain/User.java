package edu.miu.cs.auctionproject.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String licenceNumber;
    @OneToMany
    private List<Product> product;
    private boolean verification;
    @OneToOne
    @JoinColumn
    private Role role;





}
