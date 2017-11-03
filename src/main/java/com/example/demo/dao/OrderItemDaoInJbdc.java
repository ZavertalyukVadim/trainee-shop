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
        String sql = "SELECT\n" +
                "  order_items.id AS order_items_id,\n" +
                "  order_items.count AS order_items_count,\n" +
                "  goods.id AS goods_id,\n" +
                "  goods.name AS goods_name,\n" +
                "  goods.price AS goods_price,\n" +
                "  goods.type AS  goods_type,\n" +
                "  vendor.id AS vendor_id,\n" +
                "  vendor.name AS vendor_name\n" +
                "FROM order_items\n" +
                "  JOIN goods ON goods.id = order_items.goods_id\n" +
                "  JOIN vendor ON vendor.id = goods.vendor_id\n" +
                "WHERE order_items.id = ?";

        try (Connection conn = dataSource.getConnection()) {
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                conn.setAutoCommit(false);
                ps.setInt(1, id);
                Type[] types = Type.values();
                OrderItem orderItem = null;
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    orderItem = new OrderItem(
                            rs.getInt("order_items_id"),
                            rs.getInt("order_items_count"),
                            new Goods(
                                    rs.getInt("goods_id"),
                                    rs.getString("goods_name"),
                                    rs.getBigDecimal("goods_price"),
                                    types[Integer.parseInt(rs.getString("goods_type"))],
                                    new Vendor(rs.getInt("vendor_id"),
                                            rs.getString("vendor_name"))
                            )
                    );
                }
                rs.close();
                conn.commit();
                return orderItem;
            } catch (SQLException e) {
                conn.rollback();
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }


    public List<OrderItem> findAll() {

        String sql = "SELECT\n" +
                "  order_items.id AS order_items_id,\n" +
                "  order_items.count AS order_items_count,\n" +
                "  goods.id AS goods_id,\n" +
                "  goods.name AS goods_name,\n" +
                "  goods.price AS goods_price,\n" +
                "  goods.type AS  goods_type,\n" +
                "  vendor.id AS vendor_id,\n" +
                "  vendor.name AS vendor_name\n" +
                "FROM order_items\n" +
                "  JOIN goods ON goods.id = order_items.goods_id\n" +
                "  JOIN vendor ON vendor.id = goods.vendor_id";


        List<OrderItem> orderItems = new ArrayList<>();
        try (Connection conn = dataSource.getConnection()) {
            try {
                conn.setAutoCommit(false);
                PreparedStatement ps = conn.prepareStatement(sql);
                Type[] types = Type.values();
                OrderItem orderItem = null;
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    orderItem = new OrderItem(
                            rs.getInt("order_items_id"),
                            rs.getInt("order_items_count"),
                            new Goods(
                                    rs.getInt("goods_id"),
                                    rs.getString("goods_name"),
                                    rs.getBigDecimal("goods_price"),
                                    types[Integer.parseInt(rs.getString("goods_type"))],
                                    new Vendor(rs.getInt("vendor_id"),
                                            rs.getString("vendor_name"))
                            )
                    );
                    orderItems.add(orderItem);
                }
                rs.close();
                ps.close();
                conn.commit();
                return orderItems;
            } catch (SQLException e) {
                conn.rollback();
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderItems;
    }


    public boolean delete(Integer id) {
        String sql = "DELETE FROM order_items WHERE id = ?";

        try (Connection conn = dataSource.getConnection()) {
            conn.setAutoCommit(false);
            try {
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, id);
                ps.executeUpdate();
                ps.close();
                conn.commit();
                return true;
            } catch (SQLException e) {
                conn.rollback();
                e.printStackTrace();

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(OrderItem orderItem) {
        String sql = "UPDATE order_items SET count =?,goods_id =? WHERE id = ?";

        try (Connection conn = dataSource.getConnection()) {
            try {
                conn.setAutoCommit(false);
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, orderItem.getCount());
                ps.setInt(2, orderItem.getGoods().getId());
                ps.setInt(3, orderItem.getId());
                ps.executeUpdate();
                ps.close();
                conn.commit();
                return true;
            } catch (SQLException e) {
                conn.rollback();
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }

    public Integer create(OrderItem orderItem) {
        String sql = "INSERT INTO order_items(count,goods_id) VALUES (?,?) RETURNING id";
        Integer id = 0;
        try (Connection conn = dataSource.getConnection()) {
            try {
                conn.setAutoCommit(false);
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, orderItem.getCount());
                ps.setInt(2, orderItem.getGoods().getId());

                ResultSet resultSet = ps.executeQuery();
                System.out.println(resultSet);
                if (resultSet.next()) {
                    id = resultSet.getInt("id");
                }
                ps.close();
                conn.commit();
                return id;
            } catch (SQLException e) {
                conn.rollback();
                e.printStackTrace();

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
