package com.BookMyCinema.BookMyCinema.model;

import jakarta.persistence.*;

@Entity
@Table(name = "seats")
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String seatNumber; // e.g. "A1", "B5"
    private String seatType;   // "REGULAR" or "PREMIUM"

    @ManyToOne
    @JoinColumn(name = "screen_id")
    private Screen screen;

    public Seat() {
    }

    public Seat(String seatNumber, String seatType, Screen screen) {
        this.seatNumber = seatNumber;
        this.seatType = seatType;
        this.screen = screen;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getSeatType() {
        return seatType;
    }

    public void setSeatType(String seatType) {
        this.seatType = seatType;
    }

    public Screen getScreen() {
        return screen;
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
    }
}