package com.example.pharmacyinfo.repository;

import com.example.pharmacyinfo.entity.Pharmacy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OpenApiRepository extends JpaRepository<Pharmacy, Long> {


}
