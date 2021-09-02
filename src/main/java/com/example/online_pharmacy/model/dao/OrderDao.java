package com.example.online_pharmacy.model.dao;

import com.example.online_pharmacy.entity.Order;
import com.example.online_pharmacy.exception.DaoException;

import java.util.List;

public interface OrderDao {
    boolean addOrder(Order order) throws DaoException;
    List<Order> findOrderById(int orderId) throws DaoException;
    List<Order> findOrderByUserId(int userId) throws DaoException;
    List<Order> findOrderByProductId(int productId) throws DaoException;
    List<Order> findOrderByStatus(String orderStatus) throws DaoException;

}
