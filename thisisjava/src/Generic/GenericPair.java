package thisisjava.src.Generic;

public class GenericPair <T1, T2>{
    private T1 first;
    private T2 second;

    public GenericPair(T1 first, T2 second){
        this.first = first;
        this.second = second;
    }

    public T1 getFirst(){
        return first;
    }

    public T2 getSecond(){
        return second;
    }

    public void setFirst(T1 first){
        this.first = first;
    }

    public void setSecond(T2 second){
        this.second = second;
    }

    public static void main(String[] args){
        GenericPair<String, Integer> pair = new GenericPair("Hello", 5);
        String first = pair.getFirst();
        System.out.println(first);
    }
}

