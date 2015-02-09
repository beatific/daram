 /*
	user strict 명령은 엄격하게 JavaScript 룰을 적용하라는 의미이다.
	일부 브라우저의 경우 use strict 명령을 통해 보다 빠르게 동작하는 경우도 존재하는 것 같다.
	잘못된 부분에 대한 검증도 보다 엄격하게 동작한다.
	하지만, 일부 라이브러리의 경우 use strict 명령을 사용하면 동작하지 않는 경우도 있으므로 주의해야 한다.
 */
'use strict';


requirejs.config({

	baseUrl:'app',

	paths:{

		'text': '../lib/require/text', //HTML 데이터를 가져올때 text! 프리픽스를 붙여준다.
		'jquery': '../lib/jquery/jquery',
		'jquery-ui': '../lib/jquery/jquery-ui-1.10.2.min',
		'angular': '../lib/angular/angular',
		'library': '../lib',
		'routes': '../config/routes',
		'config' : '../config',
		'ngRoute' : '../lib/angular/angular-route',
		
	},
	
/*
	shim:
	AMD 형식을 지원하지 않는 라이브러리의 경우 아래와 같이 SHIM을 사용해서 모듈로 불러올 수 있다.
	참고 : http://gregfranko.com/blog/require-dot-js-2-dot-0-shim-configuration/
*/
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
		'ngRoute' :{
			deps:['angular']
		}
	}
});


//requireJS를 활용하여 모듈 로드
requirejs( [
		'text', //미리 선언해둔 path, css나 html을 로드하기 위한 requireJS 플러그인
		'jquery', //미리 선언해둔 path, jQuery는 AMD를 지원하기 때문에 이렇게 로드해도 jQuery 또는 $로 호출할 수 있다.
		'angular', //미리 선언해둔 path
		'jquery-ui',
		'app', //app.js
		'routes', //routes.js
		'ngRoute',
	],

	//디펜던시 로드뒤 콜백함수
	function (text, $, angular) {
		//이 함수는 위에 명시된 모든 디펜던시들이 다 로드된 뒤에 호출된다.
		//주의해야할 것은, 디펜던시 로드 완료 시점이 페이지가 완전히 로드되기 전 일 수도 있다는 사실이다.
		
		
		//페이지가 완전히 로드된 뒤에 실행
		$(document).ready(function () {

			//위의 디펜던시 중 myApp이 포함된 app.js가 로드된 이후에 아래가 수행된다.
			//임의로 앵귤러 부트스트래핑을 수행한다.
			
			angular.bootstrap(document, ['daramApp']);
			
		});
		
	}
);
