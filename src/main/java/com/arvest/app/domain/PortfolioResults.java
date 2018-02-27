package com.arvest.app.domain;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class PortfolioResults {

    private Portfolio[] results;
}
