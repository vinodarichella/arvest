package com.arvest.app.domain;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Instrument {

    private String tradability;

    private String tradeable;

    private Double maintenance_ratio;

    private String symbol;

    private Double day_trade_ratio;

    private String state;

    private String list_date;

    private String simple_name;

    private String type;

    private String url;

    private String country;

    private Double margin_initial_ratio;

    private String id;

    private Double min_tick_size;

    private String fundamentals;

    private String market;

    private String splits;

    private String quote;

    private String name;

    private String bloomberg_unique;

}
