package thisisjava.src.Network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class EchoClient {
    public static void main(String[] args) throws IOException{
        String hostName = "127.0.0.1";
        int protNumber = 9999;

        try (Socket echoSocket = new Socket(hostName, protNumber);
             PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
             BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))){

            String userInput;
            while((userInput = stdIn.readLine()) != null){
                out.println(userInput);
                System.out.println("echo: " + in.readLine());
            }
        }catch (UnknownHostException e){
            System.err.println("host Unkwon" + hostName);
            System.exit(1);
        }catch (IOException e){
            System.err.println("I/Oerr" + hostName);
            System.exit(1);
        }
    }
}
