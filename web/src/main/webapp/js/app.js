'use strict';

var login = angular.module('myLoginCheck',['ngCookies']).
  factory('$logincheck', function($cookieStore){
    return function(){
      if (!$cookieStore.get("access_token")) {
        return false;
      }
      return true;
    };
  });

// Declare app level module which depends on filters, and services
angular.module('registration', [
  'ngRoute',
  'registration.filters',
  'registration.services',
  'registration.directives',
  'registration.controllers',
  'ui.bootstrap',
  'ngCookies',
  'myLoginCheck',
  'localytics.directives',
  'angularFileUpload',
  'toaster'
]).
  config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/application/:appId', {templateUrl: 'partials/application.html', controller: 'WizardCtrl'});
  $routeProvider.when('/authorizations', {templateUrl: 'partials/authorizations.html'});
  $routeProvider.when('/feeds', {templateUrl: 'partials/feeds.html', controller: 'FeedsCtrl'});
  $routeProvider.when('/myapps', {templateUrl: 'partials/myapps.html', controller: 'AppsCtrl'});
  $routeProvider.when('/home',{templateUrl: 'partials/myapps.html', controller: 'AppsCtrl', action: '1'});
  $routeProvider.otherwise({redirectTo: '/myapps'});
}]);