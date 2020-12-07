package edu.miu.cs.auctionproject.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

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
//    @ElementCollection
//    private List<MultipartFile> imageLink;
    @Lob
    @Column(name = "Image", length = Integer.MAX_VALUE, nullable = true)
    private byte[] images;
    @OneToMany
    private List<Category>categories;

}
