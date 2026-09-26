package com.BookMyCinema.BookMyCinema.service;

import com.BookMyCinema.BookMyCinema.dto.BookingRequest;
import com.BookMyCinema.BookMyCinema.model.*;
import com.BookMyCinema.BookMyCinema.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private BookingSeatRepository bookingSeatRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private SeatRepository seatRepository;

    public Booking createBooking(BookingRequest request) {
        // Step A: fetch the actual User and Show from their ids
        User user = userRepository.findById(request.getUserId()).orElse(null);
        Show show = showRepository.findById(request.getShowId()).orElse(null);

        // Step B: calculate total price = show price * number of seats
        double totalAmount = show.getPrice() * request.getSeatIds().size();

        // Step C: create the Booking itself, status PENDING for now
        Booking booking = new Booking("PENDING", totalAmount, LocalDateTime.now(), user, show);
        booking = bookingRepository.save(booking);

        // Step D: for each seat id sent, create a BookingSeat linking it to this booking
        for (Long seatId : request.getSeatIds()) {
            Seat seat = seatRepository.findById(seatId).orElse(null);
            BookingSeat bookingSeat = new BookingSeat(booking, seat);
            bookingSeatRepository.save(bookingSeat);
        }

        return booking;
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public Booking getBookingById(Long id) {
        return bookingRepository.findById(id).orElse(null);
    }
}