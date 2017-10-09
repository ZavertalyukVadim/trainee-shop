package com.example.demo.controllers;

import com.example.demo.entities.Vendor;
import com.example.demo.services.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vendor")
public class VendorController {
    private final VendorService vendorService;

    @Autowired
    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @GetMapping
    public List<Vendor> getAllVendors(){
        return vendorService.getAllVendors();
    }

    @GetMapping(value = "/{id}")
    public Vendor getVendorById(@PathVariable("id") Integer id){
        return vendorService.getVendorById(id);
    }

    @PostMapping
    public Integer createVendor(@RequestBody Vendor vendor){
        return vendorService.createVendor(vendor);
    }

    @PutMapping
    public Vendor updateVendor(@RequestBody Vendor vendor){
        return vendorService.updateVendor(vendor);
    }

    @DeleteMapping(value = "/{id}")
    public boolean deleteVendorById(@PathVariable("id") Integer id){
        return vendorService.deleteVendorById(id);
    }
}
