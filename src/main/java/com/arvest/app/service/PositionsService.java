package com.arvest.app.service;

import com.arvest.app.config.ClientConfig;
import com.arvest.app.config.RobinhoodUrls;
import com.arvest.app.domain.*;
import com.arvest.app.util.GsonUtil;
import com.arvest.app.util.http.JsonHttpClient;
import com.arvest.app.util.http.UrlEncodedHttpClient;
import com.google.common.collect.Lists;
import com.google.common.io.CharStreams;
import com.google.gson.Gson;
import lombok.ToString;
import org.apache.commons.math3.util.Precision;
import org.apache.http.Header;
import org.apache.http.HttpHeaders;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class PositionsService {

    @Autowired
    RobinhoodUrls urls;

    @Autowired
    InstrumentService instrumentService;

    @Autowired
    QuotesService quotesService;

    @Autowired
    HttpService httpService;

    private List<Position> getPositions(Token token) {
        String response;
        HttpGet get = new HttpGet(urls.getPositions());
        get.addHeader(HttpHeaders.AUTHORIZATION, "Token " + token.getToken());
        response = httpService.httpGet(get);
        Positions positions = GsonUtil.fromJson(response, Positions.class);
        return Lists.newArrayList(positions.getResults());
    }

    public List<CurrentPositions> getCurrentPoistions (Token token, final Portfolio portfolio) throws Exception {
        List<CurrentPositions> currentPositionsList = getPositions(token).stream()
                .filter(position -> position.getQuantity() > 0)
                .map(p -> getCurrentPositions(p, portfolio))
                .sorted(Comparator.comparing(CurrentPositions::getPercent).thenComparing(CurrentPositions::getProfitOrLoss))
                .collect(Collectors.toList());
        printPositions(currentPositionsList);
        return currentPositionsList;
    }

    private void printPositions(List<CurrentPositions> currentPositionsList) {
        currentPositionsList.forEach(System.out::println);
    }

    public CurrentPositions getCurrentPositions(Position position, Portfolio portfolio)  {
        CurrentPositions currentPositions = new CurrentPositions();
        try {
            Instrument instrument = instrumentService.getInstrument(position.getInstrument());
            Quote quote = quotesService.getQuote(instrument.getQuote());

            double value = position.getQuantity() * quote.getLast_trade_price();
            double buyPrice = position.getQuantity() * position.getAverage_buy_price();
            double percent = value / portfolio.getMarket_value() * 100;
            double profit = value - buyPrice;

            currentPositions.setStockName(instrument.getName());
            currentPositions.setAverageBuyPrice(position.getAverage_buy_price());
            currentPositions.setInstrument(instrument.getQuote());
            currentPositions.setQuantity(position.getQuantity());
            currentPositions.setSymbol(instrument.getSymbol());
            currentPositions.setTotalValue(Precision.round(value, 2));
            currentPositions.setPercent(Precision.round(percent, 2));
            currentPositions.setProfitOrLoss(Precision.round(profit, 2));
            currentPositions.setCurrentValue(quote.getLast_trade_price());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return currentPositions;
    }
}
