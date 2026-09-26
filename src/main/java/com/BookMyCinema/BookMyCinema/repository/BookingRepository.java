package com.BookMyCinema.BookMyCinema.repository;

import com.BookMyCinema.BookMyCinema.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}