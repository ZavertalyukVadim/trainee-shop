package com.example.demo.services;

import com.example.demo.dao.VendorDao;
import com.example.demo.entities.Vendor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendorService {
    private final VendorDao vendorDao;

    @Autowired
    public VendorService(VendorDao vendorDao) {
        this.vendorDao = vendorDao;
    }

    public List<Vendor> getAllVendors(){

        return vendorDao.findAll();
    }

    public Vendor getVendorById(Integer id){
        return vendorDao.findOne(id);
    }

    public Integer createVendor(Vendor vendor){
        return vendorDao.save(vendor).getId();
    }

    public Vendor updateVendor(Vendor vendor){
        Vendor newVendor = vendorDao.findOne(vendor.getId());
        newVendor.setGoods(vendor.getGoods());
        newVendor.setName(vendor.getName());
        return vendorDao.save(newVendor);
    }

    public boolean deleteVendorById(Integer id){
        try {
            vendorDao.delete(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
