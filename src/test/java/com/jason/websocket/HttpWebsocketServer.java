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
    
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        
        // server socket 생
        int port = 1988;
        final String WS_MAGIC_STRING = "258EAFA5-E914-47DA-95CA-C5AB0DC85B11";
        
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("port number : " + port);
        
        Map<String, String> headers = new HashMap<>();
        
        while (true) {
            Socket clientSocket = serverSocket.accept();
            System.out.println("요청 들어옴");
            
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            
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
                
                byte byteData[] = md.digest();
                
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
                //				out.write("Sec-WebSocket-Extensions: permessage-deflate;client_max_window_bits=15" + "\r\n");
                //				out.write("Sec-WebSocket-Accept: " + "WXVjpcIMh5mK8bApeONPlq2DEHA=" + "\r\n");
                out.write("\r\n");
                
                System.out.println("통신 끝");
                
                out.write(0x81);
                out.write(0x4);
                //				out.write('t');
                out.flush();
                
                char[] cbuf = new char[1000];
                //				while (true) {
                //					int r = in.read(cbuf);
                //					System.out.println(cbuf);
                //					System.out.println("data start");
                //					for (int i = 0; i < r; i++) {
                //						System.out.println((int)cbuf[i]);
                //					}
                //				}
                
            }
        }
    }
    
}
