APP.controller('ImageCanvasCtrl', function($scope, $state) {
  
  $scope.url = '/api/images/' + $state.params.id + '.' + $state.params.format;
  
});