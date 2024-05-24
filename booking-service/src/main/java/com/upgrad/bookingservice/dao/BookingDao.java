package com.upgrad.bookingservice.dao;

import com.upgrad.bookingservice.entities.BookingInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingDao extends JpaRepository<BookingInfoEntity, Integer> {
}
