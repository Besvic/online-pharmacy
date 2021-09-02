package com.example.online_pharmacy._main;

import com.example.online_pharmacy.entity.Position;
import com.example.online_pharmacy.entity.User;
import com.example.online_pharmacy.entity.UserStatus;
import com.example.online_pharmacy.exception.DaoException;
import com.example.online_pharmacy.model.dao.impl.UserDaoImpl;
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
