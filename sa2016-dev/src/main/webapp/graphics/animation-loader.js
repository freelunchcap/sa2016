SA.AnimationLoader = function() {};

SA.AnimationLoader._cache = {};

SA.AnimationLoader.load = function(id) {
  var cache = SA.AnimationLoader._cache;
  
  var animation = cache[id];
  if(animation == null) {
    var url = SA.Settings.API_BASE_URL + '/animations/' + id + '.json';
    animation = new SA.RemoteResource(url);
    cache[id] = animation;
  }
  return animation;
};