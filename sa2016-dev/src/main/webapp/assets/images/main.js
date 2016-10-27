APP.controller('ImagesCtrl', function($scope, ImagesAPI) {

  const PAGE_SIZE = 100;

  $scope.grid = makeGrid();
  
  var top = false;
  var bottom = false;

  function makeGrid() {
    return {
      infiniteScrollRowsFromEnd: PAGE_SIZE / 2,
      infiniteScrollUp: true,
      infiniteScrollDown: true,
      columnDefs: [
        {name: 'id', title: 'ID'},
        {name: 'width', title: 'Width'},
        {name: 'height', title: 'Height'}
      ],
      data: [],
      onRegisterApi: function(api) {
        angular.extend($scope.grid, api);
        api.infiniteScroll.on.needLoadMoreData($scope, scrollDown);
        api.infiniteScroll.on.needLoadMoreDataTop($scope, scrollUp);
      }
    };
  }

  function scrollDown() {
    var data = $scope.grid.data;
    var last = data[data.length - 1];
    ImagesAPI.list(last.id, 'gt', PAGE_SIZE)
      .success(function(after) {
        $scope.grid.data = data.concat(after);
        bottom |= after.length < PAGE_SIZE;
        $scope.grid.infiniteScroll.dataLoaded(!top, !bottom);
      });
  }

  function scrollUp() {
    var data = $scope.grid.data;
    var first = data[0];
    ImagesAPI.list(first.id, 'lt', PAGE_SIZE)
      .success(function(before) {
        $scope.grid.data = before.concat(data);
        top |= before.length < PAGE_SIZE;
        $scope.grid.infiniteScroll.dataLoaded(!top, !bottom);
      });
  }

  function initData() {
    return ImagesAPI.list(null, 'gt', PAGE_SIZE)
      .success(function(data) {
        $scope.grid.data = $scope.grid.data.concat(data);
      });
  }

  initData().then(function() {
    $scope.grid.infiniteScroll.resetScroll(true, true);
  })

});