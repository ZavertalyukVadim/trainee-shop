package com.example.demo.services;

import com.example.demo.dao.VendorDao;
import com.example.demo.entities.Vendor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendorService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final VendorDao vendorDao;

    @Autowired
    public VendorService(VendorDao vendorDao) {
        this.vendorDao = vendorDao;
    }


    public List<Vendor> getAllVendors() {
        logger.trace("attempt to get all vendors");
        return vendorDao.getAllVendors();
    }

    public Vendor getVendorById(Integer id) {
        logger.trace("attempt to get vendor with id = " + id);
        return vendorDao.getVendorById(id);
    }

    public Integer createVendor(Vendor vendor) {
        try {
            return vendorDao.createVendor(vendor);
        } catch (Exception e) {
            return 0;
        }
    }

    public boolean updateVendor(Integer id, Vendor vendor) {
        return vendorDao.updateVendor(id, vendor);
    }

    public boolean deleteVendorById(Integer id) {
        logger.info("attempt to delete vendor with id = " + id);
        try {
            vendorDao.deleteVendor(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}