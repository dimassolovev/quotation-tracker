package com.dimas.quotationdataaggregatorservice.repository;

import com.dimas.quotationdataaggregatorservice.model.entity.Outbox;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface OutboxRepository extends JpaRepository<Outbox, UUID> {

    @Query("SELECT o FROM Outbox o WHERE o.timestamp <= :current_timestamp AND (o.status = 'REQUESTED' OR o.status = 'FAILED')")
    List<Outbox> findByTimestamp(
            @Param("current_timestamp") LocalDateTime currentTimestamp);
}
