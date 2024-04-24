package thisisjava.src.Ramda;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class LambdaExample {
    public static void main(String[] args){
        List<String> items = Arrays.asList("Apple", "Banana", "Cherry");

        items.forEach(i -> System.out.println(i));
        main2();
        main3();
        main4();
        main5();
    }

    public static void main2(){
        List<Integer> numbers = Arrays.asList(5,2,3,1,4);

        Collections.sort(numbers,(Integer a, Integer b) -> {
            return a.compareTo(b);
        });

        numbers.forEach(n -> System.out.println(n));
    }

    @FunctionalInterface
    public interface IntBinaryOperation {
        int apply(int a, int b);
    }

    public static void main3() {
        IntBinaryOperation add = (a, b) -> a + b;
        IntBinaryOperation subtract = (a, b) -> a - b;
        IntBinaryOperation multiply = (a, b) -> a * b;
        IntBinaryOperation divide = (a, b) -> a / b;
        System.out.println("10 + 5 = " + add.apply(10, 5));
        System.out.println("10 - 5 = " + subtract.apply(10, 5));
        System.out.println("10 * 5 = " + multiply.apply(10, 5));
        System.out.println("10 / 5 = " + divide.apply(10, 5));
    }
    public static void main4() {
        int num = 10;

        Runnable r = () -> System.out.println("외부 변수 num의 값은: " + num);

        new Thread(r).start();
    }

    public static void main5(){
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
        Consumer<String> printConsumer = name -> System.out.println(name);
        names.forEach(printConsumer);

        List<Integer> numbers = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        Predicate<Integer> isEven = number -> number % 2 == 0;
        List<Integer> evenNumbers = numbers.stream().filter(isEven).collect(Collectors.toList());
        System.out.println(evenNumbers);
    }
}