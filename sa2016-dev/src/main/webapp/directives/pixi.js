'use strict';

APP.directive('pixi', function ($parse) {
  return {
    restrict: 'A',
    scope: false,
    controller: function postLink($scope, $element, $attrs) {
      var self = this;
      var renderer = new PIXI.WebGLRenderer(800, 600, {
        view: $element[0]
      });

      var stage = new PIXI.Container();

      var bunny = null;

      PIXI.loader.add('bunny', '/api/animations/Q0v-8-0').load(function (loader, resources) {
        var image = JSON.parse(resources.bunny.data).images['2Sm'];
        var url = 'data:image/png;base64,' + image.data;
        var texture = PIXI.Texture.fromImage(url);

        // This creates a texture from a 'bunny.png' image.
        bunny = new PIXI.Sprite(texture);

        // Setup the position and scale of the bunny
        bunny.position.x = 400;
        bunny.position.y = 300;

        bunny.scale.x = 2;
        bunny.scale.y = 2;

        // Add the bunny to the scene we are building.
        stage.addChild(bunny);

        // kick off the animation loop (defined below)
        animate();
      });

      function animate() {
        // start the timer for the next animation loop
        requestAnimationFrame(animate);

        // each frame we spin the bunny around a bit
        bunny.rotation += 0.01;

        // this is the main render call that makes pixi draw your container and its children.
        renderer.render(stage);
      }
    }
  };
});