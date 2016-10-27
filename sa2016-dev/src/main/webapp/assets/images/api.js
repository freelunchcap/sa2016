APP.service('ImagesAPI', function($http) {

  const END_POINT = '/api/images';

  function list(start, dir, max) {
    var url = END_POINT + '/list';
    var params = [];
    if(start != null) params.push('start=' + start);
    if(dir != null) params.push('dir=' + dir);
    if(max != null) params.push('max=' + max);
    if(params.length > 0) url += '?' + params.join('&');
    return $http.get(url);
  }
  
  return {
    list: list
  }

});