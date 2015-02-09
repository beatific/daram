angular.module('daramApp').service('DaramService', ['$q', '$timeout' ,function($q, $timeout) {
    
	var service = {}, listener = $q.defer(), socket = {
		client : null,
		stomp : null
	}, messageIds = [];

	service.RECONNECT_TIMEOUT = 30000;
	service.SOCKET_URL = "/daram-web/websocket";
	service.CHAT_TOPIC = "/data";
	service.CHAT_BROKER = "/app/dashboard";

	var initialize = function() {
		socket.client = new SockJS(service.SOCKET_URL);
		socket.stomp = Stomp.over(socket.client);
		socket.stomp.connect({}, startListener);
		socket.stomp.onclose = reconnect;
	};

	var startListener = function() {
		socket.stomp.subscribe(service.CHAT_TOPIC, function(data) {
			listener.notify(getMessage(data.body));
		});
	};

	service.receive = function() {
		return listener.promise;
	};

	service.send = function(array) {
		socket.stomp.send(service.CHAT_BROKER, {}, JSON.stringify(array));
		messageIds.push(id);
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