angular.module('daramApp').service('DaramService', ['$q', '$timeout' ,function($q, $timeout) {
    
	var service = {}, listener = $q.defer(), socket = {
		client : null,
		stomp : null
	}, messageIds = [];

	service.RECONNECT_TIMEOUT = 30000;
	service.SOCKET_URL = "/daram-web/websocket";
	service.TOPIC = "/data";
	service.BROKER = "/app/dashboard";

	var initialize = function() {
		socket.client = new SockJS(service.SOCKET_URL);
		socket.stomp = Stomp.over(socket.client);
		socket.stomp.connect({}, startListener);
		socket.stomp.onclose = reconnect;
	};

	var startListener = function() {
		socket.stomp.subscribe(service.TOPIC, function(data) {
			listener.notify(getMessage(data.body));
		});
	};

	service.receive = function() {
		return listener.promise;
	};

	service.send = function(array) {
		socket.stomp.send(service.BROKER, {}, JSON.stringify(array));
	};

	var reconnect = function() {
		$timeout(function() {
			initialize();
		}, this.RECONNECT_TIMEOUT);
	};

	var getMessage = function(data) {
		var message = JSON.parse(data);
		return message;
	};

	initialize();
	return service;
  }]);

angular.module('daramApp').service('JstatService', ['$q', '$timeout' ,function($q, $timeout) {
    
	var service = {}, listener = $q.defer(), socket = {
		client : null,
		stomp : null
	}, messageIds = [];

	service.RECONNECT_TIMEOUT = 30000;
	service.SOCKET_URL = "/daram-web/websocket";
	service.GC = "/gc";
	service.BROKER = "/app/updateServer";

	var initialize = function() {
		socket.client = new SockJS(service.SOCKET_URL);
		socket.stomp = Stomp.over(socket.client);
		socket.stomp.connect({}, startListener);
		socket.stomp.onclose = reconnect;
	};

	var startListener = function() {
		socket.stomp.subscribe(service.GC, function(data) {
			listener.notify(getMessage(data.body));
		});
	};

	service.receive = function() {
		return listener.promise;
	};

	service.send = function(server) {
		socket.stomp.send(service.BROKER, {}, JSON.stringify(server));
	};
	
	var reconnect = function() {
		$timeout(function() {
			initialize();
		}, this.RECONNECT_TIMEOUT);
	};

	var getMessage = function(data) {
		var message = JSON.parse(data);
		return message;
	};

	initialize();
	return service;
  }]);