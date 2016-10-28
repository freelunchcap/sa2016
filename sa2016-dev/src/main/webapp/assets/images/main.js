APP.controller('ImagesCtrl', function($scope, $state, GRID, ImagesAPI) {

  $scope.grid = GRID.make(ImagesAPI, $state.params.id, onSelect);

  $scope.grid.columnDefs = [
    {name: 'id', title: 'ID'},
    {name: 'width', title: 'Width'},
    {name: 'height', title: 'Height'}
  ];

  function onSelect(item) {
    $state.go('assets.images.canvas', {
      id: item.id,
      format: item.format
    });
  }

});