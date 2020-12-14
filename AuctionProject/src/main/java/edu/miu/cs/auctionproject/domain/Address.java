package edu.miu.cs.auctionproject.domain;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
//@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Please Enter Street")
    private String street;
    @NotBlank(message = "Please Enter City")
    private String city;
    @NotBlank(message="Please Enter state")
    @Size(min = 2, max = 2)
    private String state;
    @NotBlank(message="Please Enter ZipCode")
    private String zipcode;

    @OneToOne(mappedBy = "address", cascade = CascadeType.ALL)
    User user;

}
