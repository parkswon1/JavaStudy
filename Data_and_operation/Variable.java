
public class Variable {

	public static void main(String[] args) {
        Child childO = new Child();

        // 자식 클래스에서 오버라이딩한 메서드 호출
        childO.display(); // 출력: Child class: 10

        // 자식 클래스에서 멤버 변수 변경
        childO.changeValue();

        // 변경된 값을 출력
        childO.display(); // 출력: Child class: 20
	}

}

class Parent {
    int value = 10;

    void display() {
        System.out.println("Parent class: " + value);
    }
}

class Child extends Parent {
    // 부모 클래스에서 상속받은 메서드를 오버라이딩
    @Override
    void display() {
        System.out.println("Child class: " + value);
    }

    // 부모 클래스에서 상속받은 멤버 변수를 변경
    void changeValue() {
        value = 20;
    }
}