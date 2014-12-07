package io.lincu.controllers;

import io.lincu.domains.Account;
import io.lincu.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author Keeun Baik
 */
@Controller
public class HomeController {

    @Autowired
    AccountRepository accountRepository;

    @RequestMapping("/")
    public String home() {
        List<Account> accounts = accountRepository.findByOwner(true);
        if (accounts.isEmpty()) {
            return "redirect:/form/accounts/owner";
        }
        return "/index";
    }

    @RequestMapping("/index")
    public String index() {
        return "/index";
    }

}
