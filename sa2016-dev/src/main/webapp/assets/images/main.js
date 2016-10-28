APP.controller('ImagesCtrl', function($scope, $state, $timeout, ImagesAPI) {

  const PAGE_SIZE = 100;

  $scope.grid = makeGrid();
  
  var top = false;
  var bottom = false;

  function makeGrid() {
    return {
      infiniteScrollRowsFromEnd: PAGE_SIZE / 2,
      infiniteScrollUp: true,
      infiniteScrollDown: true,
      multiSelect: false,
      enableRowHeaderSelection: false,
      noUnselect: true,
      columnDefs: [
        {name: 'id', title: 'ID'},
        {name: 'width', title: 'Width'},
        {name: 'height', title: 'Height'}
      ],
      data: [],
      onRegisterApi: function(api) {
        angular.extend($scope.grid, api);
      }
    };
  }

  function scrollDown() {
    var data = $scope.grid.data;
    var last = data[data.length - 1];
    ImagesAPI.list(last.id, 'gt', PAGE_SIZE)
      .success(function(after) {
        $scope.grid.infiniteScroll.saveScrollPercentage();
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
        $scope.grid.infiniteScroll.saveScrollPercentage();
        $scope.grid.data = before.reverse().concat(data);
        top |= before.length < PAGE_SIZE;
        $scope.grid.infiniteScroll.dataLoaded(!top, !bottom);
      });
  }

  function initData() {
    return ImagesAPI.list($state.params.id, 'gte', PAGE_SIZE)
      .success(function(data) {
        $scope.grid.data = $scope.grid.data.concat(data);
      });
  }

  function setupScrolling(grid) {
    var scroll = grid.infiniteScroll;
    scroll.on.needLoadMoreData($scope, scrollDown);
    scroll.on.needLoadMoreDataTop($scope, scrollUp);
  }

  function viewImage(row) {
    var image = row.entity;
    $state.go('assets.images.canvas', {
      id: image.id,
      format: image.format
    });
  }

  function setupSelection(grid) {
    var selection = grid.selection;
    selection.on.rowSelectionChanged($scope, viewImage);
  }

  initData().then(function() {
    setupScrolling($scope.grid);
    setupSelection($scope.grid);
    $timeout(function() {
      $scope.grid.infiniteScroll.resetScroll(true, true);
      $scope.grid.selection.selectRow($scope.grid.data[0]);
    });
  })

});