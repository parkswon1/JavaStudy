public class Main {
    public static void main(String[] args){
        factorial1(1,1);
        System.out.println(factorial2(5));
    }
    static void factorial1(int x, int output){
        if (x > 5){
            System.out.println(output);
            return;
        }
        int next_output = output * x;
        int next_x = x + 1;
        factorial1(next_x, next_output);
    }

    public static int factorial2(int x) {
        if (x == 1) {
            return 1;
        }
        int next_x = x - 1;
        return x * factorial2(next_x);
    }
}