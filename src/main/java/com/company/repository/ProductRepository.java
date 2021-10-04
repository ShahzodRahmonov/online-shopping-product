package com.company.repository;

import com.company.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {

    @Query(value = "select * from product p where p.id in (select d.pr_id from detail d where d.quantity >= 8)", nativeQuery = true)
    List<ProductEntity> bulkProducts();

    @Query(value = "select p.name from product p where p.id in (select d.pr_id from detail d where d.ord_id =:ordId)", nativeQuery = true)
    List<String> productsName(@Param("ordId") Integer ordId);
}
