package com.stonegame.leaderboard.controllers;

import com.stonegame.leaderboard.services.MainService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    private final MainService service;

    public MainController(MainService service) {
        this.service = service;
    }

    @GetMapping
    public String top(Model model) {
        model.addAttribute("top", service.getTop());
        return "leaderboard";
    }

}
