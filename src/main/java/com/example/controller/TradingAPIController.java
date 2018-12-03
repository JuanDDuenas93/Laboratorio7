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
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.springframework.http.HttpHeaders.USER_AGENT;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author 2085890
 */
/*
@RestController
@RequestMapping(value = "/Trading")
public class TradingAPIController {

    private String keyAPI = "BSK04RWGZFOT5SRB";

    @RequestMapping(path = "/daily/{symbol}", method = RequestMethod.GET)
    public ResponseEntity<?> getDaily(@PathVariable String symbol) {

        try {
            return new ResponseEntity<>("Hello", HttpStatus.ACCEPTED);

        } catch (Exception ex) {
            return new ResponseEntity<>("Error", HttpStatus.NOT_FOUND);

        }

    }

    @RequestMapping(path = "/intraday/{symbol}", method = RequestMethod.GET)
    public ResponseEntity<?> getIntraday(@PathVariable String symbol) throws ProtocolException, MalformedURLException, IOException {

        URL obj = new URL("https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=" + symbol + "&inverval=5min&apikey=" + keyAPI + "");
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);

        //The following invocation perform the connection implicitly before getting the code
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);
        StringBuffer response = new StringBuffer();
        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // print result
            System.out.println(response.toString());
        } else {
            System.out.println("GET request not worked");
        }
        
        return new ResponseEntity<>(response.toString(), HttpStatus.ACCEPTED);

    }

    @RequestMapping(path = "/weekly/{symbol}", method = RequestMethod.GET)
    public ResponseEntity<?> getWeekly(@PathVariable String symbol) {

        try {
            return new ResponseEntity<>("Hello", HttpStatus.ACCEPTED);

        } catch (Exception ex) {
            return new ResponseEntity<>("Error", HttpStatus.NOT_FOUND);

        }

    }

    @RequestMapping(path = "/monthly/{symbol}", method = RequestMethod.GET)
    public ResponseEntity<?> getMonthly(@PathVariable String symbol) {

        try {
            return new ResponseEntity<>("Hello", HttpStatus.ACCEPTED);

        } catch (Exception ex) {
            return new ResponseEntity<>("Error", HttpStatus.NOT_FOUND);

        }

    }

}
*/