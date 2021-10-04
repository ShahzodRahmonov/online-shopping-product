package com.company.repository;

import com.company.entity.DetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DetailRepository extends JpaRepository<DetailEntity,Integer> {

    @Query(value = "select d.pr_id, count(d.ord_id), sum(d.quantity) from detail d where d.ord_id in (select o.id from orders o where o.order_status = 'SUCCESS') group by d.pr_id having count(d.ord_id) > 10",nativeQuery=true)
    List<Object> highDemandProducts();

    List<DetailEntity> findByOrderId(Integer ordId);
}
