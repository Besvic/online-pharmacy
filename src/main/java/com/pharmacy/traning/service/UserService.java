package com.pharmacy.traning.service;

import com.pharmacy.traning.exception.DaoException;
import com.pharmacy.traning.exception.ServiceException;
import com.pharmacy.traning.model.entity.User;

import java.util.Optional;

public interface UserService {

    boolean registration(User user) throws ServiceException, DaoException;
    Optional<User> signIn(String email, String password) throws ServiceException;
}
