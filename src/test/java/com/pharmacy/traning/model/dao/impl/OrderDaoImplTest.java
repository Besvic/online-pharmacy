package com.pharmacy.traning.model.dao.impl;

import com.pharmacy.traning.exception.DaoException;
import com.pharmacy.traning.model.entity.Order;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.*;
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
        when(instance.addOrder(any(Order.class))).thenReturn(true); // TODO: 14.10.2021 как правильно писать передаваемый параметр ???
        boolean actual = instance.addOrder(new Order.OrderBuilder().createOrder());
        assertTrue(actual);
    }

    @Test
    void addProductQuantityInOrder() throws DaoException {
        when(instance.addProductQuantityInOrder(anyLong(), anyInt())).thenReturn(true);
        boolean actual = instance.addProductQuantityInOrder(anyLong(), anyInt());
        assertTrue(actual);
    }

    @Test
    void deleteOrderById() throws DaoException {
        when(instance.deleteOrderById(anyLong())).thenReturn(true);
        boolean actual = instance.deleteOrderById(anyLong());
        assertTrue(actual);
    }

    @Test
    void completedOrder() throws DaoException {
        when(instance.completedOrder(anyLong(), anyLong(), any(Connection.class))).thenReturn(true);
        boolean actual = instance.completedOrder(anyLong(), anyLong(), any(Connection.class));
        assertTrue(actual);
    }

    @Test
    void checkIsOrder() throws DaoException {
        when(instance.checkIsOrder(anyLong(), anyLong())).thenReturn(Optional.of(new Order.OrderBuilder().createOrder()));
        Optional<Order> actual = instance.checkIsOrder(anyLong(), anyLong());
        Optional<Order> expected = Optional.of(new Order.OrderBuilder().createOrder());
        assertEquals(actual, expected);
    }

    @Test
    void findAllNotCompletedOrderById() throws DaoException {
        when(instance.findAllNotCompletedOrderById(anyLong())).thenReturn(List.of(new Order.OrderBuilder().createOrder()));
        List<Order> actual = instance.findAllNotCompletedOrderById(anyLong());
        List<Order> expected = List.of(new Order.OrderBuilder().createOrder());
        assertEquals(actual, expected);
    }

    @Test
    void findAllCompletedOrder() throws DaoException {
        when(instance.findAllCompletedOrder()).thenReturn(List.of(new Order.OrderBuilder().createOrder()));
        List<Order> actual = instance.findAllCompletedOrder();
        List<Order> expected = List.of(new Order.OrderBuilder().createOrder());
        assertEquals(actual, expected);
    }

    @Test
    void findAllCompletedOrderByUserId() throws DaoException {
        when(instance.findAllCompletedOrderByUserId(anyLong(), any(LocalDate.class))).thenReturn(List.of(new Order.OrderBuilder().createOrder()));
        List<Order> actual = instance.findAllCompletedOrderByUserId(anyLong(), any(LocalDate.class));
        List<Order> expected = List.of(new Order.OrderBuilder().createOrder());
        assertEquals(actual, expected);
    }

    @Test
    void searchOrderByName() throws DaoException {
        when(instance.searchOrderByName(anyString())).thenReturn(List.of(new Order.OrderBuilder().createOrder()));
        List<Order> actual = instance.searchOrderByName(anyString());
        List<Order> expected = List.of(new Order.OrderBuilder().createOrder());
        assertEquals(actual, expected);
    }

    @Test
    void findOrderById() throws DaoException {
        when(instance.findOrderById(anyLong(), any(Connection.class))).thenReturn(Optional.of(new Order.OrderBuilder().createOrder()));
        Optional<Order> actual = instance.findOrderById(anyLong(), any(Connection.class));
        Optional<Order> expected = Optional.of(new Order.OrderBuilder().createOrder());
        assertEquals(actual, expected);
    }
    
    // false test
    @Test
    void addOrderFalse() throws DaoException {
        when(instance.addOrder(any(Order.class))).thenReturn(false);
        boolean result = instance.addOrder(new Order.OrderBuilder().createOrder());
        Assertions.assertFalse(result);
    }

    @Test
    void addProductQuantityInOrderFalse() throws DaoException {
        when(instance.addProductQuantityInOrder(anyLong(), anyInt())).thenReturn(false);
        boolean actual = instance.addProductQuantityInOrder(anyLong(), anyInt());
        assertFalse(actual);
    }

    @Test
    void deleteOrderByIdFalse() throws DaoException {
        when(instance.deleteOrderById(anyLong())).thenReturn(false);
        boolean actual = instance.deleteOrderById(anyLong());
        assertFalse(actual);
    }

    @Test
    void completedOrderFalse() throws DaoException {
        when(instance.completedOrder(anyLong(), anyLong(), any(Connection.class))).thenReturn(false);
        boolean actual = instance.completedOrder(anyLong(), anyLong(), any(Connection.class));
        assertFalse(actual);
    }

    @Test
    void checkIsOrderFalse() throws DaoException {
        when(instance.checkIsOrder(anyLong(), anyLong())).thenReturn(Optional.empty());
        Optional<Order> actual = instance.checkIsOrder(anyLong(), anyLong());
        Optional<Order> expected = Optional.empty();
        assertEquals(actual, expected);
    }

    @Test
    void findAllNotCompletedOrderByIdFalse() throws DaoException {
        when(instance.findAllNotCompletedOrderById(anyLong())).thenReturn(List.of()); // TODO: 14.10.2021  надо ли класть null?
        List<Order> actual = instance.findAllNotCompletedOrderById(anyLong());
        List<Order> expected = List.of();
        assertEquals(actual, expected);
    }

    @Test
    void findAllCompletedOrderFalse() throws DaoException {
        when(instance.findAllCompletedOrder()).thenReturn(List.of());
        List<Order> actual = instance.findAllCompletedOrder();
        List<Order> expected = List.of();
        assertEquals(actual, expected);
    }

    @Test
    void findAllCompletedOrderByUserIdFalse() throws DaoException {
        when(instance.findAllCompletedOrderByUserId(anyLong(), any(LocalDate.class))).thenReturn(List.of());
        List<Order> actual = instance.findAllCompletedOrderByUserId(anyLong(), any(LocalDate.class));
        List<Order> expected = List.of();
        assertEquals(actual, expected);
    }

    @Test
    void searchOrderByNameFalse() throws DaoException {
        when(instance.searchOrderByName(anyString())).thenReturn(List.of());
        List<Order> actual = instance.searchOrderByName(anyString());
        List<Order> expected = List.of();
        assertEquals(actual, expected);
    }

    @Test
    void findOrderByIdFalse() throws DaoException {
        when(instance.findOrderById(anyLong(), any(Connection.class))).thenReturn(Optional.empty());
        Optional<Order> actual = instance.findOrderById(anyLong(), any(Connection.class));
        Optional<Order> expected = Optional.empty();
        assertEquals(actual, expected);
    }
}