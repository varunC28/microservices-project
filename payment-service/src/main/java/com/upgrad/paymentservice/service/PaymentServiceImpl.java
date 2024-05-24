package com.upgrad.paymentservice.service;

import com.upgrad.paymentservice.dao.PaymentDao;
import com.upgrad.paymentservice.dto.PaymentDTO;
import com.upgrad.paymentservice.entities.TransactionDetailsEntity;
import com.upgrad.paymentservice.utils.POJOConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    PaymentDao paymentDao;
    public Integer savePayment(PaymentDTO paymentDTO ){
        TransactionDetailsEntity paymentEntity= paymentDao.save(POJOConverter.covertPaymentDTOToEntity(paymentDTO));
        return (paymentEntity.getTransactionId());
    }

    @Override
    public TransactionDetailsEntity getTransactionDetails(int transactionId) {
        Optional<TransactionDetailsEntity> paymentEntityInfo =paymentDao.findById(transactionId);
        if(paymentEntityInfo.isPresent()) {
            return paymentEntityInfo.get();
        }
        return null;
    }

}
