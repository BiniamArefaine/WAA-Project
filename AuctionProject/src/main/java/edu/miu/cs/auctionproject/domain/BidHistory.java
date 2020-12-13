package edu.miu.cs.auctionproject.domain;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class BidHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBid;

    private double bidAmount;

    @OneToOne
    @JoinColumn
    private Product product;

    @OneToOne
    @JoinColumn
    private User user;


}
