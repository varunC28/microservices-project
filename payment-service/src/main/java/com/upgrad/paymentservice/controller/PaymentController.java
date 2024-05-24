package com.upgrad.paymentservice.controller;

import com.upgrad.paymentservice.dto.PaymentDTO;
import com.upgrad.paymentservice.entities.TransactionDetailsEntity;
import com.upgrad.paymentservice.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @PostMapping(value = "/transaction")
    public ResponseEntity<Integer> savePayment(@RequestBody PaymentDTO paymentDTO) {
        System.out.println(paymentDTO);
        int transactionId = paymentService.savePayment(paymentDTO);
        return new ResponseEntity<>(transactionId, HttpStatus.CREATED);
    }

    @GetMapping(value = "/transaction/{transactionId}")
    public ResponseEntity<TransactionDetailsEntity> getTransactionDetails(@PathVariable int transactionId){
        System.out.println("transactionId==="+transactionId);
        return(new ResponseEntity<TransactionDetailsEntity>(paymentService.getTransactionDetails(transactionId),HttpStatus.OK));
    }

}
