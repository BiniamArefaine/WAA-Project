package edu.miu.cs.auctionproject.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
//@AllArgsConstructor
@NoArgsConstructor
public class DepositPayment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private double deposit;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private User user;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(nullable = true)
    private Product product;

}
