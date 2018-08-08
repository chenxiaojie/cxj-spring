define(['webapp'], function (webapp) {

    webapp.service('college.list.service', function ($http, $routeParams) {
        return {
            list: function () {
                var pageIndex = $routeParams.pageIndex || 1;
                var pageSize = $routeParams.pageSize || 10;
                return $http({
                    method: 'GET',
                    url: '/college/list?pageIndex=' + pageIndex + '&pageSize=' + pageSize
                });
            },
            save: function (college) {
                return $http({
                    method: 'POST',
                    url: '/college',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    data: college
                });
            },
            delete: function (id) {
                return $http({
                    method: 'DELETE',
                    url: '/college/' + id
                });
            }
        }
    });

    webapp.controller('college.list.controller', ['$scope', 'college.list.service', '$uibModal', function ($scope, listService, $uibModal) {

        $scope.pageIndex = 1;
        $scope.$watch('pageIndex', function (newValue, oldValue, scope) {
            if (newValue != oldValue) {
                window.location = '#/college/list?pageIndex=' + newValue;
            }
        });

        var list = function () {
            listService.list().success(function (data) {
                if (data && data.code == 200) {
                    $scope.colleges = data.data.list;
                    $scope.totalCount = data.data.totalCount;
                    $scope.pageIndex = data.data.pageIndex;
                }
            });
        };
        list();

        $scope.showAddDialog = function () {
            $uibModal.open({
                animation: true,
                ariaLabelledBy: 'modal-title',
                ariaDescribedBy: 'modal-body',
                templateUrl: '/static/js/college/add.html',
                controller: 'college.add.controller',
                controllerAs: '$ctrl'
            });
        };

        $scope.showDeleteDialog = function (id) {
            $uibModal.open({
                animation: true,
                ariaLabelledBy: 'modal-title',
                ariaDescribedBy: 'modal-body',
                templateUrl: '/static/js/college/delete.html',
                controller: 'college.delete.controller',
                controllerAs: '$ctrl',
                resolve: {
                    id: id
                }
            }).result.then(function (success) {
                if (success) {
                    list();
                }
            });
        };
    }]);


    webapp.controller('college.add.controller', ['$uibModalInstance', 'college.list.service', 'global.alert', function ($uibModalInstance, listService, globalAlert) {
        var $ctrl = this;

        $ctrl.save = function () {
            var college = {
                collegeName: $ctrl.collegeName,
                collegeAddress: $ctrl.collegeAddress
            };

            listService.save(college).success(function (data) {
                if (data && data.code == 200) {
                    globalAlert.alert('保存成功!', true, 'success');
                    $uibModalInstance.close(true);
                    window.location = '#/college/detail/' + data.data.id;
                } else {
                    globalAlert.alert('保存失败! ' + JSON.stringify(data));
                    $uibModalInstance.close(false);
                }
            });
        };

        $ctrl.cancel = function () {
            $uibModalInstance.dismiss(false);
        };
    }]);


    webapp.controller('college.delete.controller', ['$uibModalInstance', 'college.list.service', 'global.alert', 'id', function ($uibModalInstance, listService, globalAlert, id) {
        var $ctrl = this;

        $ctrl.delete = function () {
            listService.delete(id).success(function (data) {
                if (data && data.code == 200) {
                    globalAlert.alert('删除成功!', true, 'success');
                    $uibModalInstance.close(true);
                } else {
                    globalAlert.alert('删除失败! ' + JSON.stringify(data));
                    $uibModalInstance.close(false);
                }
            });
        };

        $ctrl.cancel = function () {
            $uibModalInstance.dismiss(false);
        };
    }]);

    return webapp;
});