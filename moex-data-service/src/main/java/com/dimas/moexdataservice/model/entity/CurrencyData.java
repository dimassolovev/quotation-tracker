package com.dimas.moexdataservice.model.entity;

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
public class CurrencyData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Integer id;

    @Column(name = "tradedate")
    LocalDate tradedate;

    @Column(name = "tradetime")
    LocalTime tradetime;

    @Column(name = "secid")
    String secid;

    @Column(name = "rate")
    Double rate;

    @ManyToOne
    @JoinColumn(name = "clearing_type_id", referencedColumnName = "id", nullable = false)
    ClearingType clearingType;

    @ManyToOne
    @JoinColumn(name = "security_id", referencedColumnName = "id", nullable = false)
    Security security;
}
