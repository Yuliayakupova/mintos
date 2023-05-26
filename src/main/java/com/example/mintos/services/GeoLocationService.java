package com.example.mintos.services;

import com.example.mintos.dto.GeoIpDTO;
import com.example.mintos.repository.LocationRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GeoLocationService {

    private static final String GEO_API_URL = "https://ipinfo.io/%s/geo";

    private final LocationRepository locationRepository;

    //https://czqu.net/posts/1268933518/
    private static final String[] REQUEST_IP_HEADERS = {
            "X-Forwarded-For",
            "Proxy-Client-IP",
            "WL-Proxy-Client-IP",
            "HTTP_X_FORWARDED_FOR",
            "HTTP_X_FORWARDED",
            "HTTP_X_CLUSTER_CLIENT_IP",
            "HTTP_CLIENT_IP",
            "HTTP_FORWARDED_FOR",
            "HTTP_FORWARDED",
            "HTTP_VIA",
            "REMOTE_ADDR"
    };

    public GeoLocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public String getClientIpAddress(HttpServletRequest request){
        return "217.195.58.147";

//        for (String header: REQUEST_IP_HEADERS){
//            String ipAddress = request.getHeader(header);
//            if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
//                continue;
//            }
//
//            String[] parts = ipAddress.split("\\s*,\\s*");
//            return parts[0];
//        }
//
//        return request.getRemoteAddr();
    }

    public GeoIpDTO getLocationByClientRequest(final HttpServletRequest request) {

        final String ipAddress = getClientIpAddress(request);

        final RestTemplate restTemplate = new RestTemplate();
        final String url = String.format(GEO_API_URL, ipAddress);
        GeoIpDTO geoIpDTO = restTemplate.getForObject(url, GeoIpDTO.class);
        locationRepository.saveGeoIpData(geoIpDTO);
        return geoIpDTO;
    }
}
