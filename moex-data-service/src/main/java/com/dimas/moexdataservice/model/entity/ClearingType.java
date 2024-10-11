package com.dimas.moexdataservice.model.entity;

import jakarta.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity(name = "clearing_type")
@Data
@NoArgsConstructor
public class ClearingType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name ="clearing")
    private String clearing;

    @OneToMany(mappedBy = "clearingType")
    private List<CurrencyData> currencyDataList;
}
