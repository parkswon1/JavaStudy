import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();
        int dev = 2;
        int check = 0 ;
        while(dev < input){
            if (input % dev == 0){
                check = 1;
                break;
            }
            dev += 1;
        }
        if (check == 1){
            System.out.println(input + "은 소수가 아닙니다.");
        }else{
            System.out.println(input + "은 소수입니다.");
        }
    }
}