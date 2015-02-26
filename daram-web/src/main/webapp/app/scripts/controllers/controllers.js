
angular.module('daramApp').controller('DaramController', ['$scope', '$http', '$location', 'DaramService',function($scope, $http, $location, DaramService) {
	
	var _contextPath = $location.absUrl().substr(0, $location.absUrl().lastIndexOf("/"));
	
	$scope.time = {};
	$scope.designs = [];
	$scope.selected = [];
	$scope.data ={};

	$scope.getGraphs = function() {
		$http({
			method : 'POST',
			url : _contextPath + '/monitor/graph',
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
			url : _contextPath + '/monitor/design',
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

angular.module('daramApp').controller('JstatController', ['$scope', '$http', '$location', 'JstatService',function($scope, $http, $location, JstatService) {
	
	var _contextPath = $location.absUrl().substr(0, $location.absUrl().lastIndexOf("/"));
	
	$scope.time = {};
	$scope.designs = [];
	$scope.data ={};
	$scope.selectedServer;

	$scope.getGraphs = function() {
		$http({
			method : 'POST',
			url : _contextPath + '/jstat/graph',
			headers : {
				'Content-Type' : 'application/json; charset=UTF-8'
			},
			data : {
				'fromTime' : $scope.time.from,
				'toTime' : $scope.time.to,
				'server' :$scope.selectedServer.server
			}
		}).success(
				function(data) {
					$scope.data = jQuery.extend(true, {}, data);
					for (var index =0; index < data.monitor.length; index++) {
						$scope.drawGraphs(data.monitor[index]);
					}
				});
	}
	
	$scope.drawGraphs = function(monitor) {
			if(monitor.name == "Survivor0" || monitor.name == "Survivor1") {
				draw(monitor.graphs,
						monitor.name,
						monitor.xTag,
						monitor.yTag,
						monitor.denomination,
						500,
						175);
			} else if(monitor.name == "GCC" || monitor.name == "GCT") {
				draw(monitor.graphs,
						monitor.name,
						monitor.xTag,
						monitor.yTag,
						monitor.denomination,
						772,
						350);
			} else {
				draw(monitor.graphs,
						monitor.name,
						monitor.xTag,
						monitor.yTag,
						monitor.denomination,
						500,
						435);
			}

	}

	JstatService.receive().then(null, null, function(list) {
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
			  
			  $scope.drawGraphs(data.monitor[index]);
		  }
			
	  });
	
	$scope.init = function() {
		$http({
			method : 'POST',
			url : _contextPath + '/jstat/server',
			headers : {
				'Content-Type' : 'application/json; charset=UTF-8'
			},
			data : {}
		}).success(function(data) {
			$scope.servers = data.servers;
			for (var i =0; i < data.servers.length; i++) 
				if(data.servers[i].selectYn == "Y") {
					$scope.selectedServer = data.servers[i];
					$scope.getGraphs();
					break;
				}
			
			
		});
	};
	
	$scope.select = function(server) {
		
		$scope.selectedServer = server;
		
	    JstatService.send(server);
		
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