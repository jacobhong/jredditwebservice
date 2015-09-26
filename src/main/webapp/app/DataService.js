
angular.module('webapp').factory('dataservice', [ '$http', '$log', function dataservice($http, logger) {
	return {
		getUserToken : getUserToken,
		copyUserMultis : copyUserMultis,
		getAccessToken : getAccessToken,
		makeMultisPublic : makeMultisPublic
	};

	function getUserToken() {
		return $http.get('http://localhost:8080/jredditwebservice/rest/userService/token')
				.success(getCardsSuccess).error(getCardsError);
		
		function getCardsSuccess(response) {
			return response.data;
		}

		function getCardsError(error) {
			logger.error('Request failed: ' + error);
		}
	}
	
	function copyUserMultis() {
		return $http.post('http://localhost:8080/jredditwebservice/rest/userService/copy')
				.success(getCardsSuccess).error(getCardsError);
		
		function getCardsSuccess(response) {
			return response.data;
		}

		function getCardsError(error) {
			logger.error('Request failed: ' + error);
		}
	}
	
	function getAccessToken() {
		return $http.get('http://localhost:8080/jredditwebservice/rest/userService/accessToken')
				.success(getCardsSuccess).error(getCardsError);
		
		function getCardsSuccess(response) {
			return response.data;
		}

		function getCardsError(error) {
			logger.error('Request failed: ' + error);
		}
	}
	
	function makeMultisPublic() {
		return $http.post('http://localhost:8080/jredditwebservice/rest/userService/visibility')
				.success(getCardsSuccess).error(getCardsError);
		
		function getCardsSuccess(response) {
			return response.data;
		}

		function getCardsError(error) {
			logger.error('Request failed: ' + error);
		}
	}
	

}]);