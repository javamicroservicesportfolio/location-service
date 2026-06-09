package com.portfolio.location_service.service.impl;

import com.portfolio.location_service.repository.AirportRepository;
import com.portfolio.payload.response.AirportResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AirportServiceImpl {

    private final AirportRepository airportRepository;

    public List<AirportResponse> findByIataCode(String iataCode) {
        return List.of(airportRepository.findByIataCode(iataCode));
    }

    public List<AirportResponse> findByCityId(Long cityId) {
        return airportRepository.findByCityId(cityId);
    }
}
