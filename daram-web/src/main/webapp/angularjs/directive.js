angular.module('appModule', [])
   .controller('Ctrl', function($scope) {
      $scope.items = [
          {title: 'What is Directive?',
           content: 'Ư���� ������ ����� ���� DOM������Ʈ.'},
          {title: 'Custom Directive',
           content: '��Ƽ�긦 ���� �����غ��ʽÿ�.'},
          {title: 'Bye~',
           content: '��Ƽ�� �̾߱⸦ ��ġ�ڽ��ϴ�.'}
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