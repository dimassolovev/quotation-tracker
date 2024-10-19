package com.dimas.moexdataservice.service;

import com.dimas.moexdataservice.model.dto.currency.CurrencyDataDto;
import com.dimas.moexdataservice.model.dto.currency.DataDto;

import java.util.List;

public interface CurrencyCacheService {
    DataDto<List<CurrencyDataDto>> find(String date);
}
