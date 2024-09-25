package com.dimas.stockdataaggregator.model.external.stock;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Trade {
    @JsonProperty("BOARDID") private String boardId;
    @JsonProperty("TRADEDATE") private String tradeDate;
    @JsonProperty("SHORTNAME") private String shortName;
    @JsonProperty("SECID") private String secId;
    @JsonProperty("NUMTRADES") private Integer numTrades;
    @JsonProperty("VALUE") private Double value;
    @JsonProperty("OPEN") private Double open;
    @JsonProperty("LOW") private Double low;
    @JsonProperty("HIGH") private Double high;
    @JsonProperty("LEGALCLOSEPRICE")private Double legalClosePrice;
    @JsonProperty("WAPRICE") private Double waPrice;
    @JsonProperty("CLOSE") private Double close;
    @JsonProperty("VOLUME") private Double volume;
    @JsonProperty("MARKETPRICE2") private Double marketPrice2;
    @JsonProperty("MARKETPRICE3") private Double marketPrice3;
    @JsonProperty("ADMITTEDQUOTE") private Double admittedQuote;
    @JsonProperty("MP2VALTRD") private Double mp2ValTrd;
    @JsonProperty("MARKETPRICE3TRADESVALUE") private Double marketPrice3TradesValue;
    @JsonProperty("ADMITTEDVALUE") private Double admittedValue;
    @JsonProperty("WAVAL") private Double waVal;
    @JsonProperty("TRADINGSESSION") private String tradingSession;
    @JsonProperty("CURRENCYID") private String currencyId;
    @JsonProperty("TRENDCLSPR") private String trendClsPr;
}
