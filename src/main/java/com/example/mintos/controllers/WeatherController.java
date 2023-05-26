package com.example.mintos.controllers;

import com.example.mintos.dto.GeoIpDTO;
import com.example.mintos.dto.WeatherDTO;
import com.example.mintos.services.GeoLocationService;
import com.example.mintos.services.WeatherService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class WeatherController {

    private final GeoLocationService geoLocationService;
    private final WeatherService weatherService;

    @Autowired
    public WeatherController(GeoLocationService geoLocationService, WeatherService weatherService) {
        this.geoLocationService = geoLocationService;
        this.weatherService = weatherService;
    }

    @GetMapping("/weather")
    public ResponseEntity<WeatherDTO> getWeather(HttpServletRequest request) {
        GeoIpDTO geoIpDTO = this.geoLocationService.getLocationByClientRequest(request);
        WeatherDTO weatherDTO = this.weatherService.getWeatherForRequest(geoIpDTO);

        if (weatherDTO != null) {
            return ResponseEntity.ok(weatherDTO);
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

}
