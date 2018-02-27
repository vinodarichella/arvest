package com.arvest.app.service;

import com.arvest.app.config.ClientConfig;
import com.arvest.app.domain.Quote;
import com.arvest.app.util.GsonUtil;
import com.google.common.io.CharStreams;
import com.google.gson.Gson;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStreamReader;

@Service
public class QuotesService {

    @Autowired
    ClientConfig clientConfig;

    public Quote getQuote(String url) throws Exception {
        CloseableHttpClient httpClient = clientConfig.httpClient();
        HttpGet get = new HttpGet(url);
        String response = execute(httpClient, get);
        return GsonUtil.fromJson(response, Quote.class);
    }

    public String execute(CloseableHttpClient httpClient, HttpRequestBase request) {
        try {
            final CloseableHttpResponse response = httpClient.execute(request);
            return CharStreams.toString(new InputStreamReader(response.getEntity().getContent()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String getQuoteSymbol(String url) throws Exception {
        String quoteSymbol = "";
        Quote quote = getQuote(url);
        if (quote != null) {
            quoteSymbol = quote.getSymbol();
        }
        return quoteSymbol;
    }

    public Double getPrice(String url) throws Exception {
        Double last_trade_price = 0.0;
        Quote quote = getQuote(url);
        if (quote != null) {
            last_trade_price = quote.getLast_trade_price();
        }
        return last_trade_price;
    }
}
