import java.util.Random;
import java.util.Scanner;

public class HW0313 {
    public static void main(String[] args){
        Scanner  scanner = new Scanner(System.in);
        System.out.println("실습문제 번호를 입력하세요");
        int HWnum = scanner.nextInt();
        Random random = new Random();
        switch (HWnum){
            case 1 :
                case1(scanner);
                break;
            case 2 :
                case2(scanner);
                break;
            case 3 :
                case3();
                break;
            case 4 :
                case4(scanner);
                break;
            case 5 :
                case5(scanner,random);
                break;
            case 6 :
                case6(scanner);
                break;
            default:
                System.out.println("다른값으로 입력하세요");
        }
    }

    private static void case6(Scanner scanner) {
        int output = 0;
        int input = 0;
        do {
            input = scanner.nextInt();
            output += input;
        } while(input != 0);
        System.out.println(output);
    }

    private static void case5(Scanner scanner, Random random) {
        int whatnum = random.nextInt(100) + 1;
        int count = 0;
        while(true){
            System.out.println("숫자 입력해보세요 " + whatnum);
            count += 1;

            int input = scanner.nextInt();
            if (input < whatnum){
                System.out.println("입력값이 더 작습니다. " + count + "번 입력하셨습니다.");
            }
            else if (input == whatnum){
                System.out.println("정답입니다.");
                break;
            }
            else{
                System.out.println("입력값이 더 큽니다. " + count + "번 입력하셨습니다.");
            }
        }
    }

    private static void case4(Scanner scanner) {
        int n = scanner.nextInt();
        int output = 1;
        for (int index = 1; index <= n; index++){
            output *= index;
        }
        System.out.println(n + "!"+ " = " + output);
    }

    private static void case3() {
        int i = 2;
        while(i < 10){
            int j = 1;
            while(j < 10){
                System.out.println(i + " * " + j + " = " + (i*j));
                j ++;
            }
            i++;
            System.out.println();
        }
    }

    private static void case1(Scanner scanner) {
        int C = scanner.nextInt();

        System.out.println(C * 9/5 + 32);
    }

    private static void case2(Scanner scanner) {
        int input = scanner.nextInt();

        if (input % 2 == 0){
            System.out.println(input + "은 짝수입니다.");
        }
        else if (input == 0) {
            System.out.println("0입니다.");
        }
        else{
            System.out.println(input + "은 홀수 입니다.");
        }
    }
}
