package com.BookMyCinema.BookMyCinema.repository;

import com.BookMyCinema.BookMyCinema.model.BookingSeat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingSeatRepository extends JpaRepository<BookingSeat, Long> {
}