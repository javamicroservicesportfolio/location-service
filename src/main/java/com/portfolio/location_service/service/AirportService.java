package com.portfolio.location_service.service;

import com.portfolio.payload.request.AirportRequest;
import com.portfolio.payload.response.AirportResponse;

import java.util.List;

public interface AirportService {
    AirportResponse createAirport(  AirportRequest request) throws Exception;
    AirportResponse getAirportById(Long id) throws Exception;
    List<AirportResponse> getAllAirports() throws Exception;
    AirportResponse updateAirport(Long id, AirportRequest request) throws Exception;
    void deleteAirport(Long id) throws Exception;
    List<AirportResponse> getAirportsByCityId(Long cityId) throws Exception;
}
