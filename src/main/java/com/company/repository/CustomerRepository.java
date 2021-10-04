package com.company.repository;

import com.company.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.lang.annotation.Native;
import java.util.List;
import java.util.Optional;
public interface CustomerRepository extends JpaRepository<CustomerEntity,Integer> {

    Optional<CustomerEntity> findByPhone(String phone);

    @Query(value = "select * from customer c where c.id not in(select cust_id from orders o where date_part('year',o.created_date) = 2016)",nativeQuery=true)
    List<CustomerEntity> customersWithoutOrders();

    @Query(value = "select c.name from customer c where c.id =:id", nativeQuery=true)
    String name(@Param("id") Integer id);
}
