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

    @OneToMany
    private List<Category>categories;

}
