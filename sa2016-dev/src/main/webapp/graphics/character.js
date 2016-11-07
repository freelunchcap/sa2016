SA.Character = function(data, action, direction) {
  var self = this;

  self._data = data;
  self.action(action);
  self.direction(direction);
  self._animation = {current: null, cache: {}};
  PIXI.Container.call(this);

  self._callbacks = {
    ready: []
  };

  self.on = {
    ready: function(fn) {
     self._callbacks.ready.push(fn);
    }
  }

};
SA.Character.prototype = Object.create(PIXI.Container.prototype);

SA.Character.load = function(id, action, direction) {
  var data = SA.CharacterLoader.load(id);
  var ret = new SA.Character(data, action, direction);
  data.then(function() {
    ret._check();
    ret._init()
  });
  return ret;
};

SA.Character.prototype.actions = function() {
  var animations = this._data.animations || {};
  return Object.keys(animations);
};

SA.Character.prototype.action = function(action) {
  if(action != null) {
    action = SA.Action.valueOf(action);
    if(this._action != action) {
      this._action = action;
      this._init();
    }
  }
  return this._action;
};

SA.Character.prototype.directions = function() {
  var animations = this._data.animations || {};
  var directions = animations[this._action] || {};
  return Object.keys(directions);
};

SA.Character.prototype.direction = function(direction) {
  if(direction != null) {
    direction = SA.Direction.valueOf(direction);
    if(this._direction != direction) {
      this._direction = direction;
      this._init();
    }
  }
  return this._direction;
};

SA.Character.prototype._check = function() {
  var lookup = this._data.animations;
  if(this._action == null || lookup[this._action] == null)
    this._action = SA.Action.valueOf(this.actions()[0]);
  if(this._direction == null || lookup[this._action][this._direction] == null)
    this._direction = SA.Direction.valueOf(this.directions()[0]);
};

SA.Character.prototype._init = function() {
  var data = this._data;
  if(!data._ready) return;
  this.removeChildren();
  this._animation.current = data.animations[this._action][this._direction];
  var animation = this._animation;
  var child = animation.cache[this._animation.current];
  if(child == null) {
    child = animation.cache[this._animation.current] = SA.Animation.load(this._animation.current);
  }
  this.addChild(child);
  this._callbacks.ready.forEach(function(fn) {fn(this)});
};