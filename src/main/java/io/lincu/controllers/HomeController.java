package io.lincu.controllers;

import io.lincu.domains.Account;
import io.lincu.domains.Content;
import io.lincu.repositories.AccountRepository;
import io.lincu.repositories.ContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

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
