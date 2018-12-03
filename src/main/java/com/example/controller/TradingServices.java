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
public interface TradingServices{
    
    String getTradingInfo(String name, String type, String sorce) throws TradingServicesException;

    String getTradingInfo(String name, String type, String interval, String sorce) throws TradingServicesException;
}
