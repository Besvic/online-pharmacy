package com.pharmacy.traning.service;

import com.pharmacy.traning.exception.DaoException;
import com.pharmacy.traning.exception.ServiceException;
import com.pharmacy.traning.model.entity.Order;

import java.util.List;

public interface ServiceOrder {
    boolean addOrder(long productId, long userId, int quantity) throws ServiceException, DaoException;
    List<Order> findAllNotCompletedOrderByUser(long userId) throws ServiceException, DaoException;
    /*boolean addProductInOrder(long productId, long userId, int quantity) throws ServiceException, DaoException;
    boolean addProductQuantityInOrder(long orderId, int quantity) throws ServiceException, DaoException;*/



}
