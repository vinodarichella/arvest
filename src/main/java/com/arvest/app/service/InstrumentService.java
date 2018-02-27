package com.arvest.app.service;

import com.arvest.app.config.ClientConfig;
import com.arvest.app.domain.Instrument;
import com.arvest.app.util.GsonUtil;
import com.arvest.app.util.http.JsonHttpClient;
import com.google.common.io.CharStreams;
import com.google.gson.Gson;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStreamReader;

@Service
public class InstrumentService {

    @Autowired
    HttpService httpService;

    public Instrument getInstrument(String url) {
        HttpGet get = new HttpGet(url);
        String response = httpService.httpGet(get);
        return GsonUtil.fromJson(response, Instrument.class);
    }
}
