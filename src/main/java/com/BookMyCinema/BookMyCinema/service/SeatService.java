package com.BookMyCinema.BookMyCinema.service;

import com.BookMyCinema.BookMyCinema.model.Seat;
import com.BookMyCinema.BookMyCinema.model.Screen;
import com.BookMyCinema.BookMyCinema.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SeatService {

    @Autowired
    private SeatRepository seatRepository;

    public Seat addSeat(Seat seat) {
        return seatRepository.save(seat);
    }

    public List<Seat> getAllSeats() {
        return seatRepository.findAll();
    }

    public Seat getSeatById(Long id) {
        return seatRepository.findById(id).orElse(null);
    }

    public void deleteSeat(Long id) {
        seatRepository.deleteById(id);
    }

    public List<Seat> generateSeats(Long screenId, int count, Screen screen) {
        List<Seat> createdSeats = new ArrayList<>();
        int seatsPerRow = 20;
        char row = 'A';
        int seatNum = 1;

        for (int i = 0; i < count; i++) {
            if (seatNum > seatsPerRow) {
                row++;
                seatNum = 1;
            }
            Seat seat = new Seat(row + "" + seatNum, "REGULAR", screen);
            createdSeats.add(seatRepository.save(seat));
            seatNum++;
        }
        return createdSeats;
    }
}