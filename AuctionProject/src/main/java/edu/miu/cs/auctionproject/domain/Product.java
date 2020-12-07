package edu.miu.cs.auctionproject.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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
    @NotNull
    @NotBlank(message = "product name required!")
    private String productName;
    @NotEmpty
    @NotBlank(message="description required!")
    private String description;

    @NotBlank(message = "starting price required!")
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
