(function (window) {
    var baseUrl = document.getElementById('require-script').getAttribute('data-baseurl');

    var config = {
        baseUrl: baseUrl,
        paths: {
            'angular': 'angular.min',
            'router': 'angular-route.min',
            'animate': 'angular-animate.min',
            'touch': 'angular-touch.min',
            'ui': 'ui-bootstrap',
            'ui-templates': 'ui-bootstrap-templates',
            'webapp': 'webapp'
        },
        shim: {
            'angular': {
                exports: 'angular'
            },
            'router': {
                deps: ['angular'],
                exports: 'router'
            },
            'animate': {
                deps: ['angular', 'router'],
                exports: 'animate'
            },
            'touch': {
                deps: ['angular', 'router', 'animate'],
                exports: 'touch'
            },
            'ui': {
                deps: ['angular', 'router', 'animate', 'touch'],
                exports: 'ui'
            },
            'ui-templates': {
                deps: ['angular', 'router', 'animate', 'touch', 'ui'],
                exports: 'ui-templates'
            },
            'webapp': {
                deps: ['angular', 'router', 'animate', 'touch', 'ui', 'ui-templates'],
                exports: 'webapp'
            }
        }
    };

    require.config(config);

    require(['angular', 'router', 'animate', 'touch', 'ui', 'ui-templates', 'webapp'], function (angular) {
        angular.bootstrap(document, ['webapp']);
    });

})(window);