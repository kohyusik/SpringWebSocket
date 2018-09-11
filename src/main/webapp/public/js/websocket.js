/**
 @FileName    : websocket
 @Description :
 @Author      : kohyusik
 @Since       : 2018-09-11
 @Version     : 1.0
 @개정이력     :
 수정일             수정자             수정내용
 ----------        -------------    ----------------------------
 2018-09-11           kohyusik          최초생성
 */

requirejs(['/public/js/root.js'], function () {
    requirejs([], function () {

        var sendBtn = $('#sendBtn');
        var messageInput = $('.message');
        var chatList = $('.chatList');

        // handshake
        var sock = new SockJS("/ws");
        // var client = Stomp.over(sock); // 1. SockJS를 내부에 들고 있는 client를 내어준다.


        // onopen : connection이 맺어졌을 때의 callback
        sock.onopen = function () {
            // send : connection으로 message를 전달
            // connection이 맺어진 후 가입(JOIN) 메시지를 전달
            // sock.send(JSON.stringify({chatRoomId: 1, type: 'JOIN', writer: '1123'}));
            sock.send('TESTSETSET JOIN');
            console.log("join");

            // onmessage : message를 받았을 때의 callback
            sock.onmessage = function (e) {
                var content = JSON.parse(e.data);
                chatList.append('<li>' + content.message + '(' + content.writer + ')</li><br/>')
            }
        };

        sendBtn.click(function () {
            var message = messageInput.val();
            // sock.send(JSON.stringify({type: 'CHAT', message: message}));
            sock.send('TESTSETSET JOIN');
            sock.send('TESTSETSET JOIN');
            messageInput.val('');
        });

    });
});