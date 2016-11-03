SA.Direction = function(name, ordinal) {
  this.name = name;
  this.ordinal = ordinal;
};

SA.Direction.prototype.getName = function() {
  return this.name;
};

SA.Direction.prototype.getOrdinal = function() {
  return this.ordinal;
};

SA.Direction.SOUTH_WEST = new SA.Direction('SOUTH_WEST', 0);
SA.Direction.WEST = new SA.Direction('WEST', 1);
SA.Direction.NORTH_WEST = new SA.Direction('NORTH_WEST', 2);
SA.Direction.NORTH = new SA.Direction('NORTH', 3);
SA.Direction.NORTH_EAST = new SA.Direction('NORTH_EAST', 4);
SA.Direction.EAST = new SA.Direction('EAST', 5);
SA.Direction.SOUTH_EAST = new SA.Direction('SOUTH_EAST', 6);
SA.Direction.SOUTH = new SA.Direction('SOUTH', 7);

SA.Direction.valueOf = function(name) {
  return SA.Direction[name.toUpperCase()];
};
