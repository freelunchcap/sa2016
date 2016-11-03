SA.RemoteResource = function(url) {

  var self = this;

  self._ready = false;
  self._callbacks = [];
  self.then = registerCallback;

  SA.get(url).success(applyResponse);

  function registerCallback(fn) {
    if(self._ready) {
      SA.immediately(function() {
        fn(self);
      });
    } else {
      self._callbacks.push(fn);
    }
    return self;
  }

  function applyResponse(response) {
    Object.assign(self, response);
    self._ready = true;
    triggerCallbacks();
  }
  
  function triggerCallbacks() {
    self._callbacks.forEach(function(callback) {
      callback(self);
    });
  }

};