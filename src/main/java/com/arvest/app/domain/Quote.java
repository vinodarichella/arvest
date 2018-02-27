package com.arvest.app.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode
public class Quote {

    private Double adjusted_previous_close;

    private String previous_close_date;

    private Double bid_price;

    private String updated_at;

    private boolean trading_halted;

    private String symbol;

    private Double ask_price;

    private Integer bid_size;

    private Double last_extended_hours_trade_price;

    private Double last_trade_price;

    private Double previous_close;

    private Integer ask_size;
}
