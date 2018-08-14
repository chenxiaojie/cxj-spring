<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>云打印</title>
    <link href="/static/css/bootstrap.min.css" rel="stylesheet">
    <link href="/static/css/ui-bootstrap-csp.css" rel="stylesheet">
    <script id="require-script" data-baseurl="/static/js/" data-main="/static/js/require-base.js"
            src="/static/js/require.js"></script>
</head>
<body>
<nav class="navbar navbar-default" role="navigation">
    <ul class="nav navbar-nav">
        <a href="/index" role="button" class="navbar-brand">
            云打印
        </a>
        <li class="dropdown" uib-dropdown>
            <a role="button" class="dropdown-toggle" uib-dropdown-toggle>
                大学 <b class="caret"></b>
            </a>
            <ul class="dropdown-menu">
                <li><a href="#/college/list">大学列表</a></li>
            </ul>
        </li>
        <li class="dropdown" uib-dropdown>
            <a href="#/college/list">大学列表</a>
        </li>
    </ul>
</nav>
<div id="container" ng-view></div>
<div id="globalAlert" style="position: fixed;z-index: 99999;right: 0px;top: 0px;width: 300px;display: none">
    <div style="margin-bottom: 5px" uib-alert ng-repeat="alert in alerts"
         ng-class="'alert-' + alert.type"
         close="closeAlert($index)">{{alert.msg}}
    </div>
</div>
</body>
</html>