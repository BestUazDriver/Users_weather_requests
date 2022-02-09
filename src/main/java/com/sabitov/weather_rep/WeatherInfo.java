package com.sabitov.weather_rep;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class WeatherInfo {
    public String getInfo(URL url){
        HttpURLConnection connection = null;
        try {

            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
        } catch (MalformedURLException | ProtocolException | FileNotFoundException e) {
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

        connection.disconnect();
        return content.toString();
    }
}
