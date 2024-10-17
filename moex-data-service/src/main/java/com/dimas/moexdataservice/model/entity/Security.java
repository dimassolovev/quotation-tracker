package com.dimas.moexdataservice.model.entity;

import jakarta.persistence.*;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity(name = "security")
@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Security {
    @Id
    @SequenceGenerator(name = "security_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "security_id_seq")
    @Column(name = "id")
    Integer id;

    @Column(name ="pair_code")
    String pairCode;

    @OneToMany(mappedBy = "security")
    List<Currency> currencyDataList;
}
