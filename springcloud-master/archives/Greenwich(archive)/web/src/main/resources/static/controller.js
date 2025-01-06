var app = angular.module('myApp', []);

app.controller('formCtr', function($scope, $http) {
	$scope.persons = [];
	$scope.person = {
		name: "Trung tam Java",
		username: "",
		role:'ROLE_ADMIN'
	};
	
	// get all
	$scope.getAllPerson = function() {
		$http({
		    method: 'GET',
		    url: 'http://localhost:8080/account/api/accounts',
		    headers: {
				"Authorization" : "Bearer " + access_token,
		    }
		}).then(function(response) {
			$scope.persons = response.data;// lay du lieu moi tu server
		})
	}
	// vao app , lay luon du lieu tu server ve
	$scope.getAllPerson();
	
	// them
	$scope.addPerson = function() {
		$http({
		    method: 'POST',
		    url: 'http://localhost:8080/account/api/account',
		    headers: {
				"Authorization" : "Bearer " + access_token,
		    },
		    data: $scope.person
		}).then(function(response) {
				$scope.persons.push(response.data);
		}, error => {
			alert("Tai khoan username da ton tai!")
		});
	}
	
	// Xoa
	$scope.delete = function(id) {
		$scope.person.id = id
	}
	
	$scope.confirmDelete = function() {
		$http({
		    method: 'DELETE',
		    url: 'http://localhost:8080/account/api/account/' + $scope.person.id,
		    headers: {
				"Authorization" : "Bearer " + access_token,
		    }
		}).then(function(response) {
				for (var i = 0; i < $scope.persons.length ; i++) {
					if ($scope.persons[i].id == $scope.person.id) {
						$scope.persons.splice(i, 1);// xoa 1 phan tu, tu vi tri
													// i
						break;
					}
				}
			});
	}
	
	// update
	$scope.updatePerson = function() {
		$http({
		    method: 'PUT',
		    url: 'http://localhost:8080/account/api/account',
		    headers: {
				"Authorization" : "Bearer " + access_token,
		    },
		    data: $scope.person
		}).then(function(response) {
			console.log("thanh cong");
		});
	}
	
	// Sua
	$scope.edit = function(p) {
		$scope.person = p;
	}
});