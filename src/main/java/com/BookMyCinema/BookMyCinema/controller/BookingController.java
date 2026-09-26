package com.BookMyCinema.BookMyCinema.controller;

import com.BookMyCinema.BookMyCinema.dto.BookingRequest;
import com.BookMyCinema.BookMyCinema.model.Booking;
import com.BookMyCinema.BookMyCinema.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping
    public Booking createBooking(@RequestBody BookingRequest request) {
        return bookingService.createBooking(request);
    }

    @GetMapping
    public List<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }

    @GetMapping("/{id}")
    public Booking getBookingById(@PathVariable Long id) {
        return bookingService.getBookingById(id);
    }
}