/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example;

import org.springframework.data.annotation.Id;

/**
 *
 * @author JuanDuenas
 */
public class TradingInfo {

    @Id
    private String key;

    private String source;
    private String name;
    private String type;
    private String data;
    private String interval;
    

    public TradingInfo() {
    }

    public TradingInfo(String name, String type, String source, String interval, String data) {
        this.name = name;
        this.type = type;
        this.source = source;
        this.interval = interval;
        this.data = data;
        this.key = source+name+type+interval;
    }

    public TradingInfo(String name, String type, String source, String data) {
        this.name = name;
        this.type = type;
        this.source = source;
        this.data = data;
        this.key = source+name+type;
    }

    @Override
    public String toString() {
        return "Serie{"+"name="+name+",type="+type+",source="+source+ ",data="+data+'}';
    }

    public String getData() {
        return data;
    }
}
