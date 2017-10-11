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
    private final VendorDao vendorDao;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public VendorService(VendorDao vendorDao) {
        this.vendorDao = vendorDao;
    }

    public List<Vendor> getAllVendors() {
        logger.info("attempt to get all vendors");
        return vendorDao.findAll();
    }

    public Vendor getVendorById(Integer id) {
        logger.info("attempt to get vendor with id = " + id);
        return vendorDao.findOne(id);
    }

    public Integer createVendor(Vendor vendor) {
        logger.info("attempt to create vendor");
        try {
            return vendorDao.save(vendor).getId();
        } catch (Exception e) {
            return 0;
        }
    }

    public boolean updateVendor(Integer id, Vendor vendor) {
        logger.info("attempt to update vendor with id = " + id);
        Vendor newVendor = vendorDao.findOne(id);
        logger.debug("check if vendor with id " + id + " exists in database");
        if (newVendor != null) {
            logger.debug("Update vendor with input id = " + id);
            vendor.setId(id);
            vendorDao.save(vendor);
            return true;
        } else {
            logger.debug("attempt to update vendor with nonexistent id = " + id);
            return false;
        }
    }

    public boolean deleteVendorById(Integer id) {
        logger.info("attempt to delete vendor with id = " + id);
        try {
            vendorDao.delete(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
