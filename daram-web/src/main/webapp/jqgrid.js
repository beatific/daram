// Code goes here

var myApp = angular.module('myApp', []);

myApp.directive('ngJqGrid', function () {
    return {
      restrict: 'E',
      scope: {
        config: '=',
        data: '=',
      },
      link: function (scope, element, attrs) {
        var table;
        
        scope.$watch('config', function (newValue) {
          element.children().empty();
          table = angular.element('<table></table>');
          element.append(table);
          $(table).jqGrid(newValue);
        });
        
        scope.$watch('data', function (newValue, oldValue) {
          var i;
          for (i = oldValue.length - 1; i >= 0; i--) {
            $(table).jqGrid('delRowData', i);
          }
          for (i = 0; i < newValue.length; i++) {
            $(table).jqGrid('addRowData', i, newValue[i]);
          }
        });
      }
    };
  });
  
myApp.controller('MyController', function ($scope) {
	
  var selected = [];
  
  $scope.config = {
  	datatype: "local",
  	height: 250,
     	colNames:['Inv No','Date', 'Client', 'Amount','Tax','Total','Notes'],
     	colModel:[
     		{name:'id',index:'id', width:60, sorttype:"int"},
     		{name:'invdate',index:'invdate', width:90, sorttype:"date"},
     		{name:'name',index:'name', width:100},
     		{name:'amount',index:'amount', width:80, align:"right",sorttype:"float"},
     		{name:'tax',index:'tax', width:80, align:"right",sorttype:"float"},		
     		{name:'total',index:'total', width:80,align:"right",sorttype:"float"},		
     		{name:'note',index:'note', width:150, sortable:false}		
     	],
     	multiselect: true,
     	caption: "Manipulating Array Data",
     	onSelectRow : 
     		function(id) {
	     		if(selected.indexOf(id) < 0)selected.push(id);
	     		else selected.splice(selected.indexOf(id), 1);
     		},
        onSelectAll : 
        	function(ids, status) {
        	    
	        	for(id in ids)  {
	        		if(status && selected.indexOf(id) < 0)selected.push(id);
	        		if(!status && selected.indexOf(id) >= 0)selected.splice(selected.indexOf(id), 1);
	        	}
        	}
    };
    
  $scope.data = [
		{id:"1",invdate:"2007-10-01",name:"test",note:"note",amount:"200.00",tax:"10.00",total:"210.00"},
		{id:"2",invdate:"2007-10-02",name:"test2",note:"note2",amount:"300.00",tax:"20.00",total:"320.00"},
		{id:"3",invdate:"2007-09-01",name:"test3",note:"note3",amount:"400.00",tax:"30.00",total:"430.00"},
		{id:"4",invdate:"2007-10-04",name:"test",note:"note",amount:"200.00",tax:"10.00",total:"210.00"},
		{id:"5",invdate:"2007-10-05",name:"test2",note:"note2",amount:"300.00",tax:"20.00",total:"320.00"},
		{id:"6",invdate:"2007-09-06",name:"test3",note:"note3",amount:"400.00",tax:"30.00",total:"430.00"},
		{id:"7",invdate:"2007-10-04",name:"test",note:"note",amount:"200.00",tax:"10.00",total:"210.00"},
		{id:"8",invdate:"2007-10-03",name:"test2",note:"note2",amount:"300.00",tax:"20.00",total:"320.00"},
		{id:"9",invdate:"2007-09-01",name:"test3",note:"note3",amount:"400.00",tax:"30.00",total:"430.00"}
		];
  
  $scope.deleteRows = function() {
	  alert(selected);
		  for(id in selected) {
			  alert(selected[id]);
		      $scope.data.splice(selected[id], 1);
		  }
		  alert($scope.data);
		  selected = [];
	  }
});