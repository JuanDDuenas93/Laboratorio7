/*
 * Copyright 2002-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;
import static org.springframework.http.HttpHeaders.USER_AGENT;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@SpringBootApplication
public class Main {
    
     private String keyAPI = "BSK04RWGZFOT5SRB";

  @Value("${spring.datasource.url}")
  private String dbUrl;

  @Autowired
  private DataSource dataSource;

  public static void main(String[] args) throws Exception {
    SpringApplication.run(Main.class, args);
  }

  @RequestMapping("/")
  String index() {
    return "index";
  }

  @RequestMapping("/db")
  String db(Map<String, Object> model) {
    try (Connection connection = dataSource.getConnection()) {
      Statement stmt = connection.createStatement();
      stmt.executeUpdate("CREATE TABLE IF NOT EXISTS ticks (tick timestamp)");
      stmt.executeUpdate("INSERT INTO ticks VALUES (now())");
      ResultSet rs = stmt.executeQuery("SELECT tick FROM ticks");

      ArrayList<String> output = new ArrayList<String>();
      while (rs.next()) {
        output.add("Read from DB: " + rs.getTimestamp("tick"));
      }

      model.put("records", output);
      return "db";
    } catch (Exception e) {
      model.put("message", e.getMessage());
      return "error";
    }
  }

  @Bean
  public DataSource dataSource() throws SQLException {
    if (dbUrl == null || dbUrl.isEmpty()) {
      return new HikariDataSource();
    } else {
      HikariConfig config = new HikariConfig();
      config.setJdbcUrl(dbUrl);
      return new HikariDataSource(config);
    }
  }
  
  @RequestMapping(path = "Trading/daily/{symbol}", method = RequestMethod.GET)
    public ResponseEntity<?> getDaily(@PathVariable String symbol) {

        try {
            return new ResponseEntity<>(null, HttpStatus.ACCEPTED);

        } catch (Exception ex) {
            return new ResponseEntity<>("Error", HttpStatus.NOT_FOUND);

        }

    }

    @RequestMapping(path = "Trading/intraday/{symbol}", method = RequestMethod.GET)
    public ResponseEntity<?> getIntraday(@PathVariable String symbol) throws ProtocolException, MalformedURLException, IOException {

       
        return new ResponseEntity<>(connectToExternalProvider("https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol="+symbol+"&interval=5min&apikey=" + keyAPI + "").toString(), HttpStatus.ACCEPTED);
        
    }

    @RequestMapping(path = "Trading/weekly/{symbol}", method = RequestMethod.GET)
    public ResponseEntity<?> getWeekly(@PathVariable String symbol) {

        try {
            return new ResponseEntity<>("Hello", HttpStatus.ACCEPTED);

        } catch (Exception ex) {
            return new ResponseEntity<>("Error", HttpStatus.NOT_FOUND);

        }

    }

    @RequestMapping(path = "Trading/monthly/{symbol}", method = RequestMethod.GET)
    public ResponseEntity<?> getMonthly(@PathVariable String symbol) {

        try {
            return new ResponseEntity<>(null, HttpStatus.ACCEPTED);

        } catch (Exception ex) {
            return new ResponseEntity<>("Error", HttpStatus.NOT_FOUND);

        }

    }
    
    private StringBuffer connectToExternalProvider(String url) throws MalformedURLException, IOException{
        URL obj = new URL(url);
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
        
        return response;
    }

}
