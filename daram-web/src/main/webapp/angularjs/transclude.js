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
      template: '<div>Name: {{person.name}} </br> Address: {{person.address}} </br> <span ng-transclude></div>',
      transclude : true
    };
});