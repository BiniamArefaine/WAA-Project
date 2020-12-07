package edu.miu.cs.auctionproject.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.time.LocalDate;
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
    //biniam-dave
    private LocalDate upLoadedDate;
    @ElementCollection
    private List<String> imageLink;

    @OneToMany
    private List<Category>categories;

}
