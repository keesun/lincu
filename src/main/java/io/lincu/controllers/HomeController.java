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

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ContentRepository contentRepository;

    @RequestMapping("/")
    public String home(Model model) {
        List<Account> accounts = accountRepository.findByOwner(true);
        if (accounts.isEmpty()) {
            return "redirect:/form/accounts/owner";
        }
        model.addAttribute("content", new Content());
        model.addAttribute("allContents", contentRepository.findAll(sortByCuratedDEAC()));
        return "/index";
    }

    private Sort sortByCuratedDEAC() {
        return new Sort(Sort.Direction.DESC, "curatedAt");
    }

    @RequestMapping("/index")
    public String index() {
        return "/index";
    }

}
