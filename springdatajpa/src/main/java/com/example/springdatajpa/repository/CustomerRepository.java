package com.example.springdatajpa.repository;

import com.example.springdatajpa.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    // 이름으로 고객 조회
    @Query("SELECT c From Customer c where c.name = :name")
    List<Customer> findALLByName(@Param("name") String name);

    // 이메일이 특정 문자열을 포함하는 고객 조회
    @Query("SELECT c FROM Customer c WHERE c.email LIKE %:email%")
    List<Customer> findAllByString(@Param("email") String string);

    // 고객과 그들의 주문 수 계산
    @Query("SELECT c, count(o) FROM Customer c LEFT JOIN c.orders o GROUP BY c")
    List<Object[]> findCustomerOrderCount();

    // 고객의 세부 정보와 그들의 가장 최근 주문 조회
    @Query("SELECT c, o FROM Customer c JOIN c.orders o WHERE o.date = (SELECT MAX(o2.date) FROM Order o2 WHERE o2.customer = c)")
    List<Object[]> findCustomersWithLatestOrder();

    // 평균 나이보다 많은 고객 조회
    @Query("SELECT c FROM Customer c WHERE c.age > (SELECT AVG(c2.age) FROM Customer c2)")
    List<Customer> findCustomersOlderThanAverage();


}
