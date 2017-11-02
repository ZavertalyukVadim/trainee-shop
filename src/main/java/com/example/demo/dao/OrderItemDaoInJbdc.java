package com.example.demo.dao;

import com.example.demo.entities.Goods;
import com.example.demo.entities.OrderItem;
import com.example.demo.entities.Type;
import com.example.demo.entities.Vendor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class OrderItemDaoInJbdc {
    private final DataSource dataSource;

    @Autowired
    public OrderItemDaoInJbdc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public OrderItem findOne(int id) {

        String sql = "SELECT * FROM order_items JOIN goods ON goods.id=order_items.goods_id  JOIN vendor ON vendor.id = goods.vendor_id WHERE order_items.id = ?";

        Connection conn = null;

        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            Type[] types = Type.values();
            OrderItem orderItem = null;
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                orderItem = new OrderItem(
                        rs.getInt(1),
                        rs.getInt("count"),
                        new Goods(
                                rs.getInt(3),
                                rs.getString("name"),
                                rs.getBigDecimal("price"),
                                types[Integer.parseInt(rs.getString("type"))],
                                new Vendor( rs.getInt(10),
                                        rs.getString(11))
                        )
                );
            }
            rs.close();
            ps.close();
            return orderItem;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public List<OrderItem> findAll() {

        String sql = "SELECT * FROM order_items JOIN goods ON goods.id=order_items.goods_id  JOIN vendor ON vendor.id = goods.vendor_id";

        Connection conn = null;
        List<OrderItem> orderItems= new ArrayList<>();
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            Type[] types = Type.values();
            OrderItem orderItem = null;
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                orderItem = new OrderItem(
                        rs.getInt(1),
                        rs.getInt("count"),
                        new Goods(
                                rs.getInt(3),
                                rs.getString("name"),
                                rs.getBigDecimal("price"),
                                types[Integer.parseInt(rs.getString("type"))],
                                new Vendor( rs.getInt(10),
                                        rs.getString(11))
                        )
                );
                orderItems.add(orderItem);
            }
            rs.close();
            ps.close();
            return orderItems;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public boolean delete(Integer id) {
        return false;
    }

    public boolean update(OrderItem orderItem) {
        return false;
    }

    public Integer create(OrderItem orderItem) {
        String sql  ="Insert Into order_items(count,goods_id) VALUES (?,?)";
        Connection conn = null;

        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, orderItem.getCount());
            ps.setInt(2, orderItem.getGoods().getId());
            int i = ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);

        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {}
            }
        }
        return 0;
    }
}
