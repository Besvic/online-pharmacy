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
import static com.pharmacy.traning.controller.comand.SessionAttribute.USER;

/**
 * The type Main.
 */
public class Main {
    /**
     * The A.
     */
    static int a = 1;
    private static final Logger logger = LogManager.getLogger();

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        User user = new User.UserBuilder().setPosition(USER).createUser();
        System.out.println(user.getPosition().getValue().equals(ADMIN));


    }
}

/**
 * The type Test.
 */
class Test{
    /**
     * The .
     */
    static int i = 1;
}