package com.dimas.quotationdataaggregatorservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "moex-client")
public interface MoscowExchangeClient {

    @GetMapping(value = "/iss/statistics/engines/futures/markets/indicativerates/securities/{security}.csv")
    String getCurrency(
            @PathVariable("security") String security,
            @RequestParam(required = false, name = "from") String from,
            @RequestParam(required = false, name = "till") String till,
            @RequestParam(required = false, name = "iss.meta", defaultValue = "on") String mode,
            @RequestParam(required = false, name = "iss.only") String choice,
            @RequestParam(required = false, name = "limit") Integer limit,
            @RequestParam(required = false, name = "sort_order") String stringOrder
    );
}
