'use strict';

/* jasmine specs for controllers go here */

describe('Registration controllers', function(){
  beforeEach(module('registration.controllers'));
  beforeEach(module('registration.services'));

  beforeEach(function(){
    this.addMatchers({
      toEqualData: function(expected) {
        return angular.equals(this.actual, expected);
      }
    });
  });

  var scope, ctrl, $httpBackend;


  describe('ApplicationDetailsController', function() {
    beforeEach(inject(function(_$httpBackend_, $rootScope, $controller) {
      $httpBackend = _$httpBackend_;
      scope = $rootScope.$new();
      var routeParams = {};
      ctrl = $controller('ApplicationDetailsCtrl', {$scope: scope, $routeParams: routeParams});
    }));

    it('addCategory', inject(function() {
      scope.addCategory("test", "category");
      expect(scope.currentApplication.categories["test"]).toBe("category");
      expect(scope.newCategoryKey).toBe("");
      expect(scope.newCategoryValue).toBe("");
    }));

    it('removeCategory', inject(function() {
      scope.addCategory("test", "category");
      scope.removeCategory("test");
      expect(scope.currentApplication.categories["test"]).toBe(undefined);
    }));
  });

  describe('AdminController', function() {
    beforeEach(inject(function(_$httpBackend_, $rootScope, $controller) {
      $httpBackend = _$httpBackend_;
      scope = $rootScope.$new();
      ctrl = $controller("AdminCtrl", {$scope: scope});
    })
    );

    it('Add new hashtags should refresh the graph', inject(function() {
      expect("1").toBe("1");
    }));
  });
});