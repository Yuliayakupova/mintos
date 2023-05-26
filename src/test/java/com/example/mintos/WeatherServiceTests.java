package com.example.mintos;

import com.example.mintos.dto.GeoIpDTO;
import com.example.mintos.dto.WeatherDTO;
import com.example.mintos.repository.LocationRepository;
import com.example.mintos.repository.WeatherRepository;
import com.example.mintos.services.WeatherService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class WeatherServiceTests {
    @Mock
    private LocationRepository locationRepository;
    @Mock
    private WeatherRepository weatherRepository;

    private WeatherService weatherService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        weatherService = new WeatherService(locationRepository, weatherRepository);
    }

    @Test
    void getWeatherForRequest_NullGeoIpDTO_ReturnsNull() {
        // Arrange
        GeoIpDTO geoIpDTO = null;

        // Act
        WeatherDTO result = weatherService.getWeatherForRequest(geoIpDTO);

        // Assert
        assertNull(result);
        verify(locationRepository, times(0)).saveGeoIpData(any(GeoIpDTO.class));
        verify(weatherRepository, times(0)).saveWeatherData(any(WeatherDTO.class));
    }

}
