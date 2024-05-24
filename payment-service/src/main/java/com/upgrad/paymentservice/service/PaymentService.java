package com.upgrad.paymentservice.service;

import com.upgrad.paymentservice.dao.PaymentDao;
import com.upgrad.paymentservice.dto.PaymentDTO;
import com.upgrad.paymentservice.entities.TransactionDetailsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public interface PaymentService {


    public Integer savePayment(PaymentDTO paymentDTO );

    public TransactionDetailsEntity getTransactionDetails(int transactionId);
}

