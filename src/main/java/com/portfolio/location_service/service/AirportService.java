package com.portfolio.location_service.service;

import com.portfolio.payload.request.AirportRequest;
import com.portfolio.payload.response.AirportResponse;

import java.util.List;

public interface AirportService {
    AirportResponse createAirport(  AirportRequest request);
    AirportResponse getAirportById(Long id);
    List<AirportResponse> getAllAirports();
    AirportResponse updateAirport(Long id, AirportRequest request);
    void deleteAirport(Long id);
    List<AirportResponse> getAirportsByCityId(Long cityId);
}
