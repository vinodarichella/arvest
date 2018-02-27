package com.arvest.app.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class Portfolio {
    private Double excess_maintenance;

    private Double equity;

    private Double withdrawable_amount;

    private Double unwithdrawable_deposits;

    private Double excess_margin_with_uncleared_deposits;

    private Double last_core_equity;

    private String url;

    private Double market_value;

    private Double unwithdrawable_grants;

    private Double adjusted_equity_previous_close;

    private Double excess_maintenance_with_uncleared_deposits;

    private Double equity_previous_close;

    private String account;

    private Double last_core_market_value;

    private Double extended_hours_market_value;

    private Double excess_margin;

    private String start_date;

    private Double extended_hours_equity;

}
