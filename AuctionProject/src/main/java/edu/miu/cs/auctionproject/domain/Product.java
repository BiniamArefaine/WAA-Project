package edu.miu.cs.auctionproject.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.Min;
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

    @Min(value = 0,message = "starting price required!")
    private Double startingPrice;

    private boolean sold;
    private boolean release;
    //biniam-dave
    private LocalDate upLoadedDate;
//    @ElementCollection
//    private List<MultipartFile> imageLink;
//    @Lob
//    @Column(name = "Image", length = Integer.MAX_VALUE, nullable = true)
//    private byte[] images;
    @Column(nullable = true, length = 64)
    private String photos;
    @OneToMany
    private List<Category>categories;
    @Transient
    public String getPhotosImagePath() {
        if (photos == null || id == null) return null;
        return "/images/product-photos/" + id + "/" + photos;
    }

}
