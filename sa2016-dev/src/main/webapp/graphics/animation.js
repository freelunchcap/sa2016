function Animation(data) {
  this.data = data;
}

Animation.prototype.render = function() {

  var data = this.data;
  var offset = calculateOffset();
  var images = indexImages();
  return makeMovieClip();

  function calculateOffset() {
    var ret = {x: 0, y: 0};
    data.images.forEach(function(image) {
      ret.x = Math.min(ret.x, image.x);
      ret.y = Math.min(ret.y, image.y);
    });
    return ret;
  }

  function indexImages() {
    var ret = {};
    data.images.forEach(function(image) {
      ret[image.id] = image;
    });
    return ret;
  }

  function makeMovieClip() {
    var textures = [];
    var frames = data.animation.frames;

    frames.forEach(function(frame) {
      var image = images[frame.image];
      textures.push(makeTexture(image));
    });
    var clip = new PIXI.extras.MovieClip(textures);
    var duration = data.animation.duration;
    clip.animationSpeed = frames.length / duration * (1000 / 60);
    clip.anchor.x = -offset.x;
    clip.anchor.y = -offset.y;
    return clip;
  }

  function makeTexture(image) {
    var base = PIXI.BaseTexture.fromImage('data:image/png;base64,' + image.data);
    var frame = new PIXI.Rectangle(image.x - offset.x, image.y - offset.y, image.width, image.height);
    return new PIXI.Texture(base, null, null, frame);
  }


};