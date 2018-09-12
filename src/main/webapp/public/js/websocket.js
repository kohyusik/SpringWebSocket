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
        var stompClient = Stomp.over(sock);

        // debug
        // stompClient.debug = null;

        stompClient.connect({}, function (frame) {
            console.log("connected, session id: " + sock._transport.url);
            stompClient.subscribe('/topic' + '/sub/test', function (data) {
                var chat = $('<p>').text(data.body);
                chatList.append(chat);
            });
        });

        sendBtn.click(function () {
            var message = messageInput.val();

            var query = {};
            // query.message = message;
            // stompClient.send("/app" + '/control/test', {}, message);
            stompClient.send("/topic" + '/sub/test', {}, message);
            messageInput.val('');
            messageInput.focus();
        });

        messageInput.keyup(function(e) {
            if (e.key === 'Enter') {
                sendBtn.click();
            }
        });

    });
});