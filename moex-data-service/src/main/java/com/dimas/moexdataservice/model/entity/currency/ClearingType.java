package com.dimas.moexdataservice.model.entity.currency;

import jakarta.persistence.*;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity(name = "clearing_type")
@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ClearingType {
    @Id
    @SequenceGenerator(name = "clearing_type_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "clearing_type_id_seq")
    @Column(name = "id")
    Integer id;

    @Column(name ="clearing")
    String clearing;

    @OneToMany(mappedBy = "clearingType")
    List<Currency> currencyDataList;
}
