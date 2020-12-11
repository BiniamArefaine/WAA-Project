package edu.miu.cs.auctionproject.domain;



import edu.miu.cs.auctionproject.validator.CategoryName;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "name is required")
    @CategoryName
    private  String name;


}
