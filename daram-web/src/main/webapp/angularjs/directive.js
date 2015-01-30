angular.module('appModule', [])
   .controller('Ctrl', function($scope) {
      $scope.items = [
          {title: 'What is Directive?',
           content: '특정한 행위의 기능을 가진 DOM엘리먼트.'},
          {title: 'Custom Directive',
           content: '디렉티브를 직접 생성해보십시오.'},
          {title: 'Bye~',
           content: '디렉티브 이야기를 마치겠습니다.'}
      ];
   })
   .directive('myTitle', function() {
       return {
          restrict: 'E',
          replace: true,
          transclude: true,
          template: '<div ng-transclude></div>',
          controller: function() {
             var items = [];
             this.addItem = function(item) {
                items.push(item);
             }
         }
      };
   })
   .directive('myContent', function(){
       return {
           restrict: 'E',
           replace: true,
           transclude: true,
           require: '^?myTitle',
           scope: { title:'=itemTitle' },
           template : '<div>' +
                      '<div class="title" ng-click="click()">{{title}}</div>' +
                      '<div class="body" ng-show="showMe" ng-transclude></div>' +
                      '</div>',
           link: function(scope, element, attrs, controller) {
               scope.showMe = false;
               controller.addItem(scope);
               scope.click = function click(){
                  scope.showMe = !scope.showMe;
               }
           }
       };
   });