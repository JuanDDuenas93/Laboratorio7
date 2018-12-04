/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.controller;

import com.example.persistence.TradingPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author JuanDuenas
 */
@Service
public class TradingServicesStub implements TradingServices{

    @Autowired
    TradingPersistence tradingPersistence;

    public TradingServicesStub() {
    }

    @Override
    public String getTradingInfo(String name, String type, String source) throws TradingServicesException {
        return tradingPersistence.getTradingInfo(name, type, source);
    }

    @Override
    public String getTradingInfo(String name, String type, String interval, String source) throws TradingServicesException {
        return tradingPersistence.getTradingInfo(name, type, interval, source);
    }
}
