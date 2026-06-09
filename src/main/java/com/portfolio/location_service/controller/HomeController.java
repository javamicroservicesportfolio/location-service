package com.portfolio.location_service.controller;

import com.portfolio.payload.response.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping
    public ApiResponse homeGet() {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Hello World");

        return apiResponse;
    }


}
