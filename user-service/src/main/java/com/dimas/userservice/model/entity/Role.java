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
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @ManyToMany(targetEntity = UserCredentials.class, mappedBy = "roles")
    private List<UserCredentials> userCredentialsList;
}
