'use strict';

define([
		'angular',
		'config/route-config',
	],
	
	function (angular, routeConfig) {
	
		var app = angular.module('daramApp', [], function ($provide, $compileProvider, $controllerProvider, $filterProvider) {
		
			routeConfig.setProvide($provide);
			routeConfig.setCompileProvider($compileProvider);
			routeConfig.setControllerProvider($controllerProvider);
			routeConfig.setFilterProvider($filterProvider);
		});

		app.controller('CommonController', function($scope) {
		
			 $scope.$on('updateCSS', function(event, args) {
				 
				$scope.stylesheets = args;
			});  
		});	
		
		return app; 
 	}
);
