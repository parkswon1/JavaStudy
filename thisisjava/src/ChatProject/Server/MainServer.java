package thisisjava.src.ChatProject.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

public class MainServer {
    private static final int PORT = 8888;
    private static final Set<PrintWriter> clientWriters = new HashSet<>();

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("서버가 시작되었습니다.");

            while (true) {
                new ClientHandler(serverSocket.accept()).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class ClientHandler extends Thread {
        private Socket socket;
        private PrintWriter writer;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                writer = new PrintWriter(socket.getOutputStream(), true);
                clientWriters.add(writer);

                String message;
                while ((message = reader.readLine()) != null) {
                    System.out.println("클라이언트로부터 받은 메시지: " + message);
                    broadcast(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (writer != null) {
                    clientWriters.remove(writer);
                }
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        private void broadcast(String message) {
            for (PrintWriter clientWriter : clientWriters) {
                clientWriter.println(message);
            }
        }
    }
}