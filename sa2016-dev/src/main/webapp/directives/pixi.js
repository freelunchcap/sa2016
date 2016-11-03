'use strict';

APP.directive('pixi', function ($parse) {
  return {
    restrict: 'A',
    scope: {
      width: '@',
      height: '@'
    },
    controller: function postLink($scope, $element, $attrs) {
      var renderer = new PIXI.WebGLRenderer($scope.width, $scope.height, {
        view: $element[0]
      });

      var stage = new PIXI.Container();
      
      var animation = SA.Animation.load('Q0v-0-0');

      animation.x = 200;
      animation.y = 150;

      stage.addChild(animation);
      animate();

      function animate() {
        // start the timer for the next animation loop
        requestAnimationFrame(animate);

        // each frame we spin the bunny around a bit
        // bunny.rotation += 0.01;

        // this is the main render call that makes pixi draw your container and its children.
        renderer.render(stage);
      }

      // PIXI.loader.add('bunny', '/api/animations/Q0v-0-0').load(function (loader, resources) {
      //   console.log(loader);
      //   var data = JSON.parse(resources.bunny.data);
      //   // var image = data.images['2RM'];
      //   // var url = 'data:image/png;base64,' + image.data;
      //   // var texture = PIXI.Texture.fromImage(url);
      //
      //   // This creates a texture from a 'bunny.png' image.
      //   bunny = .render();
      //   bunny.play();
      //
      //   // Setup the position and scale of the bunny
      //   bunny.position.x = 200;
      //   bunny.position.y = 150;
      //
      //   // Add the bunny to the scene we are building.
      //   stage.addChild(bunny);
      //
      //   // kick off the animation loop (defined below)
      //   animate();
      // });

      
    }
  };
});