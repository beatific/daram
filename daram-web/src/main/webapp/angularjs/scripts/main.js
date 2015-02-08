'use strict';

requirejs.config({

	baseUrl:'js',
	paths:{
		'text': '../lib/require/text', 
		'jquery': '../lib/jquery/jquery',
		'jquery-ui': '../lib/jquery/jquery-ui-1.10.2.min',
		'angular': '../lib/angular/angular',
		'library': '../lib',
		'config': '../config',
	},

	shim:{
		'angular':{
			deps:['jquery'],
			exports:'angular'
		},
		'jquery-ui': {
			deps: ['jquery'] 
		},
		'app':{
			deps:['angular']
		},
		'routes':{
			deps:['angular']
		},
	}
});

requirejs( [
		'text', 
		'jquery', 
		'angular', 
		'jquery-ui',
		'app',
		'routes',
	],

	function (text, $, angular, ngGrid) {
		
		$(document).ready(function () {

			angular.bootstrap(document, ['daramApp']);
			
		});
		
	}
);
