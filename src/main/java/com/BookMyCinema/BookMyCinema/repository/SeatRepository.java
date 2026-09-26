package com.BookMyCinema.BookMyCinema.repository;

import com.BookMyCinema.BookMyCinema.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<Seat, Long> {
}