package network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class Weather {

    //shamelessly quoting from: http://zetcode.com/articles/javareadwebpage/
    public static StringBuilder getWeather() throws MalformedURLException, IOException {

        String apikey = "61637a6880cdf9e9c7b84438fcf5130f";
        String weatherquery = "https://api.openweathermap.org/data/2.5/weather?q=Toronto&units=metric&APPID=";
        BufferedReader br = null;

        try {
            URL url = new URL(weatherquery + apikey);
            br = new BufferedReader(new InputStreamReader(url.openStream()));

            String line;

            StringBuilder sb = new StringBuilder();

            while ((line = br.readLine()) != null) {

                sb.append(line);
                sb.append(System.lineSeparator());
            }

            return sb;
        } finally {

            if (br != null) {
                br.close();
            }
        }
    }

    public static void extractWeather(StringBuilder sb) {
        int minIndexOfTemperature = sb.indexOf("temp") + 6;
        int endingIndexOfTemperature = sb.indexOf("pressure") - 2;
        int minIndexOfCity = sb.indexOf("name") + 7;
        int endingIndexOfCity = sb.indexOf("cod") - 3;
        String city = sb.substring(minIndexOfCity, endingIndexOfCity);
        String temperature = sb.substring(minIndexOfTemperature, endingIndexOfTemperature);
        System.out.println("Current weather in " + city + " is " + temperature + "°C");
    }
}