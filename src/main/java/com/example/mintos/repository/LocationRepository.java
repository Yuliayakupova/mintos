package com.example.mintos.repository;

import com.example.mintos.dto.GeoIpDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class LocationRepository {

    @Autowired
    private final JdbcTemplate connection;

    public LocationRepository(JdbcTemplate connection) {
        this.connection = connection;
    }


    public void saveGeoIpData(GeoIpDTO geoIpData) {
        try {
            String insertQuery = "INSERT INTO GeoIpData (ip, city, region, country, loc, org, timezone) VALUES (?, ?, ?, ?, ?, ?, ?)";
            connection.update(insertQuery,
                geoIpData.getIp(),
                geoIpData.getCity(),
                geoIpData.getRegion(),
                geoIpData.getCountry(),
                geoIpData.getLoc(),
                geoIpData.getOrg(),
                geoIpData.getTimezone());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
