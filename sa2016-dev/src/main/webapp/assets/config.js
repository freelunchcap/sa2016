APP.config(function($stateProvider) {

  $stateProvider.state('assets', {
    title: 'Assets',
    url: '/assets',
    templateUrl: '/assets/partial.html',
    controller: 'AssetsCtrl'
  });

});