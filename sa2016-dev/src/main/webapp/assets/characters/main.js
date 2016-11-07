APP.controller('CharactersCtrl', function($scope, $state, GRID, CharactersAPI) {

  $scope.grid = GRID.make(CharactersAPI, $state.params.id, select);

  $scope.grid.columnDefs = [
    {name: 'id', title: 'ID'}
  ];

  function select(item) {
    const target = 'assets.characters.canvas';
    const params = {id: item.id};
    if($state.current.name == target) {
      $state.transitionTo(target, params, {notify: false});
      $scope.$emit('hihihi', {});
    } else {
      $state.go(target, params);
    }
  }

});