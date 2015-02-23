   google.load('visualization', '1', {packages: ['corechart']});

    function drawChart(data, division, xCaption, yCaption, denomination) {

    	draw(data, division, xCaption, yCaption, denomination, 800, 350);

    }
    
    function drawSmallChart(data, division, xCaption, yCaption, denomination) {
    	draw(data, division, xCaption, yCaption, denomination, 500, 435);
    }
    
    function drawHalfSmallChart(data, division, xCaption, yCaption, denomination) {
    	draw(data, division, xCaption, yCaption, denomination, 500, 175);
    }
    
    function draw(data, division, xCaption, yCaption, denomination, width, height) {

        var table = new google.visualization.DataTable();
        
        for(var index in data) 
      	    for (var i in data[index])
      		    if(i == 0 && !(data[index][i] instanceof Date))data[index][i] = new Date(data[index][i]);
        
        table.addColumn('date', 'time');
        
        if(Array.isArray(yCaption)) {
	        for(var index in yCaption)
	        	table.addColumn('number', yCaption[index]);
        } else {
            table.addColumn('number', yCaption);
        }

        table.addRows(data);

        var dataView = new google.visualization.DataView(table);
        dataView.setColumns([{calc: function(d, row) { return d.getFormattedValue(row, 0); }, type:'string'}, 1]);
        
        var options = {
          width: width,
          height: height,
          hAxis: {
            title: xCaption
          },
          vAxis: {
            title: denomination,
          }
        };
        
        var chart = new google.visualization.LineChart(
          document.getElementById(division));

        chart.draw(table, options);

      }
    
      