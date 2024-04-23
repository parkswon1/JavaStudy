package thisisjava.src.Network;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoThreadServer {
    public static void main(String[] args) {
        //1. ServerSocket 생성..  -- 1개만 있으면 됨.
        try (ServerSocket serverSocket = new ServerSocket(9999);
        ) {
            while (true) {
                Socket clientSock = serverSocket.accept();  //클라이언트 수만큼 반복!!
                //클라이언트마다 각자 실행 할 수 있도록 만들어야함!!  (쓰레드!!)
                EchoThread echoThread = new EchoThread(clientSock);
                echoThread.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}