package com.sabitov.controllers;

import com.sabitov.models.Account;
import com.sabitov.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@RequestMapping("/sign_up")
public class SignUpController {

    private final AccountService accountService;

    @Autowired
    public SignUpController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public String getRequest(Model model) {
        model.addAttribute("err_message", "");
        return "sign_up";
    }

    @PostMapping
    public String signUp(HttpServletRequest request, Model model, @RequestParam("email") String email, @RequestParam("password") String password) {
        Optional<Account> accountOptional = accountService.isSignUp(email, password);
        if (accountOptional.isPresent()) {
            HttpSession session = request.getSession(true);
            session.setAttribute("account", accountOptional.get());
            return "/account";
        }
        model.addAttribute("err_message", "Incorrect login or password. Try again.");
        return "sign_up";
    }
}
