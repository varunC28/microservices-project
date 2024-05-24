package com.upgrad.bookingservice.controller;

import com.upgrad.bookingservice.dto.BookingDTO;
import com.upgrad.bookingservice.dto.PaymentDTO;
import com.upgrad.bookingservice.entities.BookingInfoEntity;
import com.upgrad.bookingservice.exceptions.ExceptionDTO;
import com.upgrad.bookingservice.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hotel")
public class BookingController{
    @Autowired
    BookingService bookingService;



    @PostMapping(value = "/booking")
    public ResponseEntity<?> createBooking(@RequestBody BookingDTO bookingDTO) {
        return new ResponseEntity<>(bookingService.createBooking(bookingDTO), HttpStatus.CREATED);
    }

    @PostMapping(value = "/booking/{bookingId}/transaction" )
    public ResponseEntity<?> payment(@PathVariable int bookingId, @RequestBody PaymentDTO paymentDTO ) {
        //      System.out.println("bookingId==="+bookingId);
        try {

            return  (new ResponseEntity<BookingInfoEntity>(bookingService.savePayment(bookingId, paymentDTO), HttpStatus.CREATED));
        }catch(Exception e) {
            return   (new ResponseEntity<ExceptionDTO>((new ExceptionDTO(e.getMessage(), "400")),HttpStatus.BAD_REQUEST));
        }
    }


}
