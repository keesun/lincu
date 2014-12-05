package io.lincu.controllers;

import io.lincu.domains.Account;
import io.lincu.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
            return "redirect:/account/create-owner";
        }
        return "/index";
    }

}
