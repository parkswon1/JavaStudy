public class CarExample {
    public static void main(String[] args){
        Car car1 = new Car();
        System.out.println(car1.model);

        Car car2 = new Car("자가용","검정",100);
        System.out.println(car2.model);

        Car care = new Car("자가용","검정");
        System.out.println(care.model);
    }
}
