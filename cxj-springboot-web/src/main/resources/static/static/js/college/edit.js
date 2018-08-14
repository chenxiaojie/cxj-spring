define(['webapp'], function (webapp) {

    webapp.service('college.edit.service', function ($http) {
        return {
            modify: function (college) {
                return $http({
                    method: 'PUT',
                    url: '/college/' + college.id,
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    data: college
                });
            }
        }
    });

    webapp.controller('college.edit.controller', ['$scope', '$http', '$routeParams', 'college.edit.service', 'global.alert', function ($scope, $http, $routeParams, editService, globalAlert) {
        $http({
            method: 'GET',
            url: '/college/' + $routeParams.id
        }).success(function (data) {
            if (data && data.code == 200) {
                $scope.college = data.data;
            }
        });

        $scope.edit = function () {
            editService.modify($scope.college).success(function (data) {
                if (data && data.code == 200) {
                    globalAlert.alert('更新成功!', true, 'success');
                    window.location = '#/college/detail/' + $scope.college.id;
                } else {
                    globalAlert.alert('更新失败! ' + JSON.stringify(data));
                }
            })
        };

    }]);
    return webapp;
});