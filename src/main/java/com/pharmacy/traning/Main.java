package com.pharmacy.traning;

import com.pharmacy.traning.exception.CommandException;
import com.pharmacy.traning.exception.DaoException;
import com.pharmacy.traning.exception.ServiceException;

public class Main {
    public static void main(String[] args) {
        try {
            try {
                try {
                    try {
                        throw new RuntimeException("hgbfvdcsxa");
                    } catch (RuntimeException e) {
                        throw new DaoException("DAO", e);
                    }
                } catch (DaoException e) {
                    throw new ServiceException("Service", e);
                }
            } catch (ServiceException e) {
                throw new CommandException("Command", e);
            }
        } catch (CommandException e) {
            for (var i: e.getStackTrace()) {
                System.out.println(i.toString());
            }
            System.out.println();
        }
    }
}
