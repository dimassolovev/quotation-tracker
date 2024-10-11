package com.dimas.moexdataservice.model.entity;

import jakarta.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity(name = "currency_data")
@Data
@NoArgsConstructor
public class CurrencyData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "tradedate")
    private LocalDate tradedate;

    @Column(name = "tradetime")
    private LocalTime tradetime;

    @Column(name = "secid")
    private String secid;

    @Column(name = "rate")
    private Double rate;

    @ManyToOne
    @JoinColumn(name = "clearing_type_id", referencedColumnName = "id", nullable = false)
    private ClearingType clearingType;

    @ManyToOne
    @JoinColumn(name = "security_id", referencedColumnName = "id", nullable = false)
    private Security security;
}
