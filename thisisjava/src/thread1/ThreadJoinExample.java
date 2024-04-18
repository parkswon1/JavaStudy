package thisisjava.src.thread1;

public class ThreadJoinExample {
    static class TaskThread extends Thread{
        private String taskName;

        public TaskThread(String taskName){
            this.taskName = taskName;
        }

        public void run() {
            System.out.println(taskName + "작업 시작"); //실행순서 2(thread1) //실행순서 4(thread4)
            try{
                Thread.sleep(2000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.println(taskName + "작업 완료");
        }
    }

    static class TaskThread2 extends Thread{
        private String taskName;

        public TaskThread2(String taskName){
            this.taskName = taskName;
        }

        public void run() {
            System.out.println(taskName + "작업 시작"); //실행순서 2(thread1) //실행순서 4(thread4)
            try{
                Thread.sleep(10000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.println(taskName + "작업 완료");
        }
    }


    public static void main(String[] args){
        TaskThread task1 = new TaskThread("작업 1");
        TaskThread2 task2 = new TaskThread2("작업 2");

        task1.start(); //실행순서 1
        task2.start(); //실행순서 3
        try {
            task1.join();
            System.out.println("모든 작업의 완료를 기다립니다.");
            task2.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("모든 작업이 완료되었습니다.");
    }
}

