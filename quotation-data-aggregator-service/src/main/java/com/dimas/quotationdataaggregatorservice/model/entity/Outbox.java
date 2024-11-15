package com.dimas.quotationdataaggregatorservice.model.entity;

import com.dimas.quotationdataaggregatorservice.model.type.StatusType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnTransformer;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "outbox_tasks")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Outbox {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "timestamp", nullable = false)
    private LocalDateTime timestamp;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_type")
    private StatusType status;

    @Column(name = "topic", nullable = false)
    private String topic;

    @Column(columnDefinition = "jsonb")
    @ColumnTransformer(write = "?::jsonb")
    private String payload;
}
