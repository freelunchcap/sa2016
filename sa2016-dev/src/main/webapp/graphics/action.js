SA.Action = function(name, ordinal) {
  this._name = name;
  this._ordinal = ordinal;
};

SA.Action.prototype.name = function() {
  return this._name;
};

SA.Action.prototype.ordinal = function() {
  return this._ordinal;
};

SA.Action.prototype.toString = function() {
  return this.name();
};

SA.Action.ATTACK = new SA.Action('ATTACK', 0);
SA.Action.INJURED = new SA.Action('INJURED', 1);
SA.Action.FAINT = new SA.Action('FAINT', 2);
SA.Action.STAND = new SA.Action('STAND', 3);
SA.Action.WALK = new SA.Action('WALK', 4);
SA.Action.SIT = new SA.Action('SIT', 5);
SA.Action.WAVE = new SA.Action('WAVE', 6);
SA.Action.HAPPY = new SA.Action('HAPPY', 7);
SA.Action.ANGRY = new SA.Action('ANGRY', 8);
SA.Action.SAD = new SA.Action('SAD', 9);
SA.Action.DEFENSE = new SA.Action('DEFENSE', 10);
SA.Action.NOD = new SA.Action('NOD', 11);
SA.Action.TOSS = new SA.Action('TOSS', 12);

SA.Action.valueOf = function(name) {
  return SA.Action[name];
};
