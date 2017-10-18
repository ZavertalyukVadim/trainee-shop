package com.example.demo.dao;

import com.example.demo.entities.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorDao extends JpaRepository<Vendor, Integer> {
}
