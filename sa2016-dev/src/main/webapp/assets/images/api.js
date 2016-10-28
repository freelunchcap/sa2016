APP.service('ImagesAPI', function(API) {

  const END_POINT = '/images';

  return {
    list: API.list(END_POINT)
  }

});