package com.company.repository;

import com.company.entity.InvoiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface InvoiceRepository extends JpaRepository<InvoiceEntity,Integer> {

    List<InvoiceEntity> findByDueBefore(LocalDateTime time);
    Optional<InvoiceEntity> findByOrderId(Integer id);

    @Query(value = "select * from invoice i where i.id in (select inv_id from payment p group by p.inv_id having count(p.inv_id) > 1)",nativeQuery=true)
    List<InvoiceEntity> overpaidInvoices();
}
