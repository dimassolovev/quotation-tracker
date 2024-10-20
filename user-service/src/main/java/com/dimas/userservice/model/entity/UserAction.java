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
@Table(name = "user_actions")
public class UserAction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "action_type", nullable = false, unique = true)
    private String actionType;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "userAction")
    private List<UserHistory> userHistoryList;
}
