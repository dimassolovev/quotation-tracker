package com.dimas.moexdataservice.repository;

import com.dimas.moexdataservice.model.entity.Currency;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Integer> {}
