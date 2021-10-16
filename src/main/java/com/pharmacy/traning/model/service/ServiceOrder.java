package com.pharmacy.traning.model.service;

import com.pharmacy.traning.exception.ServiceException;
import com.pharmacy.traning.model.entity.Order;

import java.time.LocalDate;
import java.util.List;

/**
 * The interface Service order.
 */
public interface ServiceOrder {
    /**
     * Add order boolean.
     *
     * @param productId the product id
     * @param userId    the user id
     * @param quantity  the quantity
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean addOrder(long productId, long userId, String quantity) throws ServiceException;

    /**
     * Pay order boolean.
     *
     * @param orderId       the order id
     * @param pharmacyId    the pharmacy id
     * @param orderQuantity the order quantity
     * @param orderPrice    the order price
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean payOrder(long orderId, long pharmacyId, String orderQuantity, String orderPrice) throws ServiceException;

    /**
     * Delete order by id boolean.
     *
     * @param orderId the order id
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean deleteOrderById(long orderId) throws ServiceException;

    /**
     * Find all not completed order by user list.
     *
     * @param userId the user id
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Order> findAllNotCompletedOrderByUser(long userId) throws ServiceException;

    /**
     * Find all completed order by user id list.
     *
     * @param userId the user id
     * @param date   the date
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Order> findAllCompletedOrderByUserId(long userId, LocalDate date) throws ServiceException;

    /**
     * Find all completed order list.
     *
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Order> findAllCompletedOrder() throws ServiceException;

    /**
     * Search order by name list.
     *
     * @param name the name
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Order> searchOrderByName(String name) throws ServiceException;


    // TODO: 13.10.2021 del
    /*boolean addProductInOrder(long productId, long userId, int quantity) throws ServiceException, DaoException;
    boolean addProductQuantityInOrder(long orderId, int quantity) throws ServiceException, DaoException;*/



}
