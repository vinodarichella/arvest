package com.arvest.app.domain;

import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class Position {
    private Double intraday_average_buy_price;

    private String updated_at;

    private Double shares_held_for_stock_grants;

    private Double shares_held_for_sells;

    private String created_at;

    private Double intraday_quantity;

    private String account;

    private Double quantity;

    private String instrument;

    private Double average_buy_price;

    private String url;

    private Double shares_held_for_buys;
}
