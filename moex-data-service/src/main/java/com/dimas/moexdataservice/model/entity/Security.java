package com.dimas.moexdataservice.model.entity;

import jakarta.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity(name = "security")
@Data
@NoArgsConstructor
public class Security {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name ="pair_code")
    private String pairCode;

    @OneToMany(mappedBy = "security")
    private List<CurrencyData> currencyDataList;
}
