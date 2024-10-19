package com.dimas.moexdataservice.repository;

import com.dimas.moexdataservice.model.entity.currency.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Integer> {
    Optional<List<Currency>> findByTradeDate(LocalDate date);
}
