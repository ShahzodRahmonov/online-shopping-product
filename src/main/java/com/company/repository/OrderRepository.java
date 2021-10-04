package com.company.repository;

import com.company.dto.CountCountry;
import com.company.entity.OrderEntity;
import com.company.enums.OrderDetailStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<OrderEntity,Integer> {

    List<OrderEntity> findAllByDateBeforeAndDetailStatus(LocalDateTime date, OrderDetailStatus status);

    @Query(value = "select * from orders where cust_id =:customerId order by created_date desc limit 1", nativeQuery = true)
    Optional<OrderEntity> customersLastOrders(@Param("customerId") Integer customerId);

    @Query(value = "select distinct o.cust_id from orders o", nativeQuery = true)
    List<Integer> customerIdList();

    @Query(value = "select c.country, count(o.id) from customer c join orders o on c.id = o.cust_id where date_part('year',o.created_date) = 2016 group by c.country having count(o.id) > 0", nativeQuery = true)
    List<Object> countryCountOrders();

    @Query(value = "select o.id, o.created_date, d.quantity, p.price from detail d join orders o on d.ord_id = o.id join product p on d.pr_id = p.id where d.ord_id in (select ord.id from orders ord where ord.id not in (select i.ord_id from invoice i) and ord.detail_status = 'YES')", nativeQuery = true)
    List<Object> ordersWithoutInvoices();
    
}
