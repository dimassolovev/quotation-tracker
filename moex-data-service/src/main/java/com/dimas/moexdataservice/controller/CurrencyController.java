package com.dimas.moexdataservice.controller;

import com.dimas.moexdataservice.model.dto.currency.CurrencyDataDto;
import com.dimas.moexdataservice.model.dto.currency.DataDto;
import com.dimas.moexdataservice.service.CurrencyCacheService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("${other.currency.related_path}")
public class CurrencyController {
    private final CurrencyCacheService currencyCacheService;

    @GetMapping("/list")
    public ResponseEntity<DataDto<List<CurrencyDataDto>>> getCurrenciesByDate(
            @RequestParam(name = "date", required = false) String date
    ) {
        return ResponseEntity
                .ok(this.currencyCacheService.find(date == null ? LocalDate.now().toString() : date));
    }
}