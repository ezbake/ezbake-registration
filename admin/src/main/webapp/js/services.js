'use strict';

/* Services */

angular.module('admin.services', ['ngResource'])

.factory('DeployApp', ['$resource',
   function($resource) {
     return $resource('rest/admin/deployApp/:appSecId/:isApproved/:status',
    		 {appSecId : '@appSecId', isApproved : '@isApproved', status: '@status'},
    		 {deploy: {method: 'POST'}});
}])

.factory('DeleteApp', ['$resource',
   function($resource) {
     return $resource('rest/admin/deleteApp/:appSecId',
    		 {appSecId : '@appSecId'},
    		 {deleteApp: {method: 'DELETE'}});
}])

.factory('RegisterApp', ['$resource', 
     function($resource) {
		return $resource('rest/admin/registerApp/:appSecId/:isApproved',
				{appSecId : '@appSecId', isApproved : '@isApproved'},
				{register: {method: 'POST'}});
}])

.factory('UpdateManifest', ['$resource', 
     function($resource) {
		return $resource('rest/admin/updateAndDeploy/:appName/:appServiceId/:numOfInstances',
				{appName : '@appName', appServiceId : '@appServiceId', numOfInstances : '@numOfInstances'},
				{update: {method: 'POST'}});
}]);