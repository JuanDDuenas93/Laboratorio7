/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var TradingServices = (function () {
  
  
  return {
    getIntraday: function (identifier) {
        return "https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol="+identifier+"&inverval=5min&apikey=BSK04RWGZFOT5SRB"
    },
    getDaily: function (identifier) {
        return "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol="+identifier+"&apikey=BSK04RWGZFOT5SRB"
    },
    getWeekly: function (identifier) {
        return "https://www.alphavantage.co/query?function=TIME_SERIES_WEEKLY&symbol="+identifier+"&apikey=BSK04RWGZFOT5SRB"
    },
    getMonthly: function (identifier) {
        return "https://www.alphavantage.co/query?function=TIME_SERIES_MONTHLY&symbol="+identifier+"&apikey=BSK04RWGZFOT5SRB"
    }
  };

})();
