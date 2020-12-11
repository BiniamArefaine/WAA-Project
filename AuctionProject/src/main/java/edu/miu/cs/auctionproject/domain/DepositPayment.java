package edu.miu.cs.auctionproject.domain;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import java.time.LocalDate;

@Entity
@Getter
@Setter
//@AllArgsConstructor
@NoArgsConstructor
public class DepositPayment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nameInCard;
    private Double deposit;
    @OneToOne
    private User user;
    @OneToOne(cascade = CascadeType.ALL)
    private Product product;

    private String cardNumber;
    private String cvv;
    private Double finalPayment;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate paymentDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate cardExpiration;
    @ManyToOne
    private Address address;

}
