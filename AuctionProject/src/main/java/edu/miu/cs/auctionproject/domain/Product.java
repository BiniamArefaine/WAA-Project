package edu.miu.cs.auctionproject.domain;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.ArrayList;
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
    private Integer bidcount;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate upLoadedDate=LocalDate.now();
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Future
    private LocalDate dueDate;
    @ElementCollection
    private List<String> photos=new ArrayList<>();
    @ManyToMany
    private List<Category> categories;

    private Double maxBidPrice=startingPrice;
    private Boolean shipped;
    @Transient
    public void addPhoto(String file) {
        photos.add(file);
    }


}
