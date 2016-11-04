SA.doNothing = function() {};

SA.immediately = function(fn) {
  setTimeout(fn, 0);
};

SA.get = function(url) {
  var uid = PIXI.utils.uid().toString(16);
  SA.Loader.add(uid, url);
  return {
    success: function(fn) {
      SA.Loader.load(function(ignore, response) {
        fn(response[uid].data);
      });
    }
  }
};