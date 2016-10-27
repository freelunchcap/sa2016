APP.config(function($stateProvider) {

  $stateProvider.state('assets.images', {
    title: 'Images',
    url: '/images',
    templateUrl: '/assets/images/partial.html?' + new Date(),
    controller: 'ImagesCtrl'
  });
  
});