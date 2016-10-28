APP.controller('CharactersCtrl', function($scope, $state, GRID, CharactersAPI) {

  $scope.grid = GRID.make(CharactersAPI, $state.params.id, select);

  $scope.grid.columnDefs = [
    {name: 'id', title: 'ID'}
  ];

  function select(item) {
    $state.go('assets.characters.canvas', {
      id: item.id
    });
  }

});