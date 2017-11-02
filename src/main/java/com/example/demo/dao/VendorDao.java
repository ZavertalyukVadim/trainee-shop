package com.example.demo.dao;

import com.example.demo.entities.Vendor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public class VendorDao {
    final
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public VendorDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Integer createVendor(Vendor vendor) {
        jdbcTemplate.update("INSERT INTO vendor(name) VALUES (?)", vendor.getName());

        String sql = "SELECT id FROM vendor WHERE name = ?";
        Integer id = jdbcTemplate.queryForObject(sql, Integer.class, vendor.getName());
        System.out.println("id=" + id);
        return id;
    }

    public List<Vendor> findAll() {
        return jdbcTemplate.query(
                "SELECT id, name FROM vendor",
                (rs, rowNum) -> new Vendor(rs.getInt("id"),
                        rs.getString("name"))
        );
    }

    public Vendor findOne(Integer id) {
        return null;
    }

    public Vendor save(Vendor vendor) {
        return null;
    }

    public boolean delete(Integer id) {
        String sql = "DELETE FROM vendor WHERE id=?";
        int update = jdbcTemplate.update(sql, id);
        return update == 1;
    }
}
