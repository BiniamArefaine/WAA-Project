package edu.miu.cs.auctionproject.domain;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
@Setter
//@AllArgsConstructor
@NoArgsConstructor
public class  Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "name is required")
    @Column(unique = true,nullable = false)
    private  String name;


}
