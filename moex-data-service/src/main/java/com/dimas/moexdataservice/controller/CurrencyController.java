package com.dimas.moexdataservice.controller;

import com.dimas.moexdataservice.exception.IncorrectDateFormat;
import com.dimas.moexdataservice.model.dto.currency.CurrencyDataDto;
import com.dimas.moexdataservice.model.dto.currency.DataDto;
import com.dimas.moexdataservice.model.dto.other.ExceptionMessage;
import com.dimas.moexdataservice.service.CurrencyCacheService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Tag(name = "Currency")
@RestController
@RequiredArgsConstructor
@RequestMapping("${other.currency.related_path}")
public class CurrencyController {
    private final CurrencyCacheService currencyCacheService;

    @Operation(
            description = "Endpoint for getting currency data list by date.",
            summary = "Return list of currency from redis cache or postgres.",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CurrencyDataDto.class)
                            )
                    ),
                    @ApiResponse(
                            description = "Method Not Allowed",
                            responseCode = "405",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionMessage.class)
                            )
                    )
            }
    )
    @GetMapping("/list")
    public ResponseEntity<DataDto<List<CurrencyDataDto>>> getCurrenciesByDate(
            @Parameter(description = "Trade date in format yyyy-MM-dd", example = "2024-10-20")
            @RequestParam(name = "tradeDate", required = false)
            String tradeDate
    ) throws IncorrectDateFormat {

        return ResponseEntity
                .ok(this.currencyCacheService.find(
                                tradeDate == null ? LocalDate.now().toString() : tradeDate
                        )
                );
    }

    @Operation(
            description = "Endpoint for getting currency data list by date and currency code.",
            summary = "Return list of currency from redis cache or postgres.",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CurrencyDataDto.class)
                            )
                    ),
                    @ApiResponse(
                            description = "Method Not Allowed",
                            responseCode = "405",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionMessage.class)
                            )
                    )
            }
    )
    @GetMapping("/list/{currencyCode}")
    public ResponseEntity<DataDto<List<CurrencyDataDto>>> getCurrenciesByPairCode(
            @Parameter(description = "Currency code", example = "USD")
            @PathVariable("currencyCode")
            String currencyCode,

            @Parameter(description = "Trade date in format yyyy-MM-dd", example = "2024-10-20")
            @RequestParam(name = "tradeDate", required = false)
            String tradeDate
    ) throws IncorrectDateFormat {

        return ResponseEntity
                .ok(this.currencyCacheService.findByPairCode(
                        tradeDate == null ? LocalDate.now().toString() : tradeDate,
                        String.format("%s/RUB", currencyCode)
                        )
                );
    }
}