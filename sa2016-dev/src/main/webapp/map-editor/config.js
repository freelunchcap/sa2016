APP.config(function($stateProvider) {
  $stateProvider.state('map-editor', {
    title: 'Map Editor',
    url: '/map-editor',
    template: '/map-editor/partial.html'
  });
});