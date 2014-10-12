/*   Copyright (C) 2013-2014 Computer Sciences Corporation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. */

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