package com.portfolio.location_service.service.impl;

import com.portfolio.location_service.service.CityService;
import com.portfolio.payload.request.CityRequest;
import com.portfolio.payload.response.CityResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {



    @Override
    public CityResponse createCity(CityRequest request) {
        return null;
    }

    @Override
    public CityResponse getCityById(Long id) {
        return null;
    }

    @Override
    public CityResponse updateCity(Long id, CityRequest request) {
        return null;
    }

    @Override
    public void deleteCityById(Long id) {

    }

    @Override
    public Page<CityResponse> getAllCities(Pageable pageable) {
        return null;
    }

    @Override
    public Page<CityResponse> searchCities(String keyword, Pageable pageable) {
        return null;
    }

    @Override
    public Page<CityResponse> getCitiesByCountryCode(String countryCode, Pageable pageable) {
        return null;
    }

    @Override
    public boolean cityExists(String cityCode) {
        return false;
    }

    @Override
    public boolean validateCityCode(String cityCode) {
        return false;
    }
}
