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
    static int a = 1;
    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        System.out.println(Test.i);
        Test.i = 3;
        System.out.println(Test.i);

    }
}

class Test{
    static int i = 1;
}