package thisisjava.src.ChatProject.Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SendClient {
    public static void main(String[] args) {
        createSendingConsole();
    }

    private static void createSendingConsole() {
        try {
            Socket outputServerSocket = new Socket("localhost", 8888);
            PrintWriter outputOut = new PrintWriter(outputServerSocket.getOutputStream(), true);

            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
            String userInput;
            while ((userInput = stdIn.readLine()) != null) {
                outputOut.println(userInput);
            }

            outputOut.close();
            outputServerSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
