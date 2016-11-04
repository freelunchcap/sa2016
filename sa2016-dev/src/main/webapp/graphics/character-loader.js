SA.CharacterLoader = function() {};

SA.CharacterLoader._cache = {};

SA.CharacterLoader.load = function(id) {
  var cache = SA.CharacterLoader._cache;

  var character = cache[id];
  if(character == null) {
    var url = SA.Settings.API_BASE_URL + '/characters/' + id + '.json';
    character = new SA.RemoteResource(url);
    cache[id] = character;
  }
  return character;
};