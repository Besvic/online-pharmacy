package com.pharmacy.traning;

import com.pharmacy.traning.exception.CommandException;
import com.pharmacy.traning.exception.DaoException;
import com.pharmacy.traning.exception.ServiceException;
import com.pharmacy.traning.model.service.impl.ServiceOrderImpl;

public class Main {
    public static void main(String[] args) {
    /*    try {
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
        }*/



        byte b = (byte) 128;
        int i = 0;
        int[] a = {3, 6};
        a[i] = i = 9;
        a.clone();

        System.out.println(i + a[0] );
    }
}
