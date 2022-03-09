package com.sabitov.controllers;

import com.sabitov.dto.CreateUserDto;
import com.sabitov.dto.SimpleUserDto;
import com.sabitov.models.Account;
import com.sabitov.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/sign_in")
public class SignInController {


    private final AccountService accountService;

    @Autowired
    public SignInController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public String controller(Model model) {
        model.addAttribute("accounts", accountService.findAll().stream().map(SimpleUserDto::reverseForm).collect(Collectors.toList()));
        return "sign_in";
    }

    @PostMapping
    public String signIn(@RequestParam("name") String name, @RequestParam("email") String email, @RequestParam("password") String password) {
        CreateUserDto dto=CreateUserDto.builder().name(name).email(email).password(password).build();
        Account account = Account.builder().
                name(dto.getName()).
                email(dto.getEmail()).
                password(dto.getPassword()).
                role("user").
                build();
        accountService.saveDto(account);
        return "account";
    }
}
