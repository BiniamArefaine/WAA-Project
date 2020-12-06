package edu.miu.cs.auctionproject.domain;

import jdk.vm.ci.meta.Local;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Entity
@Data
public class Bid {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    private LocalDate dueDate;

    @OneToOne
    @Column(nullable = false)
    private Product product;






}
