APP.controller('CharacterCanvasCtrl', function($scope, $state) {

  $scope.pixi = {};
  $scope.character = null;
  $scope.setAction = setAction;
  $scope.setDirection = setDirection;
  
  $scope.$watch('pixi.renderer', function(renderer) {
    render(renderer);
  });

  function render(renderer) {
    var stage = new PIXI.Container();
    animate();
    $scope.$watch(function() {return $state.params}, function(params) {
      loadCharacter(params.id)
    });

    function loadCharacter(id) {
      stage.removeChildren();
      $scope.character = SA.Character.load(id);
      $scope.character.x = 200;
      $scope.character.y = 150;
      stage.addChild($scope.character);
    }

    function animate() {
      requestAnimationFrame(animate);
      renderer.render(stage);
    }
  }

  function setAction(action) {
    $scope.character.action(action);
  }

  function setDirection(direction) {
    $scope.character.direction(direction);
  }

});