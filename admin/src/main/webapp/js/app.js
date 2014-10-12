'use strict';

// Declare app level module which depends on filters, and services
angular.module('admin', [
  'ngRoute',
  'admin.filters',
  'admin.services',
  'admin.directives',
  'admin.controllers',
  'ui.bootstrap',
  'toaster'
]).
  config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/admin', {templateUrl: 'partials/admin.html', controller: 'AdminCtrl'});
  $routeProvider.otherwise({redirectTo: '/admin'});
}]);
