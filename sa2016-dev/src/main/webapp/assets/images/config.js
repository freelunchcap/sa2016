APP.config(function($stateProvider) {

  $stateProvider.state('assets.images', {
    title: 'Images',
    url: '/images',
    templateUrl: '/assets/images/partial.html?' + new Date(),
    controller: 'ImagesCtrl'
  });

  $stateProvider.state('assets.images.canvas', {
    url: '/:id?format',
    templateUrl: '/assets/images/canvas.html?' + new Date(),
    controller: 'ImageCanvasCtrl'
  });
  
});