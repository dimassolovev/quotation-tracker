package com.dimas.userservice.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "securities")
public class Security {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "pair_code", unique = true, nullable = false)
    private String pairCode;
    @ManyToMany(targetEntity = UserCredentials.class, mappedBy = "securities")
    List<UserCredentials> userCredentialsList;
}
