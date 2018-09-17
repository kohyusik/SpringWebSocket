package com.jason.websocket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class HttpWebsocketServer {

    static final int port = 1988;
    static final String WS_MAGIC_STRING = "258EAFA5-E914-47DA-95CA-C5AB0DC85B11";


    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {

        System.out.println("start server " + port);

        ServerSocket serverSocket = new ServerSocket(port);

        Map<String, String> headers = new HashMap<>();

        while (true) {
            Socket clientSocket = serverSocket.accept();

            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

            DataInputStream din = new DataInputStream(clientSocket.getInputStream());
            DataOutputStream dout = new DataOutputStream(clientSocket.getOutputStream());

            String line;
            int seq = 0;
            while ((line = in.readLine()) != null) {

                String[] header = line.split(": ");
                if (header.length == 2) {
                    headers.put(header[0], header[1]);
                }

                System.out.print(seq++ + " : ");
                System.out.println(line);

                if (line.isEmpty()) {
                    break;
                }
            }

            System.out.println(headers);

            if (headers.get("Upgrade") == null) {
                System.out.println("[HTTP]");
                // header
                out.write("HTTP/1.0 200 OK\r\n");
                out.write("Date: Saturday night \r\n");
                out.write("Server:  my server \r\n");
                out.write("Content-Type: text/html\r\n");
                out.write("TEST header: kohyusik\r\n");
                out.write("Content-Length: zz\r\n");
                out.write("Expires: Sat, 14 March 1988 00:59:59 GMT\r\n");
                out.write("Last-modified: Fri, 09 Aug 1996 03:14:44 GMT\r\n");

                // body
                out.write("\r\n");
                out.write("<html>");
                out.write("<head>"
                        + "<title>제목 Title</title>"
                        + "<meta charset=\"UTF-8\">"
                        + "</head>");
                out.write("<p>body 본문 </p>");
                out.write("</html>");
                out.write("\r\n");

                out.close();
                in.close();
                clientSocket.close();

            } else {

                System.out.println("[WebSocket]");
                String unique = headers.get("Sec-WebSocket-Key") + WS_MAGIC_STRING;
                MessageDigest md = MessageDigest.getInstance("SHA-1");
                md.update(unique.getBytes());

                byte byteData[] = md.digest();// SHA-1 hash value

                StringBuffer sb = new StringBuffer();
                for (int i = 0; i < byteData.length; i++) {
                    sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
                }

                String retVal = sb.toString();

                System.out.println(retVal);
                String accHd = new String(Base64.getEncoder().encode(byteData));
                System.out.println(accHd);
                // header
                out.write("HTTP/1.1 101 Switching Protocols\r\n");
                out.write("Upgrade: websocket\r\n");
                out.write("Connection: Upgrade\r\n");
                out.write("Sec-WebSocket-Accept: " + accHd + "\r\n");
                out.write("\r\n");
                out.flush();


                // Web socket connection
                int payloadLenth = 0;
                int[] maskKey = new int[4];
                int[] payloadArray = null;
                int streamIndex = 0;
                int payloadIndex = 0;

                while (true) {

                    int dataSegment = din.read();
                    System.out.println(Integer.toBinaryString(dataSegment));


//                    dout.write(0b10000001); // FIN, text
//                    dout.write(0b00000001); // payload length = 1
//                    dout.write(0b00110001); // '1'
//                    dout.flush();

                    if (streamIndex == 0) {

                    } else if (streamIndex == 1) {

                        payloadLenth = dataSegment - 128; // payload 길이 추출

                    } else if (streamIndex >= 2 && streamIndex <= 5) {

                        maskKey[streamIndex - 2] = dataSegment;

                    } else {

                        if (payloadIndex == 0) {
                            payloadArray = new int[payloadLenth];
                        }
                        payloadArray[payloadIndex] = dataSegment;

                        if (payloadLenth - 1 == payloadIndex) {

                            dout.write(0b10000001);
                            dout.write(payloadLenth);

                            for (int i = 0; i < payloadArray.length; i++) {
                                int mask = maskKey[i % 4];
                                int encoded = payloadArray[i];
                                dout.write(encoded ^ mask); // XOR MASK
                            }
                            dout.flush();

                            payloadIndex = -1;
                            streamIndex = -1;
                        }

                        payloadIndex++;
                    }

                    streamIndex++;

                }

            }
        }
    }


}
