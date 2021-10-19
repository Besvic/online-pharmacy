package com.pharmacy.traning.model.service.impl;

import com.pharmacy.traning.exception.ServiceException;
import com.pharmacy.traning.model.entity.Order;
import com.pharmacy.traning.model.service.ServiceOrder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.when;

class ServiceOrderImplTest {

    @Mock
    private static ServiceOrderImpl instanceMock;
    private static final ServiceOrder instanceOrder = ServiceOrderImpl.getInstance();

    @BeforeEach
    private void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    // Correct result

    @Test
    void addOrder() throws ServiceException {
        when(instanceMock.addOrder(anyLong(), anyLong(), anyString())).thenReturn(true);
        boolean actual = instanceMock.addOrder(anyLong(), anyLong(), anyString());
        assertTrue(actual);
    }

    @Test
    void payOrder() throws ServiceException {
        when(instanceMock.payOrder(anyLong(), anyLong(), anyString(), anyString())).thenReturn(true);
        boolean actual = instanceMock.payOrder(anyLong(), anyLong(), anyString(), anyString());
        assertTrue(actual);
    }

    @Test
    void deleteOrderById() throws ServiceException {
        when(instanceMock.deleteOrderById(anyLong())).thenReturn(true);
        boolean actual = instanceMock.deleteOrderById(anyLong());
        assertTrue(actual);
    }

    @Test
    void findAllNotCompletedOrderByUser() throws ServiceException {
        when(instanceMock.findAllNotCompletedOrderByUser(anyLong())).thenReturn(List.of(new Order.OrderBuilder().createOrder()));
        List<Order> actual = instanceMock.findAllNotCompletedOrderByUser(anyLong());
        List<Order> expected = List.of(new Order.OrderBuilder().createOrder());
        assertEquals(expected, actual);
    }

    @Test
    void findAllCompletedOrderByUserId() throws ServiceException {
        when(instanceMock.findAllCompletedOrderByUserId(anyLong(), anyString())).thenReturn(List.of(new Order.OrderBuilder().createOrder()));
        List<Order> actual = instanceMock.findAllCompletedOrderByUserId(anyLong(), anyString());
        List<Order> expected = List.of(new Order.OrderBuilder().createOrder());
        assertEquals(expected, actual);
    }

    @Test
    void findAllCompletedOrder() throws ServiceException {
        when(instanceMock.findAllCompletedOrder()).thenReturn(List.of(new Order.OrderBuilder().createOrder()));
        List<Order> actual = instanceMock.findAllCompletedOrder();
        List<Order> expected = List.of(new Order.OrderBuilder().createOrder());
        assertEquals(expected, actual);
    }

    @Test
    void searchOrderByName() throws ServiceException {
        when(instanceMock.searchOrderByName(anyString())).thenReturn(List.of(new Order.OrderBuilder().createOrder()));
        List<Order> actual = instanceMock.searchOrderByName(anyString());
        List<Order> expected = List.of(new Order.OrderBuilder().createOrder());
        assertEquals(expected, actual);
    }

    // Incorrect result

    @Test
    void addOrderThrowException() {
        assertThrows(ServiceException.class, () -> {
            instanceOrder.addOrder(12, 12, "str");
        });
    }

    @Test
    void payOrderThrowException() throws ServiceException {
        when(instanceMock.payOrder(anyLong(), anyLong(), anyString(), anyString())).thenThrow(ServiceException.class);
        assertThrows(ServiceException.class, () -> {
            instanceMock.payOrder(anyLong(), anyLong(), anyString(), anyString());
        });
    }

    @Test
    void deleteOrderByIdThrowException() throws ServiceException {
        when(instanceMock.deleteOrderById(anyLong())).thenThrow(ServiceException.class);
        assertThrows(ServiceException.class, () -> {
            instanceMock.deleteOrderById(12);
        });
    }

    @Test
    void findAllNotCompletedOrderByUserThrowException() throws ServiceException {
        when(instanceMock.findAllNotCompletedOrderByUser(anyLong())).thenThrow(ServiceException.class);
        assertThrows(ServiceException.class, () -> {
            instanceMock.findAllNotCompletedOrderByUser(12);
        });
    }

    @Test
    void findAllCompletedOrderByUserIdThrowException()  {
        assertThrows(ServiceException.class, () -> {
            instanceOrder.findAllCompletedOrderByUserId(12, "sadfgh");
        });
    }

    @Test
    void findAllCompletedOrderThrowException() throws ServiceException {
        when(instanceMock.findAllCompletedOrder()).thenThrow(ServiceException.class);
        assertThrows(ServiceException.class, () -> {
            instanceMock.findAllCompletedOrder();
        });
    }

    @Test
    void searchOrderByNameThrowException() {
        assertThrows((ServiceException.class), () ->{
            instanceOrder.searchOrderByName(null);
        });

    }
}