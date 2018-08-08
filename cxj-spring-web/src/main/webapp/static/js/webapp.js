define(['angular', 'router', 'require'], function (angular, router, require) {

    var baseUrl = document.getElementById('require-script').getAttribute('data-baseurl');
    document.getElementById('globalAlert').style.display = '';

    var dispatcher = {
        '/college/detail/:id': {
            namespace: 'college/detail',
            template: '/static/js/college/detail.html',
            js: 'college/detail'
        },
        '/college/edit/:id': {
            namespace: 'college/edit'
        },
        '/college/list': {},
        '/demo/alert': {},
        '/demo/time': {},
        '/demo/modal': {}
    };

    var webapp = angular.module('webapp', [
        'ngRoute',
        'ui.bootstrap',
        'ngAnimate',
        'ngTouch'
    ]);

    webapp.config([
        '$routeProvider',
        '$locationProvider',
        '$controllerProvider',
        '$compileProvider',
        '$filterProvider',
        '$provide', function ($routeProvider, $locationProvider, $controllerProvider, $compileProvider, $filterProvider, $provide) {

            webapp.controller = $controllerProvider.register;
            webapp.component = $compileProvider.component;
            webapp.directive = $compileProvider.directive;
            webapp.filter = $filterProvider.register;
            webapp.factory = $provide.factory;
            webapp.service = $provide.service;

            //hash模式，不是h5，经典的spa
            $locationProvider.html5Mode(false);

            //设置默认页
            $routeProvider.otherwise({
                redirectTo: '/college/list'
            });

            for (var path in dispatcher) {
                dispatcher[path].namespace = dispatcher[path].namespace || path.substr(1);
                var template = dispatcher[path].template || (baseUrl + dispatcher[path].namespace + '.html');
                var js = dispatcher[path].js || dispatcher[path].namespace;
                (function (path, template, js) {
                    $routeProvider.when(path, {
                        templateUrl: template,
                        resolve: {
                            keyName: function ($q, $rootScope) {
                                var deferred = $q.defer();
                                require([js], function () {
                                    $rootScope.$apply(function () {
                                        deferred.resolve();
                                    });
                                });
                                return deferred.promise;
                            }
                        }
                    });
                })(path, template, js);
            }
        }
    ]);

    webapp.factory('global.alert', ['$rootScope', '$timeout', function ($rootScope, $timeout) {
        $rootScope.alerts = [];
        $rootScope.alert = function (msg, autoClose, type, time) {
            var currentIndex = $rootScope.alerts.length - 1;

            type = type || 'danger';
            $rootScope.alerts.push({msg: msg, type: type});

            if (autoClose == undefined || autoClose) {
                time = time || 3000;
                $timeout(function () {
                    $rootScope.alerts.splice(currentIndex, 1);
                }, time);
            }
        };
        $rootScope.closeAlert = function (index) {
            $rootScope.alerts.splice(index, 1);
        };
        return {
            'alert': $rootScope.alert,
            'closeAlert': $rootScope.closeAlerts
        };
    }]);

    return webapp;
});