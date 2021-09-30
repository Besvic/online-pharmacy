package com.pharmacy.traning.model.dao;

import com.pharmacy.traning.model.entity.User;
import com.pharmacy.traning.exception.DaoException;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public interface UserDao {

    boolean createUser(User user) throws DaoException;
    boolean deleteUserById(long id) throws DaoException;
    boolean updateUserById(User user, long id) throws DaoException;
    boolean updateCashById(Double cash, long id) throws DaoException;
    boolean reduceCashById(Double cash, long id, Connection connection) throws DaoException;
    boolean changeUserStatusOnInRegister(long userId) throws DaoException;
    boolean changeUserStatusOnActive(long userId) throws DaoException;
    List<User> findAllInRegisterUser() throws DaoException;
    List<User> findAllDeleteUser() throws DaoException;
    List<User> findAllNonDeleteUser() throws DaoException;
    Optional<User> checkAuthorisation(String login, String password) throws DaoException;
    Optional<User> findUserById(long userId, Connection connection) throws DaoException;
    boolean updatePhotoById(String path, long id) throws DaoException;
    boolean checkIsAdmin() throws DaoException;





}
