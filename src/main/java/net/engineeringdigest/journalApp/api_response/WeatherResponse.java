package net.engineeringdigest.journalApp.api_response;

import lombok.Data;

import java.util.ArrayList;

@Data
public class WeatherResponse {


    private  Current current;


    @Data
    public class Current {

        private int temperature;
        private int weather_code;
        private ArrayList<String> weather_icons;
        private ArrayList<String> weather_descriptions;


    }


}
