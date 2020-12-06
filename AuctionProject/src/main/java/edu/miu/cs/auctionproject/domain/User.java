package edu.miu.cs.auctionproject.domain;

import javax.persistence.*;
import java.util.List;

@Entity
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





}
