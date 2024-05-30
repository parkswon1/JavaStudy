package com.example.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class UserDAO {
    private EntityManagerFactory emf;

    public UserDAO(){
        emf = Persistence.createEntityManagerFactory("UserPU");
    }

    public void close(){
        if(emf != null)
            emf.close();
    }

//    public void createUser(User user){
//        EntityManager em = emf.createEntityManager();
//        try{
//            em.getTransaction().begin();
//            em.persist(user);
//            em.getTransaction().commit();
//        }finally {
//            em.close();
//        }
//    }

    public User findUser(Long id){
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        try{
            User user = em.find(User.class, id);
            em.close();
            return user;
        }finally {
            em.close();
        }
    }

    public void createUser(User user){
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        try{
            entityManager.getTransaction().begin();

            entityManager.persist(user);

            entityManager.getTransaction().commit();
        }finally {
            entityManager.close();
        }
    }
}
