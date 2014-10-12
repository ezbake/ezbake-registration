'use strict';

/* Directives */


angular.module('admin.directives', [])
  .directive('ngConfirmClick', [
    function(){
      return {
        priority: 1,
        terminal: true,
        link: function (scope, element, attr) {
          var msg = attr.ngConfirmClick || "Are you sure?";
          var clickAction = attr.ngClick;
          element.bind('click',function (event) {
            if ( window.confirm(msg) ) {
              scope.$eval(clickAction)
            }
          });
        }
      };
    }
  ])
  .directive('dialogWindowNode', [ '$document', '$timeout', '$window', function( $document, $timeout, $window ) {
	var linkFn;
	linkFn = function link(scope, element, attr ) {
		$timeout(function(){
			var applicationContainer = document.querySelector('#slide-in-container-id');
			applicationContainer.appendChild(document.querySelector('.modalDialog'));
		} );
		
	}; 
		return {
			restrict: 'E',
			link: linkFn
	};
}]) 
  ;

