package io.lincu.controllers;

import io.lincu.domains.Account;
import io.lincu.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * @author Keeun Baik
 */
@Controller
public class AccountController {

    @Autowired
    AccountService service;

    @RequestMapping(value = "/form/accounts/owner", method = RequestMethod.GET)
    public String createOwnerForm(Model model) {
        Account newOwnerAccount = new Account();
        model.addAttribute("owner", newOwnerAccount);
        return "/account/create-owner";
    }

    @RequestMapping(value = "/form/accounts/owner", method = RequestMethod.POST)
    public String createOwner(@ModelAttribute("owner") @Valid Account account, BindingResult result) {
        if(result.hasErrors()) {
            return "/account/create-owner";
        }
        service.createOwner(account);
        return "redirect:/";
    }

}
