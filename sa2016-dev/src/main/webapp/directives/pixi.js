'use strict';

APP.directive('pixi', function() {
  return {
    restrict: 'A',
    scope: {
      pixi: '=',
      width: '@',
      height: '@'
    },
    controller: function postLink($scope, $element) {
      var options = {view: $element[0]};
      angular.extend(options, $scope.pixi);
      $scope.pixi.renderer = new PIXI.WebGLRenderer($scope.width, $scope.height, options);
    }
  };
});