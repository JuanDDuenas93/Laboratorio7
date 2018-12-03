/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.controller;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author JuanDuenas
 */
public class HttpConnectionToAlphaVantage implements HttpConnectionInterface{
    
    private final Map<String, String> tradingInfo;

    public HttpConnectionToAlphaVantage() {
        tradingInfo = new HashMap<>();
        tradingInfo.put("Intraday", "TIME_SERIES_INTRADAY");
        tradingInfo.put("Daily", "TIME_SERIES_DAILY");
        tradingInfo.put("Weekly", "TIME_SERIES_WEEKLY");
        tradingInfo.put("Monthly", "TIME_SERIES_MONTHLY");
    }
    

    @Override
    public String getTradingInfo(String name, String type, String interval) throws TradingServicesException {
        String GET_URL = String.format("https://www.alphavantage.co/query?function=%s&symbol=%s&interval=%s&apikey=Q1QZFVJQ21K7C6XM", tradingInfo.get(type), name, interval);
        return getTradingInfo(GET_URL);
    }

    @Override
    public String getTradingInfo(String name, String type) throws TradingServicesException {
        String GET_URL = String.format("https://www.alphavantage.co/query?function=%s&symbol=%s&apikey=Q1QZFVJQ21K7C6XM", tradingInfo.get(type), name);
        return getTradingInfo(GET_URL);
    }
}
