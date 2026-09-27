package com.BookMyCinema.BookMyCinema.service;

import com.BookMyCinema.BookMyCinema.dto.BookingRequest;
import com.BookMyCinema.BookMyCinema.model.*;
import com.BookMyCinema.BookMyCinema.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public Booking createBooking(BookingRequest request) {
        User user = userRepository.findById(request.getUserId()).orElse(null);
        Show show = showRepository.findById(request.getShowId()).orElse(null);

        // Lock the seats while we check + book them
        List<Seat> seats = seatRepository.findSeatsForBooking(request.getSeatIds());

        // Check if ANY of these seats are already booked
        for (Seat seat : seats) {
            if (seat.isBooked()) {
                throw new RuntimeException("Seat " + seat.getSeatNumber() + " is already booked");
            }
        }

        // Mark all requested seats as booked
        for (Seat seat : seats) {
            seat.setBooked(true);
            seatRepository.save(seat);
        }

        double totalAmount = show.getPrice() * seats.size();
        Booking booking = new Booking("CONFIRMED", totalAmount, LocalDateTime.now(), user, show);
        booking = bookingRepository.save(booking);

        for (Seat seat : seats) {
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