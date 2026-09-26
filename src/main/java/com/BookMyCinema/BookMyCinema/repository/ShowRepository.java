package com.BookMyCinema.BookMyCinema.repository;

import com.BookMyCinema.BookMyCinema.model.Show;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowRepository extends JpaRepository<Show, Long> {
}