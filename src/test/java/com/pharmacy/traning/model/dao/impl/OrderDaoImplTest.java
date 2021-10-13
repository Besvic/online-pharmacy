package com.pharmacy.traning.model.dao.impl;

import com.pharmacy.traning.exception.DaoException;
import com.pharmacy.traning.model.entity.Order;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

class OrderDaoImplTest {

    @Mock
    private OrderDaoImpl instance;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void addOrderTrue() throws DaoException {
        when(instance.addOrder(any(Order.class))).thenReturn(true);
        boolean result = instance.addOrder(new Order.OrderBuilder().createOrder());
        Assertions.assertTrue(result);
    }

    @Test
    void addOrderFalse() throws DaoException {
        when(instance.addOrder(any(Order.class))).thenReturn(false);
        boolean result = instance.addOrder(new Order.OrderBuilder().createOrder());
        Assertions.assertFalse(result);
    }

    /*@Test(expected = DaoException.class)
    void addOrderDaoException() throws DaoException {
        when(instance.addOrder(any(Order.class))).thenThrow(DaoException.class);
        *//*assertThrows(DaoException.class, instance.addOrder(new Order.OrderBuilder().createOrder()));*//*
        instance.addOrder(new Order.OrderBuilder().createOrder();
    }*/
    @Test
    void addProductQuantityInOrder() {
    }

    @Test
    void deleteOrderById() {
    }

    @Test
    void completedOrder() {
    }

    @Test
    void checkIsOrder() {
    }

    @Test
    void findAllNotCompletedOrderById() {
    }

    @Test
    void findAllCompletedOrder() {
    }

    @Test
    void findAllCompletedOrderByUserId() {
    }

    @Test
    void searchOrderByName() {
    }

    @Test
    void findOrderById() {
    }
}