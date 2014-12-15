package io.lincu.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Keeun Baik
 */
@Controller
public class HomeController {

    @RequestMapping("/")
    public String home() {
        return "redirect:/posts";
    }

    @RequestMapping("/index")
    public String index() {
        return "/index";
    }

}
