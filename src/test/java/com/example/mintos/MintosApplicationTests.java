package com.example.mintos;

import com.example.mintos.controllers.WeatherController;
import com.example.mintos.dto.GeoIpDTO;
import com.example.mintos.dto.WeatherDTO;
import com.example.mintos.repository.LocationRepository;
import com.example.mintos.repository.WeatherRepository;
import com.example.mintos.services.GeoLocationService;
import com.example.mintos.services.WeatherService;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class MintosApplicationTests {

	@Mock
	private GeoLocationService geoLocationService;
	@Mock
	private LocationRepository locationRepository;
	@Mock
	private WeatherService weatherService;
	@Mock
	private WeatherRepository weatherRepository;
	@Mock
	private HttpServletRequest request;

	private WeatherController weatherController;

	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
		weatherController = new WeatherController(geoLocationService, weatherService);

	}

	@Test
	void getWeather_NullGeoIpDTO_ReturnsInternalServerErrorResponse() {
		// Arrange
		WeatherDTO weatherDTO = new WeatherDTO();
		when(geoLocationService.getLocationByClientRequest(any(HttpServletRequest.class))).thenReturn(null);
		when(weatherService.getWeatherByCoordinates(any(String.class))).thenReturn(weatherDTO);

		// Act
		ResponseEntity<WeatherDTO> response = weatherController.getWeather(request);

		// Assert
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
		verify(locationRepository, times(0)).saveGeoIpData(any(GeoIpDTO.class));
		verify(weatherRepository, times(0)).saveWeatherData(any(WeatherDTO.class));
	}
}
