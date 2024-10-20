package com.dimas.authenticationservice.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "user_uuids")
public class UserUUID {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "user_uuid")
    private UUID userUuid;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserCredentials userCredentials;
}
