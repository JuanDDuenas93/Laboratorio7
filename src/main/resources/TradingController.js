/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var tradingController = (function () {

    function clearTables() {
        var content = document.getElementById("content");
        var tables = document.getElementsByClassName("table");
        while (tables.length > 0) {
            content.removeChild(tables[0]);
        }
        var titles = document.getElementsByClassName("table-title");
        while (titles.length > 0) {
            content.removeChild(titles[0]);
        }
    }


    function addTradingInfoToTable(tradingInfo) {
        var tableOrder = document.createElement("table");
        var header = document.createElement("tr");

        var cell = document.createElement("th");
        cell.innerHTML = "Date";
        header.appendChild(cell);

        var cell = document.createElement("th");
        cell.innerHTML = "Open";
        header.appendChild(cell);

        var cell = document.createElement("th");
        cell.innerHTML = "High";
        header.appendChild(cell);

        var cell = document.createElement("th");
        cell.innerHTML = "Low";
        header.appendChild(cell);

        var cell = document.createElement("th");
        cell.innerHTML = "Close";
        header.appendChild(cell);

        var cell = document.createElement("th");
        cell.innerHTML = "Volume";
        header.appendChild(cell);


        tableOrder.appendChild(header);

        tableOrder.setAttribute("class", "table");
        for (i in tradingInfo) {
            var row = document.createElement("tr");

            var cell = document.createElement("td");
            cell.innerHTML = tradingInfo[i].date;
            row.appendChild(cell);

            var cell = document.createElement("td");
            cell.innerHTML = tradingInfo[i].open;
            row.appendChild(cell);

            var cell = document.createElement("td");
            cell.innerHTML = tradingInfo[i].high;
            row.appendChild(cell);

            var cell = document.createElement("td");
            cell.innerHTML = tradingInfo[i].low;
            row.appendChild(cell);

            var cell = document.createElement("td");
            cell.innerHTML = tradingInfo[i].close;
            row.appendChild(cell);

            var cell = document.createElement("td");
            cell.innerHTML = tradingInfo[i].volume;
            row.appendChild(cell);

            tableOrder.appendChild(row);
        }
        var title = document.createElement("h4");
        title.setAttribute("class", "table-title");
        title.innerHTML = "Table";
        var firstTable = document.getElementById("HTMLtable");
        content.insertBefore(tableOrder, firstTable);
        content.insertBefore(title, tableOrder);
    }

    function getSelected(id) {
        var selectedTable = document.getElementById(id);
        var tableLabel = selectedTable.options[selectedTable.selectedIndex].value;
        return tableLabel;
    }


    function loadTradingInfo(SerieBuilder, filterSource) {
        clearTables();
        loadInfo(SerieBuilder, filterSource);
    }

    function SerieAlphavantageBuilder(tradingInfo) {
        var buildTradingInfo = [];
        var counter = 0;
        var timeForTradingInfo;
        for(a in tradingInfo){
            if(counter++ === 1){
                timeForTradingINfo = a;
            }
        }
        for (i in tradingInfo[timeForTradingInfo]) {
            var info = {date: i, open: tradingInfo[timeForTradingInfo][i]["1. open"], high: tradingInfo[timeForTradingInfo][i]["2. high"], low: tradingInfo[timeForTradingInfo][i]["3. low"], close: tradingInfo[timeForTradingInfo][i]["4. close"], volume: tradingInfo[timeForTradingInfo][i]["5. volume"]};
            buildTradingInfo.push(info);
        }
        addTradingInfo(buildTradingInfo);
    }

    function loadInfo(callback, filterSource) {
        axios.all([filterSource()])
                .then(axios.spread(function (info) {
                    callback(info);
                }));
    }

    function getInfoFromAlphavantage() {
        var selection = getSelected("selectFunction");
        var name = document.getElementById("company").value;
        if (selection === "Intraday") {
            var interval = getSelected("interval");
            return tradingRestController.getIntervalInfoFromAlphavantage(name, selection, interval);
        }
        return tradingRestController.getInfoAtAlphavantage(name, selection);
    }

    function getInfoFromIextrading() {
        var selection = getSelected("selectFunction");
        var name = document.getElementById("company").value;
        if (typeSelected === "Date") {
            var date = document.getElementById("date").value;
            return tradingRestController.getInfoDateIextrading(name, selection, date);
        }
        return tradingRestController.getInfoAtIextrading(name, selection);
    }

    return {
        loadTradingInfo: loadTradingInfo,
        getInfoFromIextrading: getInfoFromIextrading,
        getInfoFromAlphavantage: getInfoFromAlphavantage,
        SerieIextradingBuilder: addTradingInfoToTable,
        SerieAlphavantageBuilder: SerieAlphavantageBuilder
    };
})();

var tradingRestController = (function () {
    var url = '';

    function getInfoAtAlphavantage(name, type) {
        return axios.get(url+"/trading/alphavantage/"+name+"/"+type).then(function (response) {
            return response.data;
        })
    }

    function getInInfoFromAlphavantage(name, type, interval) {
        return axios.get(url+"/trading/alphavantage/"+name+"/"+type+"/"+interval).then(function (response) {
            return response.data;
        })
    }

    function getInfoAtIextrading(name, type) {
        return axios.get(url+"/trading/iextrading/"+name+"/"+type).then(function (response) {
            return response.data;
        })
    }

    function getInfoDateIextrading(name, type, date) {
        return axios.get(url+"/trading/iextrading/"+name+"/"+type+"/"+date).then(function (response) {
            return response.data;
        })
    }



    return {
        getInfoAtAlphavantage: getInfoAtAlphavantage,
        getInInfoFromAlphavantage: getInInfoFromAlphavantage,
        getInfoAtIextrading: getInfoAtIextrading,
        getInfoDateIextrading: getInfoDateIextrading

    };
})();
