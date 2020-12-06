package edu.miu.cs.auctionproject.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class DepositPayment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private double deposit;
    @OneToOne
    @JoinColumn
    private User user;
    @OneToOne
    @JoinColumn
    private Product product;

}
