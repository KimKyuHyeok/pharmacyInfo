package com.example.pharmacyinfo.entity;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PharmacyResponse {

    private String name;
    private String address;
    private String telNumber;
    private String mon;
    private String tue;
    private String wed;
    private String thu;
    private String fri;
    private String sat;
    private String sun;
    private String hol;
    private String lon;
    private String lat;
}
