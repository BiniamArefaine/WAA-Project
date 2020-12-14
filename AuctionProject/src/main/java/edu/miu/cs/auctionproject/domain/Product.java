package edu.miu.cs.auctionproject.domain;

import lombok.*;
import org.hibernate.validator.constraints.Currency;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
    private double startingPrice;

    private boolean sold;
    //added

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate paymentDate;
    //added
    private boolean paidInFull;

    @NotEmpty( message = "product release must be specified")
    private String release;
    private int bidcount;
//    private LocalDate upLoadedDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate upLoadedDate=LocalDate.now();
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "please enter future date")
    private LocalDate dueDate;

    @ManyToMany
    private List<Category>categories;
    @ElementCollection
    private List<String> photos=new ArrayList<>();

    private Double maxBidPrice=startingPrice;
    private boolean shipped;
    private boolean received;
    private double depositpayment;

    @Transient
    public void addPhoto(String file) {
        photos.add(file);
    }




}
