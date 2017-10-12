package com.example.demo.utils;

import com.example.demo.entities.OrderItem;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class Calculator {
    public static BigDecimal priceCalculation(List<OrderItem> orderItems, Integer discount) {
        BigDecimal sum = BigDecimal.valueOf(0);
        BigDecimal totalPrice= null;
        for (OrderItem orderItem : orderItems) {
            if (orderItem.getGoods() != null) {
                BigDecimal underSum = orderItem.getGoods().getPrice().multiply(BigDecimal.valueOf(orderItem.getCount()));
                sum = sum.add(underSum);
            }
        }
        if (sum != null ) {
            BigDecimal sale = BigDecimal.valueOf(discount).divide(BigDecimal.valueOf(100),3, RoundingMode.CEILING);
            totalPrice = sum.subtract(sum.multiply(sale));
        }
        return totalPrice;
    }
}
