package com.example.online_pharmacy._main;

import com.example.online_pharmacy.entity.Order;
import com.example.online_pharmacy.exception.DaoException;
import com.example.online_pharmacy.exception.DatabaseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        Order order = new Order.OrderBuilder().createOrder();
    }
}
