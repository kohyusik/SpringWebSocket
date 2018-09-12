/**
 @FileName    : websocket
 @Author      : kohyusik
 */
'use strict';
require(['/public/js/root.js'], function (root) {
    require(['jquery', 'sockjs', 'stomp'], function ($, SockJS , stomp) {

        var sendBtn = $('#sendBtn');
        var messageInput = $('.message');
        var chatList = $('.chatList');

        // handshake
        var sock = new SockJS("/ws");
        var stompClient = Stomp.over(sock); // 1. SockJS를 내부에 들고 있는 client를 내어준다.

        // stompClient.debug = null;

        stompClient.connect({}, function (frame) {
            console.log("connected, session id: " + sock._transport.url);
            var websocketUrl = sock._transport.url;
            var tempArray = websocketUrl.split('/');

            stompClient.subscribe('/topic' + '/sub/test', function (data) {
                console.log(data);
            });
        });

        // sock.onopen = function () {
        //     // send : connection으로 message를 전달
        //     // connection이 맺어진 후 가입(JOIN) 메시지를 전달
        //     // sock.send(JSON.stringify({chatRoomId: 1, type: 'JOIN', writer: '1123'}));
        //     sock.send('TESTSETSET JOIN');
        //     console.log("join");
        //
        //     // onmessage : message를 받았을 때의 callback
        //     sock.onmessage = function (e) {
        //         var content = JSON.parse(e.data);
        //         chatList.append('<li>' + content.message + '(' + content.writer + ')</li><br/>')
        //     }
        // };

        sendBtn.click(function () {
            var message = messageInput.val();

            var query = {};
            // query.message = message;
            stompClient.send("/app" + '/control/test', {}, message);
            messageInput.val('');
        });

    });
});