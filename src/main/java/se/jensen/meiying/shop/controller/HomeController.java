package se.jensen.meiying.shop.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "Mei188 Shop API – version via GitHub Actions! 🚀🚀🚀";
    }
}
