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
@Table(name = "user_credentials")
public class UserCredentials {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @OneToOne(targetEntity = UserUUID.class, mappedBy = "userCredentials", fetch = FetchType.LAZY)
    private UserUUID userUUID;

    @ManyToMany(targetEntity = Role.class)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles;

    @OneToMany(targetEntity = UserHistory.class, mappedBy = "userCredentials")
    private List<UserHistory> userHistoryList;

    @ManyToMany(targetEntity = Security.class)
    @JoinTable(
            name = "user_security",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "security_id")
    )
    private List<Security> securities;
}
