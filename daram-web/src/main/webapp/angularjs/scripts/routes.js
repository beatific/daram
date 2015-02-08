'use strict';

define([
		'app', 
		'route-config'
	],

	function (app, routeConfig) {
	
		return app.config(function ($routeProvider) {

			$routeProvider.when('/view1', routeConfig.config('../views/view1.html', 'controllers/first', {
				directives: ['directives/version'], 
				services: [], 
				filters: ['filters/reverse']
			}));
			
			$routeProvider.when('/view2', routeConfig.config('../views/view2.html', 'controllers/second', {
				directives: ['directives/version'], 
				services: ['services/tester'], 
				filters: []
			}));
			
			$routeProvider.when('/grid', routeConfig.config('../views/grid.html', 'controllers/grid'));
			
			$routeProvider.when('/admin', routeConfig.config('../views/admin.html', 'controllers/third')); 

			$routeProvider.otherwise({redirectTo:'/view1'});
		});
});
