package com.upgrad.paymentservice.utils;

import com.upgrad.paymentservice.dto.PaymentDTO;
import com.upgrad.paymentservice.entities.TransactionDetailsEntity;


    public class POJOConverter {
        public static PaymentDTO covertPaymentEntityToDTO(TransactionDetailsEntity paymentEntity) {
            PaymentDTO paymentDTO = new PaymentDTO();
            paymentDTO.setPaymentMode(paymentEntity.getPaymentMode());
            paymentDTO.setBookingId(paymentEntity.getBookingId());
            paymentDTO.setCardNumber(paymentEntity.getCardNumber());
            paymentDTO.setUpiId(paymentEntity.getUpiID());
            return paymentDTO;
        }

        public static TransactionDetailsEntity covertPaymentDTOToEntity(PaymentDTO paymentDTO) {
            TransactionDetailsEntity paymentEntity = new TransactionDetailsEntity();
            paymentEntity.setPaymentMode(paymentDTO.getPaymentMode());
            paymentEntity.setBookingId(paymentDTO.getBookingId());
            paymentEntity.setCardNumber(paymentDTO.getCardNumber());
            paymentEntity.setUpiID(paymentDTO.getUpiId());
            return paymentEntity;
        }
    }
