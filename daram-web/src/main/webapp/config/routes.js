'use strict';

define([
		'app', //생성한 앵귤러 모듈에 루트를 등록하기 위해 임포트
		'config/route-config', //루트를 등록하는 routeConfig를 사용하기 위해 임포트
	],

	function (app, routeConfig) {
	
		return app.config(function ($routeProvider) {

			$routeProvider.when('/view1', routeConfig.config('../views/view1.html', 'controllers/controllers', {
				directives: [], 
				services: ['services/services'], 
				filters: []
			}));

			$routeProvider.otherwise({redirectTo:'/view1'});
		});
});
