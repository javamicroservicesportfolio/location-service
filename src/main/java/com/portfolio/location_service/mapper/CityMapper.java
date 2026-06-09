package com.portfolio.location_service.mapper;

import com.portfolio.location_service.model.City;
import com.portfolio.payload.request.CityRequest;
import com.portfolio.payload.response.CityResponse;

public class CityMapper {

    public static City getCity(CityRequest request){
        if (request == null) {
            return null;
        }

        return City.builder()
                .name(request.getName())
                .cityCode(request.getCityCode())
                .countryName(request.getCountryName())
                .countryCode(request.getCountryCode())
                .timeZoneId(request.getTimeZoneOffset())
                .build();
    }

    public static CityResponse getCityResponse(City city){
        if (city == null) {
            return null;
        }

        return CityResponse.builder()
                .id(city.getId())
                .name(city.getName())
                .cityCode(city.getCityCode())
                .countryName(city.getCountryName())
                .countryCode(city.getCountryCode())
                .regionCode(city.getRegionCode())
                .timeZoneOffset(city.getTimeZoneId())
                .build();
    }

    
}
