package thisisjava.src.ChatProject.Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ReceiveClient {
    public static void main(String[] args) {
        createReceivingConsole();
    }

    private static void createReceivingConsole() {
        try {
            Socket inputServerSocket = new Socket("localhost", 8888);
            BufferedReader inputIn = new BufferedReader(new InputStreamReader(inputServerSocket.getInputStream()));

            String message;
            while ((message = inputIn.readLine()) != null) {
                System.out.println("서버로부터의 메시지: " + message);
            }

            inputIn.close();
            inputServerSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
