package com.example.mintos.repository;

import com.example.mintos.dto.WeatherDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.awt.*;

@Repository
public class WeatherRepository {

    @Autowired
    private final JdbcTemplate connection;

    public WeatherRepository(JdbcTemplate connection) {
        this.connection = connection;
    }

    public void saveWeatherData(WeatherDTO weatherData) {
        try{
            String insertQuery = "INSERT INTO WeatherData (latitude, longitude, generationtime_ms, utc_offset_seconds, timezone, timezone_abbreviation, elevation) VALUES (?, ?, ?, ?, ?, ?, ?)";
            connection.update(insertQuery,
                    weatherData.getLatitude(),
                    weatherData.getLongitude(),
                    weatherData.getGenerationtime_ms(),
                    weatherData.getUtc_offset_seconds(),
                    weatherData.getTimezone(),
                    weatherData.getTimezone_abbreviation(),
                    weatherData.getElevation());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}