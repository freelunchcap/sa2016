'use strict';

APP.directive('pixi', function ($parse) {
  return {
    restrict: 'A',
    scope: {
      width: '@',
      height: '@'
    },
    controller: function postLink($scope, $element, $attrs) {
      var self = this;
      var renderer = new PIXI.WebGLRenderer($scope.width, $scope.height, {
        view: $element[0]
      });

      var stage = new PIXI.Container();

      var bunny = null;

      function prepareAnimation(data) {
        var offset = calculateOffset(data);
        var clip = makeMovieClip(offset, data);
        clip.play();
        return clip;
      }

      function calculateOffset(data) {
        var ret = {x: 0, y: 0};
        angular.forEach(data.images, function(image) {
          ret.x = Math.min(ret.x, image.x);
          ret.y = Math.min(ret.y, image.y);
        });
        return ret;
      }

      function makeMovieClip(offset, data) {
        var textures = [];
        var frames = data.animation.frames;

        angular.forEach(frames, function(frame) {
          var imageId = frame.image;
          var image = data.images[imageId];
          textures.push(makeTexture(offset, image));
        });
        var clip = new PIXI.extras.MovieClip(textures);
        var duration = data.animation.duration;
        clip.animationSpeed = frames.length / duration * (1000 / 60 * 2);
        clip.anchor.x = -offset.x;
        clip.anchor.y = -offset.y;
        return clip;
      }

      function makeTexture(offset, image) {
        var base = PIXI.BaseTexture.fromImage('data:image/png;base64,' + image.data);
        var frame = new PIXI.Rectangle(image.x - offset.x, image.y - offset.y, image.width, image.height);
        return new PIXI.Texture(base, null, null, frame);
      }

      PIXI.loader.add('bunny', '/api/animations/Q0v-0-0').load(function (loader, resources) {
        var data = JSON.parse(resources.bunny.data);
        // var image = data.images['2RM'];
        // var url = 'data:image/png;base64,' + image.data;
        // var texture = PIXI.Texture.fromImage(url);

        // This creates a texture from a 'bunny.png' image.
        bunny = prepareAnimation(data);
        // bunny.play();

        // Setup the position and scale of the bunny
        bunny.position.x = 200;
        bunny.position.y = 150;

        // Add the bunny to the scene we are building.
        stage.addChild(bunny);

        // kick off the animation loop (defined below)
        animate();
      });

      function animate() {
        // start the timer for the next animation loop
        requestAnimationFrame(animate);

        // each frame we spin the bunny around a bit
        // bunny.rotation += 0.01;

        // this is the main render call that makes pixi draw your container and its children.
        renderer.render(stage);
      }
    }
  };
});