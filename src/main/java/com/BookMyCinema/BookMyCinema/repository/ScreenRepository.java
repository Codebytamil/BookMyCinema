package com.BookMyCinema.BookMyCinema.repository;

import com.BookMyCinema.BookMyCinema.model.Screen;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScreenRepository extends JpaRepository<Screen, Long> {
}