package com.pharmacy.traning.service;

import com.pharmacy.traning.exception.DaoException;
import com.pharmacy.traning.exception.ServiceException;
import com.pharmacy.traning.model.entity.User;

import java.util.Optional;

public interface ServiceUser {

    boolean registration(User user) throws ServiceException, DaoException;
    boolean updatePhotoById(String path, long id) throws ServiceException;
    boolean updateUserById(User user, long id) throws ServiceException;
    boolean updateCashById(Double cash, long id) throws ServiceException;
    Optional<User> signIn(String email, String password) throws ServiceException;

}
