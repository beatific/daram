angular.module('exampleDirective', [])
  .controller('Ctrl', function($scope) {
    $scope.person = {
      name: 'nextreeMember',
      address: 'Gasan'
    };
  })
  .directive('myExample', function() {
    return {
      restrict: 'E',
      template: '<div>Hello AngularJS</div>',
      replace: true
    };
});