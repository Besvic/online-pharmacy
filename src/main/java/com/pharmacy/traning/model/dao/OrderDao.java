package com.pharmacy.traning.model.dao;

import com.pharmacy.traning.model.entity.Order;
import com.pharmacy.traning.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface OrderDao {
    boolean addOrder(Order order) throws DaoException;

    boolean addProductQuantityInOrder(long orderId, int quantity) throws DaoException;
    Optional<Order> checkIsOrder(long userId, long productId) throws DaoException;
    List<Order> findAllNotCompletedOrderById(long userId) throws DaoException;
    Optional<Order> findOrderById(int orderId) throws DaoException;
//    List<Order> findOrderByUserId(int userId) throws DaoException;
//    List<Order> findOrderByProductId(int productId) throws DaoException;
//    List<Order> findOrderByStatus(String orderStatus) throws DaoException;

}
