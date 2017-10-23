package com.example.demo.dao;

import com.example.demo.entities.Vendor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class VendorDao {
    private final SessionFactory sessionFactory;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public VendorDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public List<Vendor> getAllVendors() {
        Session session = sessionFactory.openSession();
        return session.createQuery("SELECT v from Vendor v").list();
    }

    @Transactional
    public Vendor getVendorById(Integer id) {
        Session session = sessionFactory.openSession();
        try {
            logger.trace("try to get vendor with id=" + id);
            return session.byId(Vendor.class).load(id);
        } catch (Exception e) {
            logger.info(e.getMessage());
            return null;
        }
    }

    @Transactional
    public Integer createVendor(Vendor vendor) {
        Session session = sessionFactory.openSession();
        vendor.setId(null);
        try {
            logger.trace("try to create vendor");
            return ((Integer) session.save(vendor));
        } catch (Exception e) {
            logger.info(e.getMessage());
            return 0;
        }
    }

    @Transactional
    public boolean updateVendor(Integer id, Vendor vendor) {
        logger.trace("try to update vendor with id=" + id);
        Session session = sessionFactory.getCurrentSession();
        Vendor persistedVendor = session.get(Vendor.class, id);
        persistedVendor.setId(id);
        persistedVendor.setName(vendor.getName());
        persistedVendor.setGoods(vendor.getGoods());
        try {
            session.persist(persistedVendor);
            return true;
        } catch (Exception e) {
            logger.debug("attempt to update vendor with nonexistent id = " + id);
            return false;
        }
    }

    @Transactional
    public boolean deleteVendor(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        logger.trace("try to delete vendor with id=" + id);
        try {
            session.createQuery("delete from Vendor v where v.id = :id")
                    .setParameter("id", id)
                    .executeUpdate();
            return true;
        } catch (Exception e) {
            logger.debug("attempt to delete vendor with nonexistent id = " + id);
            return false;
        }
    }
}
