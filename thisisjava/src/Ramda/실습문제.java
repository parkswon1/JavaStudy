package thisisjava.src.Ramda;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

public class 실습문제 {
    public static void main(String[] args){
        stringSort();
        searchBigNumber();
        plusTen();
        printBiggerThanFive();
        square();
        checkEven();
    }

    //문제 1 문자열 리스트 정렬하기
    private static void stringSort() {
        List<String> koreanString= Arrays.asList("가", "다", "나", "라");
        Collections.sort(koreanString,(String a, String b) ->{
            return a.compareTo(b);
        });
        koreanString.forEach(System.out::println);
    }

    //문제 2 최대값 찾기
    private static void searchBigNumber(){
        Integer[] numbers = {1,2,3,4,5};
        Comparator<Integer> comparator = (Integer a, Integer b) -> b - a;
        Arrays.sort(numbers, comparator);
        System.out.println(numbers[0]);
    }

    //문제 3 리스트의 각 요소에 연산 적용하기
    private static void plusTen(){
        List<Integer> numbers = Arrays.asList(1,2,3,4,5,6);
        List<Integer> plusNumbers = new ArrayList<>();
        numbers.forEach(num -> plusNumbers.add(num + 10));
        numbers.forEach(num -> System.out.print(num +" "));
        System.out.println();
    }

    //문제 4 조건에 맞는 요소 찾기
    private static void printBiggerThanFive() {
        List<String> strings = Arrays.asList("가","나","다라마바사","아","자","차카타파하아에이오우");
        Predicate<String> check = s -> s.length() >= 5;
        for(String s : strings){
            if(check.test(s)){
                System.out.println(s);
                return;
            }
        }
    }

    //문제 5 요소 변환하기
    private static void square() {
        List<Integer> integers = Arrays.asList(1,2,3,4,5);
        System.out.println("원래 배열: " + integers);
        Function<Integer,Integer> apply = (a) -> a*a;
        for(int i = 0; i < integers.size(); i++){
            integers.set(i, apply.apply(integers.get(i)));
        }
        System.out.println("변환 배열: " + integers);
    }

    //문제 6 짝수인지 확인하기
    private static void checkEven() {
        List<Integer> numbers = Arrays.asList(1,2,3,4,5);
        Predicate<Integer> check = num -> num % 2 == 0;
        String word = "";
        for(int num: numbers){
            if(check.test(num)){
                word = "짝수";
            }else{
                word = "홀수";
            }
            System.out.println(num + "은" + word + "입니다.");
        }
    }
}