package com.example.jpa.School;

import com.example.jpa.JPAUtil;
import jakarta.persistence.EntityManager;

public class schoolMain {
    public static void main(String[] args){
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();

        try {
            School school = new School("Lion school");

            Student student1 = new Student("박", school);
            Student student2 = new Student("석", school);
            Student student3 = new Student("원", school);

            entityManager.persist(school);
            entityManager.getTransaction().commit();

        }finally {
            entityManager.close();
        }
    }
}
