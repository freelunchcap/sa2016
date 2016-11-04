APP.config(function($stateProvider) {

  $stateProvider.state('assets.characters', {
    title: 'Characters',
    url: '/characters',
    templateUrl: '/assets/characters/partial.html',
    controller: 'CharactersCtrl'
  });

  $stateProvider.state('assets.characters.canvas', {
    url: '/:id',
    templateUrl: '/assets/characters/canvas.html',
    controller: 'CharacterCanvasCtrl'
  });
  
});