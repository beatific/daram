angular.module('ui.bootstrap.demo').controller('MyController', function ($scope, $http) {

	$scope.checkModel = {
    left: false,
    middle: true,
    right: false
  };
  
  $scope.designs = [];
  
  $scope.$watch('$viewContentLoaded', function(){
	  
  	$http({
    	     method: 'POST',
   			 url: 'http://localhost:8081/daram-web/monitor/design',
   			 headers: {
   			   'Content-Type': 'application/json; charset=UTF-8'
   			 },
   			 data : {}}
    			).success(function(data) {
    				$scope.SomeData = data.designs; 
    			
    		});
   });
});