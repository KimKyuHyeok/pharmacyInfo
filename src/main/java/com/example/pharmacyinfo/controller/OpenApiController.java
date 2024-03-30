package com.example.pharmacyinfo.controller;

import com.example.pharmacyinfo.service.OpenApiService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class OpenApiController {

    private final OpenApiService openApiService;

    @GetMapping("/")
    public String dataInsert() {

        openApiService.getOpenApiDataList();

        return "index";
    }
}
