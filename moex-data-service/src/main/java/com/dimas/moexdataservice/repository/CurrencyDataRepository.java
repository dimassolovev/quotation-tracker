package com.dimas.moexdataservice.repository;

import com.dimas.moexdataservice.model.entity.CurrencyData;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyDataRepository extends JpaRepository<CurrencyData, Integer> {}
