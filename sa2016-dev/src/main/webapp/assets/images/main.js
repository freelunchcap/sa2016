APP.controller('ImagesCtrl', function($scope, ImagesAPI) {

  const PAGE_SIZE = 100;

  $scope.grid = makeGrid();

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
    console.log('down');
  }

  function scrollUp() {
    console.log('up');
  }

  function initData() {
    return ImagesAPI.list(null, 'gt', PAGE_SIZE)
      .success(function(data) {
        $scope.grid.data = $scope.grid.data.concat(data);
      });
  }

  initData().then(function() {
    $scope.grid.infiniteScroll.resetScroll(false, false);
  })

});