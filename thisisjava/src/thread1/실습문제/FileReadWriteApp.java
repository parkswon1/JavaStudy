package thisisjava.src.thread1.실습문제;

import java.io.*;
import java.util.Scanner;

public class FileReadWriteApp {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Thread fileReaderTask = new Thread(new FileReaderTask());
        Thread fileWriterTask = new Thread(new FileWriterTask());

        fileReaderTask.start();
        fileWriterTask.start();
    }

    static class FileReaderTask implements Runnable {
        private final String fileName = "input.txt";

        @Override
        public void run() {
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    static class FileWriterTask implements Runnable {
        private final String fileName = "output.txt";

        @Override
        public void run() {
            try (FileWriter fileWriter = new FileWriter(fileName, true);
                 BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
                String input;
                while (true) {
                    System.out.println("아무거나 입력하세요. 종료하고 싶을 경우 + 를 입력하세요:");
                    input = scanner.nextLine();
                    if (input.equals("+")) {
                        break;
                    } else {
                        System.out.println("입력값을 기록합니다.");
                        bufferedWriter.write(input);
                        bufferedWriter.newLine();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
