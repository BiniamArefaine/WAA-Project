package edu.miu.cs.auctionproject.domain;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;


@Entity
@Getter
@Setter
//@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    private String release;
    private int bidcount;
//    private LocalDate upLoadedDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Future
    private LocalDate dueDate;
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
