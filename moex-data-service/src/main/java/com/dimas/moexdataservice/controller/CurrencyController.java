package com.dimas.moexdataservice.controller;

import com.dimas.moexdataservice.model.dto.currency.CurrencyDataDto;
import com.dimas.moexdataservice.model.dto.currency.DataDto;
import com.dimas.moexdataservice.service.CurrencyCacheService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("${other.currency.related_path}")
public class CurrencyController {
    private final CurrencyCacheService currencyCacheService;

    @GetMapping("/list")
    public ResponseEntity<DataDto<List<CurrencyDataDto>>> getCurrenciesByDate(
            @RequestParam(name = "tradeDate", required = false) String tradeDate
    ) {
        return ResponseEntity
                .ok(this.currencyCacheService.find(
                                tradeDate == null ? LocalDate.now().toString() : tradeDate
                        )
                );
    }

    @GetMapping("/list/{pairCode}")
    public ResponseEntity<DataDto<List<CurrencyDataDto>>> getCurrenciesByPairCode(
            @PathVariable("pairCode") String currencyCode,
            @RequestParam(name = "tradeDate", required = false) String tradeDate
    ) {
        return ResponseEntity
                .ok(this.currencyCacheService.findByPairCode(
                        tradeDate == null ? LocalDate.now().toString() : tradeDate,
                        String.format("%s/RUB", currencyCode)
                        )
                );
    }
}