package com.example.demo.services;

import com.example.demo.dao.VendorDaoOnJdbc;
import com.example.demo.entities.Vendor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendorService {
    private final VendorDaoOnJdbc vendorDaoOnJdbc;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public VendorService(VendorDaoOnJdbc vendorDaoOnJdbc) {
        this.vendorDaoOnJdbc = vendorDaoOnJdbc;
    }

    public List<Vendor> getAllVendors() {
        logger.info("attempt to get all vendors");
        return vendorDaoOnJdbc.findAll();
    }

    public Vendor getVendorById(Integer id) {
        logger.info("attempt to get vendor with id = " + id);
        return vendorDaoOnJdbc.findOne(id);
    }

    public Integer createVendor(Vendor vendor) {
        logger.info("attempt to create vendor");
        try {
            return vendorDaoOnJdbc.createVendor(vendor);
        } catch (Exception e) {
            return 0;
        }
    }

    public boolean updateVendor(Integer id, Vendor vendor) {
        logger.info("attempt to update vendor with id = " + id);

        logger.debug("Update vendor with input id = " + id);
        vendor.setId(id);
        return vendorDaoOnJdbc.update(vendor);
    }

    public boolean deleteVendorById(Integer id) {
        logger.info("attempt to delete vendor with id = " + id);

        return vendorDaoOnJdbc.delete(id);

    }
}