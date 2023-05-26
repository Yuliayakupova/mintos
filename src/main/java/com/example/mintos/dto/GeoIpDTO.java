package com.example.mintos.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GeoIpDTO {

    @JsonProperty("ip")
    private String ip;

    @JsonProperty("city")
    private String city;

    @JsonProperty("region")
    private String region;

    @JsonProperty("country")
    private String country;

    @JsonProperty("loc")
    private String loc;

    @JsonProperty("org")
    private String org;

    @JsonProperty("timezone")
    private String timezone;

    public String getIp() {
        return ip;
    }

    public String getCity() {
        return city;
    }

    public String getRegion() {
        return region;
    }

    public String getCountry() {
        return country;
    }

    public String getLoc() {
        return loc;
    }

    public String getOrg() {
        return org;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public void setOrg(String org) {
        this.org = org;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }
}
