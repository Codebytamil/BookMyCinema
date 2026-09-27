package com.BookMyCinema.BookMyCinema.controller;

import com.BookMyCinema.BookMyCinema.model.Seat;
import com.BookMyCinema.BookMyCinema.model.Screen;
import com.BookMyCinema.BookMyCinema.repository.ScreenRepository;
import com.BookMyCinema.BookMyCinema.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/seats")
public class SeatController {

    @Autowired
    private SeatService seatService;

    @Autowired
    private ScreenRepository screenRepository;

    @PostMapping
    public Seat addSeat(@RequestBody Seat seat) {
        return seatService.addSeat(seat);
    }

    @GetMapping
    public List<Seat> getAllSeats() {
        return seatService.getAllSeats();
    }

    @GetMapping("/{id}")
    public Seat getSeatById(@PathVariable Long id) {
        return seatService.getSeatById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteSeat(@PathVariable Long id) {
        seatService.deleteSeat(id);
        return "Seat deleted successfully";
    }

    @PostMapping("/generate")
    public List<Seat> generateSeats(@RequestParam Long screenId, @RequestParam int count) {
        Screen screen = screenRepository.findById(screenId).orElse(null);
        return seatService.generateSeats(screenId, count, screen);
    }
}