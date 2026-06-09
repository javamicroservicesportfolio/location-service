package com.portfolio.location_service.mapper;

import com.portfolio.location_service.model.City;
import com.portfolio.payload.request.CityRequest;
import com.portfolio.payload.response.CityResponse;


public class CityMapper {

    private CityMapper() {
        /* This utility class should not be instantiated */
    }


    public static City toCity(CityRequest request){
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

    public static CityResponse toResponse(City city){
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
                .build();
    }

    public static City updateCity(City city,CityRequest request){
        if (city == null || request == null) {
            return city;
        }

        city.setName(request.getName());
        city.setCityCode(request.getCityCode());
        city.setCountryName(request.getCountryName());
        city.setCountryCode(request.getCountryCode());
        city.setRegionCode(request.getRegionCode());

        return city;
    }




}
