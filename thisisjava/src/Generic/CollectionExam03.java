package thisisjava.src.Generic;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CollectionExam03 {

    public static void main(String[] args) throws NotInBoundException {
        Scanner input = new Scanner(System.in);
        List<Integer> scores = new ArrayList<>();

        try {
            // 점수 입력 받기
            readScores(input, scores);
        }catch (NotInBoundException e){
            System.out.println("0-100사이의 숫자만 입력이 가능합니다.");
        }

        // 0점 제거
        removeZero(scores);

        // 결과 출력
        printScores(scores);

        // 리소스 정리
        input.close();
    }

    private static void readScores(Scanner input, List<Integer> scores) throws NotInBoundException{
        while(true){
            int inputScore = input.nextInt();
            if (inputScore > 100 || inputScore < 0){
                throw new NotInBoundException("0-100사이의 숫자만 입력이 가능합니다.");
            }
            if (inputScore == 0){
                break;
            }
            scores.add(inputScore);
        }
    }

    private static void removeZero(List<Integer> scores) {
        scores.removeIf(score -> score == 0);
    }

    private static void printScores(List<Integer> scores){
        int sum = 0;
        for (int score : scores) {
            System.out.println("score[" + score + "]: " +score);
            sum += score;
        }
        System.out.println("sum: " + sum);
        System.out.println("avg: " + sum/scores.size());
    }
}