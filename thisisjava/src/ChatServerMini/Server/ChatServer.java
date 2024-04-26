package thisisjava.src.ChatServerMini.Server;

import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer {
    private static final int PORT = 12345;
    private static Set<String> nicknames = new HashSet<>();
    private static Map<Integer, List<ClientHandler>> rooms = new HashMap<>();
    private static int nextRoomNumber = 2;
    private static Map<String, PrintWriter> clientWriters = new HashMap<>();

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server is running on port " + PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected: " + clientSocket);

                ClientHandler clientHandler = new ClientHandler(clientSocket);
                new Thread(clientHandler).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class ClientHandler implements Runnable {
        private String nickname;
        private Socket socket;
        private BufferedReader in;
        private PrintWriter out;
        private int roomNumber = -1;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);

                while (true) {
                    String name = in.readLine();
                    if (name == null) {
                        return;
                    }
                    synchronized (nicknames) {
                        if (!nicknames.contains(name)) {
                            nickname = name;
                            nicknames.add(nickname);
                            clientWriters.put(nickname, out);
                            break;
                        } else {
                            out.println("Nickname already exists. Please choose another one:");
                        }
                    }
                }

                System.out.println(nickname + " connected.");
                broadcastToRoom("User " + nickname + " connected.", roomNumber);

                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    if (inputLine.startsWith("/")) {
                        handleCommand(inputLine);
                    } else {
                        broadcastToRoom(nickname + ": " + inputLine, roomNumber);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (nickname != null) {
                    nicknames.remove(nickname);
                    clientWriters.remove(nickname);
                    broadcastToRoom("User " + nickname + " disconnected.", roomNumber);
                }
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        private void handleCommand(String command) {
            if (command.startsWith("/create")) {
                createRoom();
            } else if (command.startsWith("/list")) {
                listRooms();
            } else if (command.startsWith("/join")) {
                joinRoom(command);
            } else if (command.startsWith("/exit")) {
                exitRoom();
            } else if (command.startsWith("/bye")) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (command.startsWith("/users")) {
                listUsers();
            } else if (command.startsWith("/roomusers")) {
                listRoomUsers();
            } else if (command.startsWith("/whisper")) {
                whisper(command);
            }
        }

        private void createRoom() {
            synchronized (rooms) {
                List<ClientHandler> clients = new ArrayList<>();
                clients.add(this);
                rooms.put(nextRoomNumber, clients);
                out.println("Room " + nextRoomNumber + " created.");
                roomNumber = nextRoomNumber;
                nextRoomNumber++;
                broadcastToRoom("User " + nickname + " created the room.", roomNumber);
            }
        }

        private void listRooms() {
            synchronized (rooms) {
                out.println("Room List:");
                for (int room : rooms.keySet()) {
                    out.println(room);
                }
            }
        }

        private void joinRoom(String command) {
            String[] parts = command.split(" ");
            if (parts.length != 2) {
                out.println("Invalid command. Usage: /join [room number]");
                return;
            }
            int requestedRoomNumber = Integer.parseInt(parts[1]);
            synchronized (rooms) {
                if (!rooms.containsKey(requestedRoomNumber)) {
                    out.println("Room " + requestedRoomNumber + " does not exist.");
                    return;
                }
                if (roomNumber == requestedRoomNumber) {
                    out.println("You are already in room " + requestedRoomNumber + ".");
                    return;
                }
                if (roomNumber != -1) {
                    broadcastToRoom("User " + nickname + " left the room.", roomNumber);
                    rooms.get(roomNumber).remove(this);
                }
                rooms.get(requestedRoomNumber).add(this);
                roomNumber = requestedRoomNumber;
                broadcastToRoom("User " + nickname + " joined the room.", roomNumber);
            }
        }

        private void exitRoom() {
            synchronized (rooms) {
                if (roomNumber != -1) {
                    broadcastToRoom("User " + nickname + " left the room.", roomNumber);
                    rooms.get(roomNumber).remove(this);
                    roomNumber = -1;
                }
            }
        }

        private void listUsers() {
            synchronized (nicknames) {
                out.println("Current users:");
                for (String user : nicknames) {
                    out.println(user);
                }
            }
        }

        private void listRoomUsers() {
            synchronized (rooms) {
                if (roomNumber != -1) {
                    out.println("Users in the room:");
                    for (ClientHandler client : rooms.get(roomNumber)) {
                        out.println(client.nickname);
                    }
                } else {
                    out.println("You are not in a room.");
                }
            }
        }

        private void whisper(String command) {
            String[] parts = command.split(" ", 3);
            if (parts.length != 3) {
                out.println("Invalid command. Usage: /whisper [nickname] [message]");
                return;
            }
            String recipient = parts[1];
            String message = parts[2];
            PrintWriter recipientWriter = clientWriters.get(recipient);
            if (recipientWriter != null) {
                recipientWriter.println("[Whisper from " + nickname + "]: " + message);
                out.println("[Whisper to " + recipient + "]: " + message);
            } else {
                out.println("User " + recipient + " not found.");
            }
        }

        private void broadcastToRoom(String message, int roomNumber) {
            synchronized (rooms) {
                if (roomNumber != -1) {
                    for (ClientHandler client : rooms.get(roomNumber)) {
                        client.out.println(message);
                    }
                } else {
                    out.println("You are not in a room.");
                }
            }
        }
    }
}
