APP.service('CharactersAPI', function(API) {

  const END_POINT = '/characters';

  return {
    list: API.list(END_POINT)
  }

});