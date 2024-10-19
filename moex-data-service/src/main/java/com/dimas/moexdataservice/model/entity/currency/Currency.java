package com.dimas.moexdataservice.model.entity.currency;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity(name = "currency_data")
@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Currency {
    @Id
    @SequenceGenerator(name = "currency_data_id_seq", allocationSize = 45)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "currency_data_id_seq")
    @Column(name = "id")
    Integer id;

    @Column(name = "trade_date")
    LocalDate tradeDate;

    @Column(name = "trade_time")
    LocalTime tradeTime;

    @Column(name = "rate")
    Double rate;

    @ManyToOne
    @JoinColumn(name = "clearing_type_id", referencedColumnName = "id", nullable = false)
    ClearingType clearingType;

    @ManyToOne
    @JoinColumn(name = "security_id", referencedColumnName = "id", nullable = false)
    Security security;
}
