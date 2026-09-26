package com.BookMyCinema.BookMyCinema.service;

import com.BookMyCinema.BookMyCinema.model.Movie;
import com.BookMyCinema.BookMyCinema.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public Movie addMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public Movie getMovieById(Long id) {
        return movieRepository.findById(id).orElse(null);
    }

    // NEW: delete a movie by id
    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);
    }
}