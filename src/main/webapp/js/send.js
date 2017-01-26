/**
 * Created by rmochetc on 24.01.2017.
 */

var stompClient = null;

$(function () {
    connect();
    // sendName();
});

function connect() {
    var socket = new SockJS('/travian/askHelp');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        showGreeting("TEST");
        stompClient.subscribe('/topic/greetings', function (greeting) {
            showGreeting(JSON.parse(greeting.body).content);
        });
    });
}

function sendName() {
    stompClient.send("/app/askhelp", {}, JSON.stringify({'name': 'test'}));
}

function showGreeting(message) {
    $("#greetings").append("<tr><td>" + message + "</td></tr>");
}

