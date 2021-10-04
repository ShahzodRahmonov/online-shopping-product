package com.company.repository;

import com.company.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<PaymentEntity,Integer> {

    List<PaymentEntity> findAllByInvoiceId(Integer id);
}
