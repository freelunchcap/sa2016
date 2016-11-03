SA.AnimationLoader = function() {};

SA.AnimationLoader._loader = new PIXI.loaders.Loader();

SA.AnimationLoader._cache = {};

SA.AnimationLoader.load = function(id) {
  var loader = SA.AnimationLoader._loader;
  var cache = SA.AnimationLoader._cache;
  
  var animation = cache[id];
  if(animation == null) {
    var url = SA.Settings.API_BASE_URL + '/animations/' + id;
    animation = new SA.RemoteResource(url, loader);
    cache[id] = animation;
  }
  return animation;
};