package edu.miu.cs.auctionproject.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String licenceNumber;
    @OneToMany
    @JoinColumn(name = "user_id",nullable = true)
    private List<Product> product;
    private boolean verification;
    @OneToOne
    @JoinColumn(nullable = true)
    private Role role;





}
