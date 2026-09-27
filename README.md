# 🎬 BookMyCinema

A full-stack movie ticket booking system built with Spring Boot, MySQL, and vanilla JavaScript — featuring real-world backend engineering: relational data modeling, concurrency-safe seat booking, authentication-ready security, and cloud deployment.

**Live API:** https://bookmycinema-tpc6.onrender.com/api/movies 
*(Hosted on Render's free tier — first request may take 30-60 seconds to wake up)*

## Features

- **Relational data model** across 7 entities (User, Movie, Theatre, Screen, Seat, Show, Booking) with proper one-to-many relationships
- **Concurrency-safe booking**: uses pessimistic database locking (`SELECT ... FOR UPDATE`) combined with `@Transactional` to prevent two users from double-booking the same seat
- **Secure password storage** with BCrypt hashing (Spring Security)
- **Input validation** on API requests (`@Valid`, `@NotBlank`, `@Email`)
- **DTO pattern** for handling multi-seat booking requests cleanly
- **Deployed** on Render (backend) with a MySQL database on Railway, connected via environment variables
- **Simple JavaScript frontend** demonstrating the full booking flow end-to-end

## Tech Stack

- **Backend:** Java 17, Spring Boot 3, Spring Data JPA, Spring Security
- **Database:** MySQL (Railway, cloud-hosted)
- **Deployment:** Docker + Render
- **Frontend:** HTML/CSS/JavaScript (vanilla, calls REST API directly)

## Architecture

Controller → Service → Repository → Database


Each entity follows this layered structure. Key relationships:
- Theatre → has many Screens
- Screen → has many Seats, hosts many Shows
- Movie → has many Shows
- Booking → linked to one User, one Show, and many Seats (via `BookingSeat` join table)

## API Endpoints (sample)

| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/users` | Register a new user (password hashed) |
| GET | `/api/movies` | List all movies |
| POST | `/api/shows` | Create a show (links movie + screen) |
| POST | `/api/bookings` | Book seats (with concurrency protection) |
| GET | `/api/seats` | List all seats with booked status |

## Key Engineering Challenge: Preventing Double-Booking

When two users try to book the same seat simultaneously, a naive implementation would allow both bookings to succeed — corrupting the seat inventory. This project solves it with:

```java
@Lock(LockModeType.PESSIMISTIC_WRITE)
@Query("SELECT s FROM Seat s WHERE s.id IN :seatIds")
List<Seat> findSeatsForBooking(List<Long> seatIds);
```

combined with `@Transactional` on the booking method — the database locks the seat rows for the duration of the transaction, so a second concurrent booking request must wait, then correctly sees the seat as already booked, and the booking is rejected with a clear error.

## Running Locally

1. Clone the repo
2. Set up a local MySQL database
3. Configure `application.properties` with your DB credentials
4. Run `BookMyCinemaApplication.java`
5. Test endpoints via Postman on `http://localhost:8080`

## Author

Tamilarasan Appadurai — [GitHub](https://github.com/Codebytamil)