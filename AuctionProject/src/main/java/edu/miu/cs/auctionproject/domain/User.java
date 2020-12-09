package edu.miu.cs.auctionproject.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
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
    @Column(unique = true)
    private String email;
    private String licenceNumber;
    private String password;
    @OneToMany
    @JoinColumn(name = "user_id",nullable = true)
    private List<Product> product;

    private boolean verification;
    @OneToOne
    @JoinColumn(nullable = true)
    private Role role;
    //added

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn
    @Valid
    private Credential credential;
    @Valid
    @OneToOne(cascade = CascadeType.ALL)
    private Address address;
    public void addProduct(Product p){
        product.add(p);
    }
    public void removeProduct(Product p){
        product.remove(p);
    }


}
