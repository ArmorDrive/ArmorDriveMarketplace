package ua.com.marketplace.armordrive.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class HomeController {
    @GetMapping("/api/welcome_message")
    @ResponseBody
    public String index() {
        return "Welcome to Armor Drive Marketplace!!!";
    }

}
