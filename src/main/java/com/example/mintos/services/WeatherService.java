package com.example.mintos.services;

import com.example.mintos.dto.GeoIpDTO;
import com.example.mintos.dto.WeatherDTO;
import com.example.mintos.repository.LocationRepository;
import com.example.mintos.repository.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class WeatherService {

    private static final String WEATHER_API_URL = "https://api.open-meteo.com/v1/dwd-icon?latitude=%f&longitude=%f&hourly=temperature_2m";

    private final LocationRepository locationRepository;

    private final WeatherRepository weatherRepository;

    @Autowired
    public WeatherService(LocationRepository locationRepository, WeatherRepository weatherRepository) {
        this.locationRepository = locationRepository;
        this.weatherRepository = weatherRepository;
    }

    public WeatherDTO getWeatherByCoordinates(String coordinates) {
        if (coordinates == null) {
            return null;
        }
        RestTemplate restTemplate = new RestTemplate();
        String[] latLng = coordinates.split(",");
        double latitude = Double.parseDouble(latLng[0]);
        double longitude = Double.parseDouble(latLng[1]);
        String url = String.format(WEATHER_API_URL, latitude, longitude);
        return restTemplate.getForObject(url, WeatherDTO.class);
    }

    public WeatherDTO getWeatherForRequest(GeoIpDTO geoIpDTO) {
        if (geoIpDTO != null) {
            locationRepository.saveGeoIpData(geoIpDTO);
            WeatherDTO weatherDTO = getWeatherByCoordinates(geoIpDTO.getLoc());
            weatherRepository.saveWeatherData(weatherDTO);
            return weatherDTO;
        }
        return null;
    }
}
