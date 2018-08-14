define(['webapp'], function (webapp) {
    webapp.controller('college.detail.controller', function ($scope, $http, $routeParams, $uibModal) {
        $http({
            method: 'GET',
            url: '/college/' + $routeParams.id
        }).success(function (data) {
            if (data && data.code == 200) {
                $scope.college = data.data;
            }
        });
    });
    return webapp;
});