package com.dimas.userservice.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_uuids")
public class UserUUID {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "user_uuid", unique = true, nullable = false)
    private UUID userUuid;

    @OneToOne(targetEntity = UserCredentials.class)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserCredentials userCredentials;
}
