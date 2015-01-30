   google.load('visualization', '1', {packages: ['corechart']});

    function drawChart(data, division, xCaption, yCaption, denomination) {

      var table = new google.visualization.DataTable();
      
      for(var index in data) 
    	for (var i in data[index])
    		if(i == 0)data[index][i] = new Date(data[index][i]);
      
      table.addColumn('date', 'time');
      table.addColumn('number', yCaption);

      table.addRows(data);

      var options = {
        width: 800,
        height: 350,
        hAxis: {
          title: xCaption
        },
        vAxis: {
          title: denomination
        }
      };
      
      var chart = new google.visualization.LineChart(
        document.getElementById(division));

      chart.draw(table, options);

    }
      