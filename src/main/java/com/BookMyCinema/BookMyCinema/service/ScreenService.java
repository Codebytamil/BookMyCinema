package com.BookMyCinema.BookMyCinema.service;

import com.BookMyCinema.BookMyCinema.model.Screen;
import com.BookMyCinema.BookMyCinema.repository.ScreenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScreenService {

    @Autowired
    private ScreenRepository screenRepository;

    public Screen addScreen(Screen screen) {
        return screenRepository.save(screen);
    }

    public List<Screen> getAllScreens() {
        return screenRepository.findAll();
    }

    public Screen getScreenById(Long id) {
        return screenRepository.findById(id).orElse(null);
    }
}