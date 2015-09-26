/**
 * 
 * @author Jacob Hong
 *
 */
angular.module('webapp', []).controller('UserController',
		[ '$scope', '$filter', 'dataservice', '$log', function UserController($scope, $filter,ds, logger) {	
			
			$scope.getUserToken = function() {
				return ds.getUserToken().then(function(data) {
					$scope.userUrl = data.data;
				});
			};
			
			$scope.copyUserMultis = function() {
				return ds.copyUserMultis().then(function(data) {
					console.log($filter('json')(data));
					$scope.d = data.data;
				});
			}
			
			$scope.getAccessToken = function() {
				return ds.getAccessToken().then(function(data) {
					console.log($filter('json')(data));
					$scope.d = data.data;
				});
			}
			
			$scope.makeMultisPublic = function() {
				return ds.makeMultisPublic().then(function(data) {
					console.log($filter('json')(data));
					$scope.d = data.data;
				});
			}
} ]); 
		
		