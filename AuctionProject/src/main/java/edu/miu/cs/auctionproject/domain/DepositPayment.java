package edu.miu.cs.auctionproject.domain;

import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
//@AllArgsConstructor
@NoArgsConstructor
public class DepositPayment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Please Enter Name In Card")
    private String nameInCard;

    private Double deposit;
    @ManyToOne
    private User user;
    @ManyToOne
    private Product product;

    @NotNull(message = "Please Enter Card Number")
    @Pattern(regexp = "^(?:4[0-9]{12}(?:[0-9]{3})?|[25][1-7][0-9]{14}|6(?:011|5[0-9][0-9])" +
            "[0-9]{12}|3[47][0-9]{13}|3(?:0[0-5]|[68][0-9])[0-9]{11}|(?:2131|1800|35\\d{3})\\d{11})$",
            message = "Please Enter Valid Card Number")
    private String cardNumber;

    @NotNull(message = "Please Enter 3 digits CVV Card Number")
    private Integer cvv;

    private Double finalPayment;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate paymentDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate cardExpiration;
//    @ManyToOne
//    private Address address;

}
