/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.controller;

/**
 *
 * @author JuanDuenas
 */
public class HttpConnectionToIextrading implements HttpConnectionInterface {
    @Override
    public String getTradingInfo(String name, String type, String date) throws TradingServicesException {
        String GET_URL = String.format("https://api.iextrading.com/1.0/stock/%s/chart/%s/%s", name, type, date);
        return getTradingInfo(GET_URL);
    }

    @Override
    public String getTradingInfo(String name, String type) throws TradingServicesException {
        String GET_URL = String.format("https://api.iextrading.com/1.0/stock/%s/chart/%s", name, type);
        return getTradingInfo(GET_URL);
    }
}
