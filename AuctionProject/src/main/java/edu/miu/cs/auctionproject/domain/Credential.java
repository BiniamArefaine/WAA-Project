package edu.miu.cs.auctionproject.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "credentials")
    public class Credential {

        @Id
        @Column(name = "credential_id",nullable=false)
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long credentialId;

        @Column(name = "user_name",nullable=true)
        @NotBlank(message = "Please provide user name")
        private String userName;

        @Column(name = "password",nullable=true)
        @NotBlank(message = "Please provide password")
        private String password;

        @OneToOne(mappedBy = "credential", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
        private User user;


    }
