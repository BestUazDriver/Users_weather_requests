package com.sabitov.weather;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Optional;

@Controller
@RequestMapping("/weatherInfo")
public class WeatherController {
    private final String keyApi = "61c4a7fe2f5767b8c857034ee7ab4c50";

    @GetMapping
    public String getWeatherInf(@RequestParam(name = "city", defaultValue = "Kazan") String city, HttpServletResponse resp) {
        URL url;
        HttpURLConnection connection = null;
        try {
            url = new URL("https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + keyApi);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
        } catch (MalformedURLException | ProtocolException |FileNotFoundException e) {
            throw new IllegalArgumentException(e);
        } catch (IOException e) {
            e.printStackTrace();
        }


        StringBuilder content = null;
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            content = new StringBuilder();
            String input;
            while (true) {
                try {
                    if ((input = bufferedReader.readLine()) == null) break;
                } catch (IOException e) {
                    throw new IllegalArgumentException(e);
                }
                content.append(input);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(content);
        connection.disconnect();
        PrintWriter out = null;
        try {
            out = resp.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        out.print(content);
        out.flush();
        return content.toString();
    }
}
