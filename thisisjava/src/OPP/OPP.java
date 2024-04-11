package thisisjava.src.OPP;

import java.util.Scanner;

public class OPP {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println("어떤걸 드시겠어요?\n1. 피자 2. 햄버거");
        int orderNum = scanner.nextInt();
        paymentOrder paymentOrder = new paymentOrder();
        int price = 0;
        switch (orderNum){
            case 1:
                PizzaOrder pizzaOrder = new PizzaOrder();
                price = pizzaOrder.completeOrder();
                break;
            case 2:
                BurgerOrder burgerOrder = new BurgerOrder();
                price = burgerOrder.completeOrder();
                break;
            default:
                break;
        }
        paymentOrder.payment(price);
        System.out.println("감사합니다. 안녕히가세요");
    }

    public abstract static class Order{
        abstract void prepareFood();
        abstract void serveFood();
        abstract int priceReturn();
        public void completeOrderPrinter(){
            System.out.println("음식을 먹는 중입니다.");
        }

        final int completeOrder(){
            prepareFood();
            serveFood();
            completeOrderPrinter();
            return priceReturn();
        }
    }

    public interface Payment{
        void processPayment(int price);
    }

    public static class PizzaOrder extends Order{


        void prepareFood() {
            System.out.println("피자를 굽는 중입니다.");
        }


        void serveFood() {
            System.out.println("치즈가 잘늘어나는 피자가 나왔습니다.");
        }

        int priceReturn(){
            return 20000;
        }
    }

    public static class BurgerOrder extends Order{

        void prepareFood() {
            System.out.println("햄버거의 층을 쌓는 중입니다.");
        }

        void serveFood() {
            System.out.println("햄버거에 꽂힌 깃발이 휘날리며 나왔습니다.");
        }

        int priceReturn(){
            return 12000;
        }
    }

    public static class CreditPayment implements Payment {

        public void processPayment(int price){
            System.out.println(price + "원 신용카드 결제를 처리합니다.");
        }
    }

    public static class CashPayment implements Payment {

        public void processPayment(int price){
            System.out.println(price + "원 현금 결제를 처리합니다.");
        }
    }

    public static class paymentOrder{
        void payment(int price){
            System.out.println(price + "원 결제는 어떤걸로 하시겠어요? \n1. 카드 2. 현금");
            int paymentNum = scanner.nextInt();

            switch (paymentNum){
                case 1:
                    CreditPayment creditPayment = new CreditPayment();
                    creditPayment.processPayment(price);
                    break;
                case 2:
                    CashPayment cashPayment = new CashPayment();
                    cashPayment.processPayment(price);
                    break;
                default:
                    break;
            }
        }
    }
}
