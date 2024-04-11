package thisisjava.src.filed;

public class Main {
    public static void main(String[] args){
        Person person = new Person(1001, "John Doe");
        System.out.println("사람의 ID: " + person.getId());
        System.out.println("사람의 이름: " + person.getName());
        person.setName("Jane");
        System.out.println("변경된 이름: " + person.getName());
    }
}
