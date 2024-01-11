package OthersOOP.src;

class Print{
    public static void printLine() {
        System.out.println("----");
    }

    public static void extracted(String input) {
        System.out.println(input);
        System.out.println(input);
    }
}
public class MyOOP {
    public static void main(String[] args) {

        Print.printLine();
        Print.extracted("A");

        Print.printLine();
        Print.extracted("B");
    }
}
