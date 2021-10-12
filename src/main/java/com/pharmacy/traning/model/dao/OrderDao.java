package com.pharmacy.traning.model.dao;

import com.pharmacy.traning.model.entity.Order;
import com.pharmacy.traning.exception.DaoException;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * The interface Order dao.
 */
public interface OrderDao {

    /**
     * Add order boolean.
     *
     * @param order the order
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean addOrder(Order order) throws DaoException;
    boolean addProductQuantityInOrder(long orderId, int quantity) throws DaoException;

    /**
     * Delete order by id boolean.
     *
     * @param orderId the order id
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean deleteOrderById(long orderId) throws DaoException;

    /**
     * Completed order boolean.
     *
     * @param orderId    the order id
     * @param pharmacyId the pharmacy id
     * @param connection the connection
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean completedOrder(long orderId, long pharmacyId, Connection connection) throws DaoException;

    /**
     * Check is order optional.
     *
     * @param userId    the user id
     * @param productId the product id
     * @return the optional
     * @throws DaoException the dao exception
     */
    Optional<Order> checkIsOrder(long userId, long productId) throws DaoException;

    /**
     * Find all not completed order by id list.
     *
     * @param userId the user id
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Order> findAllNotCompletedOrderById(long userId) throws DaoException;

    /**
     * Find all completed order list.
     *
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Order> findAllCompletedOrder() throws DaoException;

    /**
     * Find all completed order by user id list.
     *
     * @param userId the user id
     * @param date   the date
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Order> findAllCompletedOrderByUserId(long userId, LocalDate date) throws DaoException;

    /**
     * Search order by name list.
     *
     * @param name the name
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Order> searchOrderByName(String name) throws DaoException;

    /**
     * Find order by id optional.
     *
     * @param orderId    the order id
     * @param connection the connection
     * @return the optional
     * @throws DaoException the dao exception
     */
    Optional<Order> findOrderById(long orderId, Connection connection) throws DaoException;

}
