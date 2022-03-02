package com.sabitov.weather;

import com.sabitov.services.UserRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/user_requests")
public class UserRequestController {

    private final UserRequestService userRequestService;

    @GetMapping
    public String getRequests(Model model){
        model.addAttribute("requests", userRequestService.findAll());
        return "userRequests";
    }

}
