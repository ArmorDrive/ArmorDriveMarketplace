package ua.com.marketplace.armordrive.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class HomeController {
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/")
    @ResponseBody
    public String index() {
        return "Welcome to Armor Drive Marketplace!!!!!";
    }

}
