var app = angular.module('app', []);

  app.controller("MyController", function($scope, $http) {
    
   $scope.designs = [];
   $scope.selected = [];
    
   $scope.init = function(){
    	$http({
      	     method: 'POST',
     			 url: 'http://localhost:8081/daram-web/monitor/design',
     			 headers: {
     			   'Content-Type': 'application/json; charset=UTF-8'
     			 },
     			 data : {}}
      			).success(function(data) {
      				$scope.designs = data.designs; 
      			
      		});
     };
     
     $scope.select = function(design) {
    	 $scope.selected.push(design);
     };
  });