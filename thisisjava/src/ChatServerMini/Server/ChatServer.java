package thisisjava.src.ChatServerMini.Server;

import java.io.*;
import java.net.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ChatServer {
    private static final int PORT = 12347;
    private static Set<String> nicknames = new HashSet<>();
    private static Map<Integer, List<ClientHandler>> rooms = new HashMap<>();
    private static int nextRoomNumber = 1;
    private static Map<String, PrintWriter> clientWriters = new HashMap<>();

    private static final Map<String, String> RESPONSES = new HashMap<>();

    static {
        RESPONSES.put("날씨", "무료 버전은 항상 맑은 날씨입니다.");
        RESPONSES.put("오늘", getCurrentDate() + " 윈도우 오른쪽 아래엔 달력기능이 있답니다.");
        RESPONSES.put("하늘", "저녁 노을이 이쁘긴 합니다.");
        RESPONSES.put("발표", "현제 4조와 함께하고 계십니다.");
        RESPONSES.put("이해 했나요?", "대충 고양이 우는 이모티콘.");
        RESPONSES.put("오늘의 추천 음악", "samsmith - midnight train");
        RESPONSES.put("내일의 추천 음악", "bruno mars - 777");
    }

    private static String getCurrentDate() {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");
        return currentDate.format(formatter);
    }

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("서버가 " + PORT + "에서 실행 중입니다.");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("새로운 클라이언트가 입장했습니다.: " + clientSocket);

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
                            out.println("이미 존재하는 닉네임 입니다. 다른 닉네임을 선택해주세요.");
                        }
                    }
                }

                System.out.println(nickname + "으로 로그인했습니다..");

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
                    broadcastToRoom(nickname + " 이 방에서 나가셨습니다.", roomNumber);
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
            } else if (command.startsWith("/help")) {
                displayHelp();
            } else if (command.startsWith("/ai")) {
                String query = command.substring(4).trim();
                handleAIResponse(query);
            }
        }

        private void displayHelp() {
            out.println("사용 가능한 명령어:");
            out.println("/create - 새로운 방을 생성합니다.");
            out.println("/list - 생성된 방 목록을 확인합니다.");
            out.println("/join [방 번호] - 특정 방에 참여합니다.");
            out.println("/exit - 현재 방을 나갑니다.");
            out.println("/bye - 채팅을 종료합니다.");
            out.println("/users - 현재 서버에 접속한 모든 사용자의 목록을 확인합니다.");
            out.println("/roomusers - 현재 방에 속한 사용자들의 목록을 확인합니다.");
            out.println("/whisper [이름] [메세지] - 특정 사용자에게 개인 메시지를 보냅니다.");
        }

        private void createRoom() {
            synchronized (rooms) {
                List<ClientHandler> clients = new ArrayList<>();
                clients.add(this);
                rooms.put(nextRoomNumber, clients);
                out.println(nextRoomNumber + "번 방을 생성했습니다.");
                roomNumber = nextRoomNumber;
                nextRoomNumber++;
                broadcastToRoom(nickname + "이(가) 방을 생성했습니다.", roomNumber);
            }
        }

        private void listRooms() {
            synchronized (rooms) {
                out.println("방 목록:");
                for (int room : rooms.keySet()) {
                    out.println(room);
                }
            }
        }

        private void joinRoom(String command) {
            String[] parts = command.split(" ");
            if (parts.length != 2) {
                out.println("잘못된 커멘드 입니다. 다음과 같이 시도해 보십시오: /join [방 번호]");
                return;
            }
            int requestedRoomNumber = Integer.parseInt(parts[1]);
            synchronized (rooms) {

                if (!rooms.containsKey(requestedRoomNumber)) {
                    out.println(requestedRoomNumber + "은(는) 존재하지 않는 방 번호 입니다.");
                    return;
                }
                if (roomNumber == requestedRoomNumber) {
                    out.println("이미 해당 방에 속해 있습니다.");
                    return;
                }
                if (roomNumber != -1) {
                    out.println("이미 다른 방에 속해 있습니다. 먼저 그 방에서 나가십시오.");
                    return;
                }
                rooms.get(requestedRoomNumber).add(this);
                roomNumber = requestedRoomNumber;
                broadcastToRoom(nickname + "님이 방에 들어왔습니다.", roomNumber);
            }
        }

        private void exitRoom() {
            synchronized (rooms) {
                if (roomNumber != -1) {
                    broadcastToRoom(nickname + "님이 방을 나갔습니다.", roomNumber);
                    rooms.get(roomNumber).remove(this);
                    roomNumber = -1;
                }
            }
        }

        private void listUsers() {
            synchronized (nicknames) {
                out.println("서버 접속자:");
                for (String user : nicknames) {
                    out.println(user);
                }
            }
        }

        private void listRoomUsers() {
            synchronized (rooms) {
                if (roomNumber != -1) {
                    out.println("방 접속자:");
                    for (ClientHandler client : rooms.get(roomNumber)) {
                        out.println(client.nickname);
                    }
                } else {
                    out.println("당신은 방에 있지 않습니다.");
                }
            }
        }

        private void whisper(String command) {
            String[] parts = command.split(" ", 3);
            if (parts.length != 3) {
                out.println("잘못된 커멘드 입니다. 다음과 같이 시도해 보십시오: /whisper [이름] [메세지]");
                return;
            }
            String recipient = parts[1];
            String message = parts[2];
            PrintWriter recipientWriter = clientWriters.get(recipient);
            if (recipientWriter != null) {
                recipientWriter.println("[" + nickname + "]: " + message);
                out.println("[" + recipient + "]: " + message);
            } else {
                out.println(recipient + "이 닉네임이 없습니다..");
            }
        }

        private void broadcastToRoom(String message, int roomNumber) {
            synchronized (rooms) {
                if (roomNumber != -1) {
                    for (ClientHandler client : rooms.get(roomNumber)) {
                        client.out.println(message);
                    }
                } else {
                    out.println("방에 소속하지 않았습니다.");
                }
            }
        }

        public static String generateResponse(String input) {
            List<String> tokens = Arrays.asList(input.toLowerCase().split(" "));
            Map<String, Integer> similarityScores = new HashMap<>();
            for (String key : RESPONSES.keySet()) {
                int score = calculateSimilarity(tokens, key);
                similarityScores.put(key, score);
            }
            String bestResponse = findBestResponse(similarityScores);
            return RESPONSES.get(bestResponse);
        }

        private static int calculateSimilarity(List<String> tokens, String responseKey) {
            int score = 0;
            for (String token : tokens) {
                if (responseKey.toLowerCase().contains(token)) {
                    score++;
                }
            }
            return score;
        }

        private static String findBestResponse(Map<String, Integer> similarityScores) {
            Random random = new Random();
            String bestResponse = "";
            int maxScore = Integer.MIN_VALUE;
            for (Map.Entry<String, Integer> entry : similarityScores.entrySet()) {
                int score = entry.getValue();
                if (score > maxScore) {
                    maxScore = score;
                    bestResponse = entry.getKey();
                } else if (score == maxScore) {
                    if (random.nextBoolean()) {
                        bestResponse = entry.getKey();
                    }
                }
            }
            return bestResponse;
        }

        private void handleAIResponse(String message) {
            String response = generateResponse(message);

            if (roomNumber != -1) {
                broadcastToRoom(response, roomNumber);
            } else {
                out.println(response);
            }
        }
    }
}