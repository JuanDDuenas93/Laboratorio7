/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.persistence;
import com.example.controller.TradingServicesException;
/**
 *
 * @author JuanDuenas
 */
public interface TradingPersistence {
    
    public String getTradingInfo(String name, String type, String source) throws TradingServicesException;

    public String getTradingInfo(String name, String type, String interval, String source) throws TradingServicesException;

}
