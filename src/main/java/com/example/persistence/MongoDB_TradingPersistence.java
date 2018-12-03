/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.persistence;

import com.example.TradingInfo;
import com.example.TradingRepository;
import com.example.controller.HttpConnectionInterface;
import com.example.controller.TradingServicesException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author JuanDuenas
 */
@Service
public class MongoDB_TradingPersistence implements TradingPersistence{

    @Autowired
    private TradingRepository repo;

    @Override
    public String getTradingInfo(String name, String type, String source) throws TradingServicesException {
        
        HttpConnectionInterface externalAPI = HttpConnectionInterface.getExternalAPI(source);
        TradingInfo tradingInfo = repo.searchByKey(source + name + type);
        if (tradingInfo == null) {
            String data = externalAPI.getTradingInfo(name, type);
            tradingInfo = new TradingInfo(name, type, source, data);
            repo.save(tradingInfo);
        }
        return tradingInfo.getData();
    }

    @Override
    public String getTradingInfo(String name, String type, String interval, String source) throws TradingServicesException {
        HttpConnectionInterface externalAPI = HttpConnectionInterface.getExternalAPI(source);
        TradingInfo tradingInfo = repo.searchByKey(source + name + type + interval);
        if (tradingInfo == null) {
            String data = externalAPI.getTradingInfo(name, type);
            tradingInfo = new TradingInfo(name, type, source, interval, data);
            repo.save(tradingInfo);
        }
        return tradingInfo.getData();
    }

}   

