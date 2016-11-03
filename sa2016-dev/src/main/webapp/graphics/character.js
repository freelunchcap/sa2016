SA.Character = function(data, action, direction) {
  this._data = data;
  this.action = action;
  this.direction = direction;
  PIXI.Container.call(this);
};
SA.Character.prototype = Object.create(PIXI.Container.prototype);

SA.Character.prototype.actions = function() {
  var animations = this._data.animations || {};
  return Object.keys(animations);
};

SA.Character.prototype.action = function(action) {
  this.action = SA.Action.valueOf(action);
  this._init();
};

SA.Character.prototype.directions = function() {
  var animations = this._data.animations || {};
  var directions = animations[this.action] || {};
  return Object.keys(directions);
};

SA.Character.prototype.direction = function(direction) {
  this.direction = SA.Direction.valueOf(direction);
  this._init();
};

SA.Character.prototype._init = function() {

};