package com.sabitov.weather;

import com.sabitov.weather_rep.WeatherInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;

@Controller
@RequestMapping("/weatherInfo")
public class WeatherController {
    private final String keyApi = "61c4a7fe2f5767b8c857034ee7ab4c50";

    @GetMapping
    public String getWeatherInf(@RequestParam(name = "city", defaultValue = "Kazan") String city, HttpServletResponse resp) {
        URL url = null;
        PrintWriter out = null;
        try {

            url = new URL("https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + keyApi);
            out = resp.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String content = new WeatherInfo().getInfo(url);
        out.print(content);
        out.flush();
        return content;
    }
}
