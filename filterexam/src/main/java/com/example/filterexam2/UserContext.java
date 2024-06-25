package com.example.filterexam2;


public class UserContext {
    private static final ThreadLocal<User> USER_THREAD_LOCAL = ThreadLocal.withInitial(()->null);

    public static void setUser(User user){
        USER_THREAD_LOCAL.set(user);
    }
    public static User getUser(){
        return USER_THREAD_LOCAL.get();
    }

    public static void clear(){
        USER_THREAD_LOCAL.remove();
    }
}
