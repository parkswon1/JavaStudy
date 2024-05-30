package com.example.jpa;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAUtil {
    private static final EntityManagerFactory emfInstance =
            Persistence.createEntityManagerFactory("UserPU");

    static {
        Runtime.getRuntime().addShutdownHook(new Thread(()->{
            if(emfInstance != null){
                System.out.println("------------emf close ----------------");
                emfInstance.close();
            }
        }));
    }

    private JPAUtil(){}

    public static EntityManagerFactory getEntityManagerFactory(){
        return emfInstance;
    }
}
