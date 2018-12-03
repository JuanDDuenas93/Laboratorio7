/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JuanDuenas
 */
public interface HttpConnectionInterface {

    public static final String ALPHAVANTAGE = "alphavantage";
    public static final String IEXTRADING = "iextrading";

    public final String USER_AGENT = "Mozilla/5.0";

    public String getTradingInfo(String name, String type) throws TradingServicesException;

    public String getTradingInfo(String name, String type, String interval) throws TradingServicesException;

    default String getTradingInfo(String GET_URL) throws TradingServicesException {
        try {
            URL obj = new URL(GET_URL);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", USER_AGENT);

            //The following invocation perform the connection implicitly before getting the code
            int responseCode = con.getResponseCode();
            System.out.println("GET Response Code :: " + responseCode);

            if (responseCode == HttpURLConnection.HTTP_OK || responseCode == HttpURLConnection.HTTP_ACCEPTED) {
                // success
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // print result
                return response.toString();

            } else {
                System.out.println("GET request not worked");
            }
            System.out.println("GET DONE");
            throw new TradingServicesException("Error consutando al API externo.");
        } catch (IOException ex) {
            Logger.getLogger(HttpConnectionInterface.class.getName()).log(Level.SEVERE, null, ex);
            throw new TradingServicesException("Error when fetching from external API");
        }
    }

    static HttpConnectionInterface getExternalAPI(String externalAPI) throws TradingServicesException {
        if (externalAPI.equals(HttpConnectionInterface.ALPHAVANTAGE)) {
            return new HttpConnectionToAlphaVantage();
        } else if (externalAPI.equals(HttpConnectionInterface.IEXTRADING)) {
            return new HttpConnectionToIextrading();
        } else {
            throw new TradingServicesException("Invalid Source.");
        }
    }
}
