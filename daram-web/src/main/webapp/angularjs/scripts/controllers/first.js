'use strict';

define(['jquery-ui'], function () {

	//컨트롤러 선언
	function _controller($scope) {
	
		//CSS 설정
		$scope.$emit('updateCSS', ['css/ng-grid.css', 'css/style.css']);
		
	    $scope.myData = [{name: "Moroni", age: 50},
	                     {name: "Tiancum", age: 43},
	                     {name: "Jacob", age: 27},
	                     {name: "Nephi", age: 29},
	                     {name: "Enos", age: 34}];
	    $scope.gridOptions = { data: 'myData' };
	}

	//생성한 컨트롤러 리턴
	return _controller;
});

