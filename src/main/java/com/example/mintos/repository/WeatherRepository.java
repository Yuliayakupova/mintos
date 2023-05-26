package com.example.mintos.repository;

import com.example.mintos.dto.WeatherDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class WeatherRepository {

    private final static String INTO_WEATHER_DATA = """
        INSERT INTO 
            WeatherData (
                latitude, 
                longitude, 
                generationtime_ms, 
                utc_offset_seconds, 
                timezone, 
                timezone_abbreviation, 
                elevation, 
                hourly_units_temperature_2m, 
                hourly_units_time
        ) VALUES (
            ?, 
            ?, 
            ?, 
            ?, 
            ?, 
            ?, 
            ?, 
            ?, 
            ?)
        """;

    @Autowired
    private final JdbcTemplate connection;

    public WeatherRepository(JdbcTemplate connection) {
        this.connection = connection;
    }

    public void saveWeatherData(WeatherDTO weatherData) {

        try{
            WeatherDTO.HourlyData hourly =  weatherData.getHourly();

            final Double temperature = hourly.getTemperature_2m().isEmpty()
                    ? null
                    : hourly.getTemperature_2m().get(0);

            final String time = hourly.getTime().isEmpty()
                    ? null
                    : hourly.getTime().get(0);

            connection.update(INTO_WEATHER_DATA,
                    weatherData.getLatitude(),
                    weatherData.getLongitude(),
                    weatherData.getGenerationtime_ms(),
                    weatherData.getUtc_offset_seconds(),
                    weatherData.getTimezone(),
                    weatherData.getTimezone_abbreviation(),
                    weatherData.getElevation(),
                    temperature,
                    time);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
