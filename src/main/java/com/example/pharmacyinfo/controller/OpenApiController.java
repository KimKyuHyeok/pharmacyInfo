package com.example.pharmacyinfo.controller;

import com.example.pharmacyinfo.entity.PharmacyResponse;
import com.example.pharmacyinfo.service.OpenApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class OpenApiController {

    private final OpenApiService openApiService;

    @GetMapping("/")
    public String home(Model model) throws Exception {

        List<PharmacyResponse> pharmacyResponseList = openApiService.responsesDataList();
        int total = openApiService.getTotal();

        model.addAttribute("list", pharmacyResponseList);
        model.addAttribute("totalCnt", total);

        return "index";
    }

    @GetMapping("/loading")
    public String dataInsert() {

        openApiService.getOpenApiDataList();

        return "redirect:/";
    }
}
