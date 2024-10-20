package com.dimas.userservice.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_history")
public class UserHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "time", nullable = false)
    private LocalTime time;

    @ManyToOne(targetEntity = UserCredentials.class)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserCredentials userCredentials;

    @ManyToOne(targetEntity = UserAction.class)
    @JoinColumn(name = "user_action_id", referencedColumnName = "id")
    private UserAction userAction;
}
