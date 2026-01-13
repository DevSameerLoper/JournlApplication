package net.engineeringdigest.journalApp.controller;

import lombok.extern.slf4j.Slf4j;
import net.engineeringdigest.journalApp.api_response.WeatherResponse;
import net.engineeringdigest.journalApp.entity.Users;
import net.engineeringdigest.journalApp.service.UserService;
import net.engineeringdigest.journalApp.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
@Slf4j
@RestController
@RequestMapping("/public")
public class PublicController {
    @Autowired
    private UserService userService;
    @Autowired
    private WeatherService weatherService;


    @GetMapping
    public String helthchek() {
        log.info("enterd into health check controller");
        return "Project Helth is GOOD SAMEER :)";
    }

    @PostMapping("/create-user")
    public ResponseEntity<?> createUser(@RequestBody Users myEntry) {
        try {
            userService.saveNewUser(myEntry);
            return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }
    @GetMapping("/weather")
    public ResponseEntity<?> getWeather() {

        WeatherResponse response = weatherService.getWeather("Sitamau");
        String weather="";
        if(response != null){
            weather="weather feels like,"+response.getCurrent().getTemperature();
        }
        return  new ResponseEntity<>("Hi"+weather,HttpStatus.OK);
    }
}
