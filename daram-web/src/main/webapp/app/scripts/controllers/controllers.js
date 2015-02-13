angular.module('daramApp').controller('DaramController', ['$scope', '$http', 'DaramService',function($scope, $http, DaramService) {
	$scope.time = {};
	$scope.designs = [];
	$scope.selected = [];
	$scope.data ={};

	$scope.getGraphs = function() {
		$http({
			method : 'POST',
			url : 'http://localhost:8081/daram-web/monitor/graph',
			headers : {
				'Content-Type' : 'application/json; charset=UTF-8'
			},
			data : {
				'fromTime' : $scope.time.from,
				'toTime' : $scope.time.to,
				'designs' : $scope.selected
			}
		}).success(
				function(data) {
					$scope.data = jQuery.extend(true, {}, data);

					for (var index =0; index < data.monitor.length; index++) {
						drawChart(data.monitor[index].graphs,
								data.monitor[index].name,
								data.monitor[index].xTag,
								data.monitor[index].yTag,
								data.monitor[index].denomination);
					}

				});
	}

	DaramService.receive().then(null, null, function(list) {
		if(!$scope.live)return;
		
		var data = jQuery.extend(true, {}, $scope.data);
		  for (var index =0; index < data.monitor.length; index++) {
			  
			  if(data.monitor[index].graphs.length >= 60)
			      data.monitor[index].graphs.shift();
			  
			  for (var i =0; i < list.length; i++) 
				  if(list[i].name == data.monitor[index].name) {
					  
					  data.monitor[index].graphs.push(list[i].graph);
					  break;
				  }
			  
			  $scope.data = jQuery.extend(true, {}, data);
			  
			  drawChart(data.monitor[index].graphs,
					  data.monitor[index].name,
					  data.monitor[index].xTag,
					  data.monitor[index].yTag,
					  data.monitor[index].denomination);
		  }
			
	  });
	
	$scope.init = function() {
		
		$http({
			method : 'POST',
			url : 'http://localhost:8081/daram-web/monitor/design',
			headers : {
				'Content-Type' : 'application/json; charset=UTF-8'
			},
			data : {}
		}).success(function(data) {
			$scope.designs = data.designs;
			for (var i =0; i < data.designs.length; i++) 
				if(data.designs[i].isSelected)
					$scope.select(data.designs[i], false);
			
		});
	};

	$scope.select = function(design, isRegister) {
		if ($scope.selected.indexOf(design) < 0)
			$scope.selected.push(design);
		else
			$scope.selected.splice($scope.selected.indexOf(design), 1);
		
	    console.log($scope.selected);
	    if(isRegister)DaramService.send($scope.selected);
		
	    if($scope.live)$scope.getGraphForOneHour();
	    else $scope.getGraphs();
	    
	};
	
	$scope.setLive = function() {
		if($scope.live)$scope.getGraphForOneHour();
	}
	
	$scope.getGraphForOneHour = function() {
		$scope.time.from = '';
		$scope.time.to = ''; 
		$scope.getGraphs();
	}
	
}]);

angular.module('daramApp').controller('JstatController', ['$scope', '$http', 'DaramService',function($scope, $http, DaramService) {
	$scope.time = {};
	$scope.designs = [];
	$scope.selected = [];
	$scope.data ={};

	$scope.getGraphs = function() {
		$http({
			method : 'POST',
			url : 'http://localhost:8081/daram-web/monitor/graph',
			headers : {
				'Content-Type' : 'application/json; charset=UTF-8'
			},
			data : {
				'fromTime' : $scope.time.from,
				'toTime' : $scope.time.to,
				'designs' : $scope.selected
			}
		}).success(
				function(data) {
					$scope.data = jQuery.extend(true, {}, data);

					for (var index =0; index < data.monitor.length; index++) {
						drawChart(data.monitor[index].graphs,
								data.monitor[index].name,
								data.monitor[index].xTag,
								data.monitor[index].yTag,
								data.monitor[index].denomination);
					}

				});
	}

	DaramService.receive().then(null, null, function(list) {
		if(!$scope.live)return;
		
		var data = jQuery.extend(true, {}, $scope.data);
		  for (var index =0; index < data.monitor.length; index++) {
			  
			  if(data.monitor[index].graphs.length >= 60)
			      data.monitor[index].graphs.shift();
			  
			  for (var i =0; i < list.length; i++) 
				  if(list[i].name == data.monitor[index].name) {
					  
					  data.monitor[index].graphs.push(list[i].graph);
					  break;
				  }
			  
			  $scope.data = jQuery.extend(true, {}, data);
			  
			  drawChart(data.monitor[index].graphs,
					  data.monitor[index].name,
					  data.monitor[index].xTag,
					  data.monitor[index].yTag,
					  data.monitor[index].denomination);
		  }
			
	  });
	
	$scope.init = function() {
		
		$http({
			method : 'POST',
			url : 'http://localhost:8081/daram-web/monitor/design',
			headers : {
				'Content-Type' : 'application/json; charset=UTF-8'
			},
			data : {}
		}).success(function(data) {
			$scope.designs = data.designs;
			for (var i =0; i < data.designs.length; i++) 
				if(data.designs[i].isSelected)
					$scope.select(data.designs[i], false);
			
		});
	};

	$scope.select = function(design, isRegister) {
		if ($scope.selected.indexOf(design) < 0)
			$scope.selected.push(design);
		else
			$scope.selected.splice($scope.selected.indexOf(design), 1);
		
	    console.log($scope.selected);
	    if(isRegister)DaramService.send($scope.selected);
		
	    if($scope.live)$scope.getGraphForOneHour();
	    else $scope.getGraphs();
	    
	};
	
	$scope.setLive = function() {
		if($scope.live)$scope.getGraphForOneHour();
	}
	
	$scope.getGraphForOneHour = function() {
		$scope.time.from = '';
		$scope.time.to = ''; 
		$scope.getGraphs();
	}
	
}]);