package com.pharmacy.traning.model.dao;

import com.pharmacy.traning.model.entity.User;
import com.pharmacy.traning.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface UserDao {

    boolean createUser(User user) throws DaoException;
    boolean deleteUserById(int id) throws DaoException;
    List<User> findUserByStatus(String status) throws DaoException;
    Optional<User> checkAuthorisation(String login, String password) throws DaoException;




}
