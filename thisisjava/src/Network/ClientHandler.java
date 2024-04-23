package thisisjava.src.Network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

class EchoThread extends Thread {
    private Socket socket;

    EchoThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        System.out.println(socket.getInetAddress().getHostAddress() + "로 부터 연결되었습니다.");

        try (PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        ) {
            String line = null;
            while ((line = in.readLine()) != null) {
                System.out.println("클라이언트로 부터 받은 메시지 : " + line);
                out.println(line);
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}