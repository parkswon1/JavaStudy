package thisisjava.src.stream;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args){
        //stream1();
        //stream2();
        //PeekExam();
        //match();
        //aggregatesStreamExam();
        //reducing();
    }

    private static void reducing() {
        List<Integer> numbers = Arrays.asList(1,2,3,4,5);
        int sum = numbers.stream().reduce(0, (a, b) -> a + b);
        System.out.println(sum);
    }

    private static void aggregatesStreamExam() {
        List<Integer> numbers = Arrays.asList(1,2,3,4,5);

        long count = numbers.stream().count();
        int max = numbers.stream().max(Integer::compareTo).orElse(0);
        int min = numbers.stream().min(Integer::compareTo).orElse(0);
        double average = numbers.stream().mapToInt(Integer::intValue).average().orElse(0);
        int sum = numbers.stream().mapToInt(Integer::intValue).sum();

        System.out.println(count);
        System.out.println(max);
        System.out.println(min);
        System.out.println(average);
        System.out.println(sum);
    }

    private static void match() {
        List<Integer> numbers = Arrays.asList(1,2,3,4,5);
        boolean allPositive = numbers.stream().allMatch(n -> n > 0);
        boolean anyNegative = numbers.stream().anyMatch(n -> n < 0);
        boolean noneAboveTen = numbers.stream().noneMatch(n -> n > 10);
        System.out.println(allPositive);
        System.out.println(anyNegative);
        System.out.println(noneAboveTen);
    }

    private static void PeekExam() {
        List<Integer> numbers = Arrays.asList(1,2,3,4,5);
        numbers.stream().forEach(n -> System.out.println("Number: " + n));
        List<Integer> doubledNumbers = numbers.stream().peek(n -> System.out.println("Processing 1: " + n))
                .map(n -> n * 2)
                .peek(n -> System.out.println("Processing 2:" + n))
                .collect(Collectors.toList());
    }

    private static void stream2() {
        List<String> fruits = Arrays.asList("Banana", "Apple", "Cherry", "Date");
        List<String> sortedFruits = fruits.stream().sorted().collect(Collectors.toList());
        System.out.println(sortedFruits);
        List<String> reverserSortedFruits = fruits.stream().sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
        System.out.println(reverserSortedFruits);
    }

    private static void stream1() {
        List<List<String>> nestedList = Arrays.asList(
                Arrays.asList("Apple", "Banana"),
                Arrays.asList("Cherry", "Date")
        );
        nestedList.stream()
                .flatMap(Collection::stream)
                .forEach(System.out::println);
    }
}
