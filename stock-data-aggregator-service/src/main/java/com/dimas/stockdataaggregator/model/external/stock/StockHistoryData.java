package com.dimas.stockdataaggregator.model.external.stock;

import com.dimas.stockdataaggregator.model.external.MoexData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockHistoryData implements MoexData {
    private List<Trade> trades;
}
