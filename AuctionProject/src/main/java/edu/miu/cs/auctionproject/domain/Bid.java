package edu.miu.cs.auctionproject.domain;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Map;


@Entity
@Getter
@Setter
//@AllArgsConstructor
@NoArgsConstructor
public class Bid {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private Product product;

    @ElementCollection(fetch = FetchType.EAGER)
    @MapKeyColumn(name="user")
    @Column(name="price")
    @CollectionTable(name="bid_user", joinColumns=@JoinColumn(name="bid_id"))
    private Map<Long,Double> users;



}
