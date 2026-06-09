package com.portfolio.location_service.mapper;

import com.portfolio.location_service.model.Airport;
import com.portfolio.payload.request.AirportRequest;
import com.portfolio.payload.response.AirportResponse;

public class AirportMapper {


    public static Airport toAirport(AirportRequest request) {
        if (request == null) {
            return null;
        }

        return Airport.builder()
                .iataCode(request.getIataCode())
                .name(request.getName())
                .timeZone(request.getTimeZone().toString())
                .address(request.getAddress())
                .geoCode(request.getGeoCode())
                .build();
    }

    public static AirportResponse  toAirportResponse(Airport airport) {
        if (airport == null) {
            return null;
        }

        return AirportResponse.builder()
                .id(airport.getId())
                .iataCode(airport.getIataCode())
                .name(airport.getName())
                .timeZone(airport.getTimeZone())
                .address(airport.getAddress())
                .geoCode(airport.getGeoCode())
                .city(CityMapper.toResponse(airport.getCity()))
                .build();
    }
}
