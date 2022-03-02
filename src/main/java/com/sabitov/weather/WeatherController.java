package com.sabitov.weather;

import com.sabitov.models.UserRequest;
import com.sabitov.services.UserRequestService;
import com.sabitov.weather_rep.WeatherInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;

@Controller
@RequestMapping("/weatherInfo")
@RequiredArgsConstructor
public class WeatherController {
    private final UserRequestService userRequestService;
    private final String keyApi = "61c4a7fe2f5767b8c857034ee7ab4c50";

    @GetMapping
    public String getWeatherInf(@RequestParam(name = "city", defaultValue = "Kazan") String city, HttpServletResponse resp) {
        UserRequest userRequest = UserRequest.builder().userId(keyApi).city(city).build();
        userRequestService.save(userRequest);
        URL url = null;
        PrintWriter out = null;
        try {

            url = new URL("https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + keyApi);
            out = resp.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert url != null;
        String content = new WeatherInfo().getInfo(url);
        assert out != null;
        out.print(content);
        out.flush();
        return content;
    }
}
