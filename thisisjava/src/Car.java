public class Car {
    private int count;

    public Car() {
        this.count = 0;
    }

    public void increment() {
        this.count++; // "this"를 사용하여 현재 객체의 변수에 접근
    }

    public void reset() {
        this.count = 0; // "this"를 사용하여 현재 객체의 변수에 접근
    }

    public int count(){
        return this.count;
    }
}
