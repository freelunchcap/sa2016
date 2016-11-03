function Animation() {
  PIXI.extras.MovieClip.call(this, [PIXI.Texture.EMPTY]);
}

SA.Animation = Animation;
SA.Animation.prototype = Object.create(PIXI.extras.MovieClip.prototype);

SA.Animation.load = function(id) {
  var data = SA.AnimationLoader.load(id);
  var animation = new SA.Animation();
  data.then(init);

  function init() {
    var offset = calculateOffset();
    var images = indexImages();
    addTextures(offset, images);
    animation.play();
  }

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

  function addTextures(offset, images) {
    animation.textures.pop();
    var frames = data.animation.frames;
    frames.forEach(function(frame) {
      var image = images[frame.image];
      animation.textures.push(makeTexture(offset, image));
    });
    var duration = data.animation.duration;
    animation.animationSpeed = frames.length / duration * (1000 / 60);
    animation.anchor.x = -offset.x;
    animation.anchor.y = -offset.y;
  }

  function makeTexture(offset, image) {
    var base = PIXI.BaseTexture.fromImage('data:image/png;base64,' + image.data);
    var frame = new PIXI.Rectangle(image.x - offset.x, image.y - offset.y, image.width, image.height);
    return new PIXI.Texture(base, null, null, frame);
  }

  return animation;
};