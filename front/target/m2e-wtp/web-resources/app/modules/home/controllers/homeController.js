define([ 'app'  ], function(app) {
	app.controller('homeController', ['$filter', '$state','$scope', '$stateParams', '$rootScope',
	                                                  function($filter, $state, $scope, $stateParams, $rootScope){

		$rootScope.$notification.$notify("mario");
		
		hello.init({google: '1044895406871-4ekr4g0pnm5jf86c500uigup3mqi23se.apps.googleusercontent.com' });
		$scope.login = function(){
			
			
			hello( 'google' ).login( function() {
				  token = hello( 'google' ).getAuthResponse().access_token;
				})
			
			
			
//			hello('facebook').login().then(function() {
//				alert('You are signed in to Facebook');
//			}, function(e) {
//				alert('Signin error: ' + e.error.message);
//			});
			
		};
		
		
		
		
	}]);

	return app;
});
