package io.lincu.controllers;

import io.lincu.domains.Account;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Keeun Baik
 */
@Controller
public class AccountController {

    @RequestMapping(value = "/account/create-owner", method = RequestMethod.GET)
    public String createOwner(Model model) {
        Account newOwnerAccount = new Account();
        newOwnerAccount.setOwner(true);
        model.addAttribute("owner", newOwnerAccount);
        return "/account/create-owner";
    }

}
