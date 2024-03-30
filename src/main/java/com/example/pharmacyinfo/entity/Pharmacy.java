package com.example.pharmacyinfo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class Pharmacy {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long pharmacyId;
    private String address;
    private String name;
    @Column(name = "tel")
    private String telNumber;

    private String monStart;
    private String monEnd;

    private String tueStart;
    private String tueEnd;

    private String wedStart;
    private String wedEnd;

    private String thuStart;
    private String thuEnd;

    private String friStart;
    private String friEnd;

    private String satStart;
    private String satEnd;

    private String sunStart;
    private String sunEnd;

    private String holStart;
    private String holEnd;

    private String lon;
    private String lat;

    private LocalDateTime time;

    @Builder
    public Pharmacy(String address, String name, String telNumber, String monStart, String monEnd, String tueStart, String tueEnd, String wedStart, String wedEnd, String thuStart, String thuEnd, String friStart, String friEnd, String satStart, String satEnd, String sunStart, String sunEnd, String holStart, String holEnd, String lon, String lat, LocalDateTime time) {
        this.address = address;
        this.name = name;
        this.telNumber = telNumber;
        this.monStart = monStart;
        this.monEnd = monEnd;
        this.tueStart = tueStart;
        this.tueEnd = tueEnd;
        this.wedStart = wedStart;
        this.wedEnd = wedEnd;
        this.thuStart = thuStart;
        this.thuEnd = thuEnd;
        this.friStart = friStart;
        this.friEnd = friEnd;
        this.satStart = satStart;
        this.satEnd = satEnd;
        this.sunStart = sunStart;
        this.sunEnd = sunEnd;
        this.holStart = holStart;
        this.holEnd = holEnd;
        this.lon = lon;
        this.lat = lat;
        this.time = LocalDateTime.now();
    }
}
