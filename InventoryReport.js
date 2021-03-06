google.charts.load('current', {packages: ['corechart', 'bar']});
// google.charts.setOnLoadCallback(drawStacked);

//Bind click event to make an ajax call to post request of DataVisualization. This will return
// a json object with top 3 review for each city;
$("#btnGetChartData").click(function () {
     $("#btnGetChartData").hide();
    $.ajax({
        url: "InventoryReport",
        type: "POST",
        data: "{}",
        success: function (msg) {
            createDataTable(msg)            
        },
        error: function(){
            console.log("error occurred while making ajax call;")
        }
    });    
});


//This method will parse json data and build datatable required by google api to plot the bar chart.
function createDataTable(jsonData) {
    var parsedData = $.parseJSON(jsonData);
    var data = new Array();
    var productNameArr = new Array();
    var zipCodeArr = new Array();


    //Create an array of product name and an array of zipcodes
    for(var i=0; i<parsedData.length; i++) {
        var productName = parsedData[i]["product"];
        var zipCode = parsedData[i]["product"];
        if(!productNameArr.includes(productName)) {
            productNameArr.push(productName);
        }
        if(!zipCodeArr.includes(zipCode)) {
            zipCodeArr.push(zipCode);
        }
     }

     //Create header array for google api
     var headingArray = new Array(productNameArr.length+1);
     headingArray[0] = "Product";
     var j=0;

     for(var i=1; i<=productNameArr.length; i++) {
        headingArray[i] = productNameArr[j]; 
        j++;
     }

     data[0] = headingArray;
     var m =1;
    
     //Loop through jsondata and create an array of arrays to plot the chart;
    for(var i=0; i<zipCodeArr.length; i++) {
        var dataArr = new Array(headingArray.length);
        dataArr[0] = zipCodeArr[i];
        for(var j=0; j<productNameArr.length; j++) {
            for(k=0; k<parsedData.length; k++) {
                if(parsedData[k]["product"] === zipCodeArr[i] ) {                    
                    // dataArr[j+1] = parseInt(parsedData[k]["sales"]);                   
                    dataArr[j+1] = parseFloat(parsedData[k]["sales"]);                   
                }                 
            }

        }
        
        //Set empty cell elements to zero;
        for(var n=1; n<headingArray.length; n++) {
            if(!(dataArr[n] > 0)) {
                dataArr[n] = 0;
            }
        }
        data[m] = (dataArr);
        m++;

        
     }

     var l = data.length;
    data[0].splice(2,l);
    data[0][1] = "Count"

    for (var i in data) {
        data[i].splice(2,l);
    }
    console.log(" ?????????????????????????????????????????????????????????????????");

    for (var i in data) 
    {
       console.log("row " + i);
       for (var j in data[i]) 
         {
          console.log(" " + data[i][j]);
         }
    }



     drawChart(data, productNameArr);
}

//Plot the chart using 2d array and product names as subtitles;
function drawChart(data, productNameArr) {

    var productNames = "";
    for(var i=0; i<productNameArr.length; i++) {
        productNames += productNameArr[i] + ",";
    }


    //Invoke google's built in method to get data table object required by google.
     var chartData = google.visualization.arrayToDataTable(data);

     var options = {
        title: 'Top reservation by date',

        'width':600,
        'height':650,
          chart: {
            title: 'Top reservation',
            subtitle: productNames,
          },
          bars: 'horizontal', // Required for Material Bar Charts.
          hAxis: {
            title: 'Count',
            minValue: 0
          },
          vAxis: {
            title: 'Date'
          }
        };

    var chart = new google.visualization.BarChart(document.getElementById('chart_div'));
    chart.draw(chartData, options);

}




