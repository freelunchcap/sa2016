APP.factory('API', function($http) {

  return {
    list: function(END_POINT) {
      return function list(start, dir, max) {
        var url = '/api' + END_POINT + '/list';
        var params = [];
        if(start != null) params.push('start=' + start);
        if(dir != null) params.push('dir=' + dir);
        if(max != null) params.push('max=' + max);
        if(params.length > 0) url += '?' + params.join('&');
        return $http.get(url);
      };
    }
  }

});

APP.factory('GRID', function($timeout) {

  const PAGE_SIZE = 100;

  return {
    make: function(API, start, select) {

      var top = false;
      var bottom = false;

      var grid = {
        infiniteScrollRowsFromEnd: PAGE_SIZE / 2,
        infiniteScrollUp: true,
        infiniteScrollDown: true,
        multiSelect: false,
        enableRowHeaderSelection: false,
        noUnselect: true,
        data: [],
        onRegisterApi: function(api) {
          angular.extend(grid, api);
        }
      };

      function scrollDown() {
        var data = grid.data;
        var last = data[data.length - 1];
        API.list(last.id, 'gt', PAGE_SIZE)
          .success(function(after) {
            grid.infiniteScroll.saveScrollPercentage();
            grid.data = data.concat(after);
            grid.infiniteScroll.dataLoaded(!top, !bottom);
            bottom |= after.length < PAGE_SIZE;
          });
      }

      function scrollUp() {
        var data = grid.data;
        var first = data[0];
        API.list(first.id, 'lt', PAGE_SIZE)
          .success(function(before) {
            grid.infiniteScroll.saveScrollPercentage();
            grid.data = before.reverse().concat(data);
            grid.infiniteScroll.dataLoaded(!top, !bottom);
            top |= before.length < PAGE_SIZE;
          });
      }

      function initData() {
        return API.list(start, 'gte', PAGE_SIZE)
          .success(function(data) {
            grid.data = grid.data.concat(data);
          });
      }

      function setupScrolling(grid) {
        var scroll = grid.infiniteScroll;
        scroll.on.needLoadMoreData(null, scrollDown);
        scroll.on.needLoadMoreDataTop(null, scrollUp);
      }

      function onSelect(row) {
        select(row.entity);
      }

      function setupSelection(grid) {
        var selection = grid.selection;
        selection.on.rowSelectionChanged(null, onSelect);
      }

      initData().then(function() {
        setupScrolling(grid);
        setupSelection(grid);
        $timeout(function() {
          grid.infiniteScroll.resetScroll(true, true);
          grid.selection.selectRow(grid.data[0]);
        });
      });
      
      return grid;

    }
  }

});