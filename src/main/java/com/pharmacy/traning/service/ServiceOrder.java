package com.pharmacy.traning.service;

import com.pharmacy.traning.exception.DaoException;
import com.pharmacy.traning.exception.ServiceException;
import com.pharmacy.traning.model.entity.Order;

import java.time.LocalDate;
import java.util.List;

public interface ServiceOrder {
    boolean addOrder(long productId, long userId, String quantity) throws ServiceException, DaoException;
    boolean payOrder(long orderId, long pharmacyId, String orderQuantity, String orderPrice) throws ServiceException, DaoException;
    boolean deleteOrderById(long orderId) throws ServiceException, DaoException;

    List<Order> findAllNotCompletedOrderByUser(long userId) throws ServiceException, DaoException;
    List<Order> findAllCompletedOrderByUserId(long userId, LocalDate date) throws ServiceException, DaoException;
    List<Order> findAllCompletedOrder() throws ServiceException, DaoException;
    List<Order> searchOrderByName(String name) throws ServiceException, DaoException;

    /*boolean addProductInOrder(long productId, long userId, int quantity) throws ServiceException, DaoException;
    boolean addProductQuantityInOrder(long orderId, int quantity) throws ServiceException, DaoException;*/



}
