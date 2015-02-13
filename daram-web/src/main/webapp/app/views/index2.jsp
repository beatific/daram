<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>Monitor JVM</title>

</head>
<body>
<body ng-app='daramApp'>

	<div ng-controller="JstatController" ng-init="init()">

		<form name='monitorForm'>
			<div class='hallasan'>
				From time : <input ng-model='time.from' ng-disabled='live' required>
				~ To time : <input ng-model='time.to' ng-disabled='live' required>
				<button ng-click='getGraphs()'
					ng-disabled='!monitorForm.$valid || live'>submit</button>
				<input type="checkbox" ng-model='live' ng-change='setLive()' />live
			</div>
		</form>
		<br />
		<table>
		<tr>
		<div class="column ui-sortable">
			<div
				class="portlet ui-widget ui-widget-content ui-helper-clearfix ui-corner-all">
				<div
					class="portlet-header ui-sortable-handle ui-widget-header ui-corner-all">
					<span class="portlet-toggle"></span> {{eden.designName}}
				</div>
				<div id="{{eden.designName}}" class="portlet-content"></div>
			</div>
		</div>
		<div class="column ui-sortable">
			<div
				class="portlet ui-widget ui-widget-content ui-helper-clearfix ui-corner-all">
				<div
					class="portlet-header ui-sortable-handle ui-widget-header ui-corner-all">
					<span class="portlet-toggle"></span> {{s0.designName}}
				</div>
				<div id="{{s0.designName}}" class="portlet-content"></div>
			</div>
			<div
				class="portlet ui-widget ui-widget-content ui-helper-clearfix ui-corner-all">
				<div
					class="portlet-header ui-sortable-handle ui-widget-header ui-corner-all">
					<span class="portlet-toggle"></span> {{s1.designName}}
				</div>
				<div id="{{s1.designName}}" class="portlet-content"></div>
			</div>
		</div>
		<div class="column ui-sortable">
			<div
				class="portlet ui-widget ui-widget-content ui-helper-clearfix ui-corner-all">
				<div
					class="portlet-header ui-sortable-handle ui-widget-header ui-corner-all">
					<span class="portlet-toggle"></span> {{old.designName}}
				</div>
				<div id="{{old.designName}}" class="portlet-content"></div>
			</div>
		</div>
		</tr>
		<tr>
		<div class="column ui-sortable">
			<div
				class="portlet ui-widget ui-widget-content ui-helper-clearfix ui-corner-all">
				<div
					class="portlet-header ui-sortable-handle ui-widget-header ui-corner-all">
					<span class="portlet-toggle"></span> {{gct.designName}}
				</div>
				<div id="{{gct.designName}}" class="portlet-content"></div>
			</div>
		</div>
		<div class="column ui-sortable">
			<div
				class="portlet ui-widget ui-widget-content ui-helper-clearfix ui-corner-all">
				<div
					class="portlet-header ui-sortable-handle ui-widget-header ui-corner-all">
					<span class="portlet-toggle"></span> {{gcc.designName}}
				</div>
				<div id="{{gcc.designName}}" class="portlet-content"></div>
			</div>
		</div>
		</tr>
		</table>

	</div>
</body>
<link rel="stylesheet" href="css/jquery-ui.min.css">
<link rel="stylesheet" href="stylesheets/daram.css" />
<link rel="stylesheet" type="text/css" href="stylesheets/hangul.css">

<script src="lib/jquery/jquery.js"></script>
<script src="lib/jquery/jquery-ui.js"></script>
<script src="lib/googlejs/googlechart.js"></script>
<script src="lib/googlejs/monitor.js"></script>
<script src="lib/angular/angular.js"></script>
<script src="lib/websocket/sockjs-0.3.4.js" type="text/javascript"></script>
<script src="lib/websocket/stomp.min.js" type="text/javascript"></script>
<script src="lib/jquery/sortable.js" type="text/javascript"></script>

<script src="js/app.js" type="text/javascript"></script>
<script src="js/controllers/controllers.js" type="text/javascript"></script>
<script src="js/services/services.js" type="text/javascript"></script>

<style>
div.hallasan {
	font-family: 'Jeju Hallasan', fantasy;
}

body {
	min-width: 520px;
}

.column {
	width: 850;
	float: left;
	padding-bottom: 100px;
}

.portlet {
	margin: 0 1em 1em 0;
	padding: 0.3em;
}

.portlet-header {
	padding: 0.2em 0.3em;
	margin-bottom: 0.5em;
	position: relative;
}

.portlet-toggle {
	position: absolute;
	top: 50%;
	right: 0;
	margin-top: -8px;
}

.portlet-content {
	padding: 0.4em;
}

.portlet-placeholder {
	border: 1px dotted black;
	margin: 0 1em 1em 0;
	height: 50px;
}

.ng-scope {
	
}
</style>
</html>