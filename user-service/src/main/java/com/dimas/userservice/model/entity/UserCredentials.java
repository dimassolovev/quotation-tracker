package com.dimas.userservice.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

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

    @Column(name = "uuid", unique = true, nullable = false)
    private UUID userUUID;

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
