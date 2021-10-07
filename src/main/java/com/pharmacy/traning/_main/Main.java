package com.pharmacy.traning._main;

import com.pharmacy.traning.model.dao.ColumnName;
import com.pharmacy.traning.model.entity.Position;
import com.pharmacy.traning.model.entity.User;
import com.pharmacy.traning.model.entity.UserStatus;
import com.pharmacy.traning.exception.DaoException;
import com.pharmacy.traning.model.dao.impl.UserDaoImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Locale;

import static com.pharmacy.traning.controller.comand.SessionAttribute.ADMIN;

public class Main {
    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
       /* User user = new User.UserBuilder().
                setUserStatus(String.valueOf(UserStatus.IN_REGISTER))
                .setName("Victor2")
                .setLogin("victor21")
                .setPassword("victor2")
                .setCash(12)
                .setPosition(ADMIN)
                .createUser();*/


        /*System.out.println(user.toString());*/

        int d = Integer.parseInt("sdfvsd");


    }
}
