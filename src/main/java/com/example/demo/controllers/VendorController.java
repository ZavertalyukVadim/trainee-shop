package com.example.demo.controllers;

import com.example.demo.entities.Vendor;
import com.example.demo.services.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
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
    public ResponseEntity<List<Vendor>> getAllVendors() {
        return new ResponseEntity<>(vendorService.getAllVendors(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Vendor> getVendorById(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(vendorService.getVendorById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Integer> createVendor(@RequestBody Vendor vendor) {
        return (vendorService.createVendor(vendor) >= 1) ? new ResponseEntity<>(HttpStatus.CREATED)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping(value = "/{id}")
    public void updateVendor(@PathVariable("id") Integer id, @RequestBody Vendor vendor,HttpServletResponse response) {
         if (vendorService.updateVendor(id, vendor)){
             response.setStatus(HttpServletResponse.SC_RESET_CONTENT);
         } else {
             response.setStatus(HttpServletResponse.SC_NOT_FOUND);
         }
    }

    @DeleteMapping(value = "/{id}")
    public void deleteVendorById(@PathVariable("id") Integer id,HttpServletResponse response) {
        if (vendorService.deleteVendorById(id)){
            response.setStatus(HttpServletResponse.SC_RESET_CONTENT);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
