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

    $scope.character = SA.Character.load($state.params.id);
    $scope.character.on.ready(function() {
      // $scope.$apply();
    });

    $scope.character.x = 200;
    $scope.character.y = 150;

    stage.addChild($scope.character);
    animate();

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