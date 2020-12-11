package edu.miu.cs.auctionproject.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;


@Entity
@Getter
@Setter
//@AllArgsConstructor
@NoArgsConstructor
@Table(name = "credentials")
    public class Credential {

        @Id
        @Column(name = "credential_id",nullable=false)
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long credentialId;

        @Column(name = "user_name",nullable=true)
        @NotBlank(message = "Please provide user name")
        private String userName;

        @Column(name = "password",nullable=true)
        @NotBlank(message = "Please provide password")
        private String password;

        @OneToOne(mappedBy = "credential", cascade = CascadeType.ALL)
        private User user;


    }
