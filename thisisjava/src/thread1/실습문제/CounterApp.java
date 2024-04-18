package thisisjava.src.thread1.실습문제;

import java.util.Random;

public class CounterApp {
    public static void main(String[] args) {
        Random random = new Random(); //각 thread마다 랜덤 sleep 난수 생성
        int increseRandomNum = random.nextInt(11);
        int DecreseRandomNum = random.nextInt(11);
        System.out.println("increseRandomNum1: " + increseRandomNum);
        System.out.println("increseRandomNum2: " + DecreseRandomNum);


        Thread incrementThread = new Thread(new IncrementCounter(increseRandomNum));
        Thread decrementThread = new Thread(new DecrementCounter(DecreseRandomNum));

        incrementThread.start();
        decrementThread.start();
    }

    static class IncrementCounter implements Runnable{
        private int randomNum;

        IncrementCounter(int randomNum){
            this.randomNum = randomNum;
        }

        @Override
        public void run() {
            for (int i = 1; i < 101; i++){
                try{
                    Thread.sleep(randomNum);
                    System.out.println("Increment: " + i);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }

    static class DecrementCounter implements Runnable{
        private int randomNum;

        DecrementCounter(int randomNum){
            this.randomNum = randomNum;
        }

        @Override
        public void run() {
            for (int i = 100; i > 0; i--){
                try{
                    Thread.sleep(randomNum);
                    System.out.println("Decrement: " + i);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }
}