package com.pharmacy.traning._main;

import com.pharmacy.traning.model.entity.Position;
import com.pharmacy.traning.model.entity.User;
import com.pharmacy.traning.model.entity.UserStatus;
import com.pharmacy.traning.exception.DaoException;
import com.pharmacy.traning.model.dao.impl.UserDaoImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        User user = new User.UserBuilder().
                setUserStatus(UserStatus.IN_REGISTER)
                .setName("Victor2")
                .setLogin("victor21")
                .setPassword("victor2")
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
