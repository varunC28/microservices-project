package com.upgrad.paymentservice.dao;

import com.upgrad.paymentservice.entities.TransactionDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentDao extends JpaRepository <TransactionDetailsEntity, Integer> {

}
