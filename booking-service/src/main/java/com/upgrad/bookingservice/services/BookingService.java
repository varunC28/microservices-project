package com.upgrad.bookingservice.services;

import com.upgrad.bookingservice.dto.BookingDTO;
import com.upgrad.bookingservice.dto.PaymentDTO;
import com.upgrad.bookingservice.entities.BookingInfoEntity;
import org.springframework.stereotype.Service;

@Service
public interface BookingService {


    public BookingInfoEntity createBooking(BookingDTO bookingDTO);

    public BookingInfoEntity savePayment(int bookingId, PaymentDTO paymentDTO) throws Exception;
}