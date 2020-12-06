package edu.miu.cs.auctionproject.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String productName;
    private String description;
    private String startingPrice;
    private boolean sold;
    private boolean release;
    @ElementCollection
    private List<String> imageLink;

}
