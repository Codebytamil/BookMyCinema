package com.BookMyCinema.BookMyCinema.controller;

import com.BookMyCinema.BookMyCinema.model.Screen;
import com.BookMyCinema.BookMyCinema.service.ScreenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/screens")
public class ScreenController {

    @Autowired
    private ScreenService screenService;

    @PostMapping
    public Screen addScreen(@RequestBody Screen screen) {
        return screenService.addScreen(screen);
    }

    @GetMapping
    public List<Screen> getAllScreens() {
        return screenService.getAllScreens();
    }

    @GetMapping("/{id}")
    public Screen getScreenById(@PathVariable Long id) {
        return screenService.getScreenById(id);
    }
}