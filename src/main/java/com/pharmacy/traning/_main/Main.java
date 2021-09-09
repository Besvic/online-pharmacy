package com.pharmacy.traning._main;

import com.pharmacy.traning.entity.Position;
import com.pharmacy.traning.entity.User;
import com.pharmacy.traning.entity.UserStatus;
import com.pharmacy.traning.exception.DaoException;
import com.pharmacy.traning.model.dao.impl.UserDaoImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        /*Order order = new Order.OrderBuilder().createOrder();*/
        User user = new User.UserBuilder().
                setUserStatus(UserStatus.IN_REGISTER)
                .setName("Victor1")
                .setLogin("victor1")
                .setPassword("victor1")
                .setCash(12)
                .setPosition(Position.ADMIN)
                .createUser();

        try {
            new UserDaoImpl().createUser(user);
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }
}
