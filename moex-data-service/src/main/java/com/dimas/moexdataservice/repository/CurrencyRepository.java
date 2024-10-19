package com.dimas.moexdataservice.repository;

import com.dimas.moexdataservice.model.entity.currency.Currency;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Integer> {
    List<Currency> findByTradeDate(Date tradeDate);

    @Query("SELECT c FROM currency_data c WHERE :tradeDate = c.tradeDate AND :pairCode = c.security.pairCode")
    List<Currency> findByFiltration(
            @Param("tradeDate") Date tradeDate,
            @Param("pairCode") String pairCode);
}
