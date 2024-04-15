package thisisjava.src.Generic;

import java.util.ArrayList;
import java.util.Scanner;

public class WordCollectionExam {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args){
        ArrayList<String> arrayList = new ArrayList<>();

        input(arrayList);
        print(arrayList);
    }

    public static void input(ArrayList<String> arrayList){
        System.out.println("단어를 입력하세요. quit을 입력하면 종료됩니다.");
        while(true){
            String str = scanner.next();
            if (str.equals("quit")){
                break;
            }else{
                arrayList.add(str);
            }
        }
    }

    public static void print(ArrayList<String> arrayList){
        int countChar = 0;
        int maxSize = 0;
        String maxSizeString = null;
        for (String str : arrayList){
            System.out.println(str);
            countChar = countingStringWithoutBlank(str, countChar);

            if (str.length() > maxSize){
                maxSize = str.length();
                maxSizeString = str;
            }
        }
        System.out.println("모든 단어의 개수: " + arrayList.size() + "\n모든 글자 수의 합: " + countChar);
        System.out.println("가장 긴 단어: " + maxSizeString + "\n길이: " + maxSize);
    }

    private static int countingStringWithoutBlank(String str, int countChar) {
        for (int i = 0; i < str.length(); i++){
            if (str.charAt(i) != ' '){
                countChar += 1;
            }
        }
        return countChar;
    }
}