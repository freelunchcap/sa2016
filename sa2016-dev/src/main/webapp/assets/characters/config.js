APP.config(function($stateProvider) {

  $stateProvider.state('assets.characters', {
    title: 'Characters',
    url: '/characters',
    templateUrl: '/assets/characters/partial.html',
    controller: 'CharactersCtrl'
  });
  
});