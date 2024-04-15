package thisisjava.src.Generic;

public class NonGenericPair {
    private Object first;
    private Object second;

    public NonGenericPair(Object first, Object second){
        this.first = first;
        this.second = second;
    }

    public Object getFirst(){
        return first;
    }

    public Object getSecond(){
        return second;
    }

    public void setFirst(Object first){
        this.first = first;
    }

    public void setSecond(Object second){
        this.second = second;
    }


    public static void main(String[] args){
        NonGenericPair pair = new NonGenericPair("Hello", 5);
        Object first = pair.getFirst();
        System.out.println(first);
    }
}
