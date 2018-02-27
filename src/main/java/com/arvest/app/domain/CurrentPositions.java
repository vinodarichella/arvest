package com.arvest.app.domain;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CurrentPositions {

    private String stockName;

    private Double quantity;

    private String instrument;

    private Double averageBuyPrice;

    private String symbol;

    private Double totalValue;

    private Double percent;

    private Double profitOrLoss;

    private Double currentValue;

}
