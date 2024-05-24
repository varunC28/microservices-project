package com.upgrad.bookingservice.utils;

import com.upgrad.bookingservice.dto.BookingDTO;
import com.upgrad.bookingservice.entities.BookingInfoEntity;

public class POJOconvertor {

        public static BookingDTO covertBookingEntityToDTO(BookingInfoEntity bookingEntity) {
            BookingDTO bookingDTO = new BookingDTO();
            bookingDTO.setAadharNumber(bookingEntity.getAadharNumber());
            bookingDTO.setFromDate(bookingEntity.getFromDate());
            bookingDTO.setToDate(bookingEntity.getToDate());
            bookingDTO.setNumOfRooms(bookingEntity.getNumOfRooms());
            return bookingDTO;
        }

        public static BookingInfoEntity covertBookingDTOToEntity(BookingDTO bookingDTO) {
            BookingInfoEntity bookingEntity = new BookingInfoEntity();
            bookingEntity.setAadharNumber(bookingDTO.getAadharNumber());
            bookingEntity.setFromDate(bookingDTO.getFromDate());
            bookingEntity.setToDate(bookingDTO.getToDate());
            bookingEntity.setNumOfRooms(bookingDTO.getNumOfRooms());
            return bookingEntity;
        }
}
