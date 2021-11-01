package com.pharmacy.traning.model.dao;

import com.pharmacy.traning.model.entity.User;
import com.pharmacy.traning.exception.DaoException;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

/**
 * @author Besarab Victor
 * The interface User dao find data in database.
 */
public interface UserDao {

    /**
     * Create user boolean.
     *
     * @param user the user
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean createUser(User user) throws DaoException;

    /**
     * Delete user by id boolean.
     *
     * @param id the id
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean deleteUserById(long id) throws DaoException;

    /**
     * Update user by id boolean.
     *
     * @param user the user
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean updateUserById(User user) throws DaoException;

    /**
     * Update cash by id boolean.
     *
     * @param cash the cash
     * @param id   the id
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean updateCashById(Double cash, long id) throws DaoException;

    /**
     * Reduce cash by id boolean.
     *
     * @param cash       the cash
     * @param id         the id
     * @param connection the connection
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean reduceCashById(Double cash, long id, Connection connection) throws DaoException;

    /**
     * Change user status on in register boolean.
     *
     * @param userId the user id
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean changeUserStatusOnInRegister(long userId) throws DaoException;

    /**
     * Change user status on active boolean.
     *
     * @param userId the user id
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean changeUserStatusOnActive(long userId) throws DaoException;

    /**
     * Find all delete user list.
     *
     * @return the list
     * @throws DaoException the dao exception
     */
    List<User> findAllDeleteUser() throws DaoException;

    /**
     * Find all non delete user list.
     *
     * @return the list
     * @throws DaoException the dao exception
     */
    List<User> findAllNonDeleteUser() throws DaoException;

    /**
     * Search delete user by name list.
     *
     * @param name the name
     * @return the list
     * @throws DaoException the dao exception
     */
    List<User> searchDeleteUserByName(String name) throws DaoException;

    /**
     * Search non delete user by name list.
     *
     * @param name the name
     * @return the list
     * @throws DaoException the dao exception
     */
    List<User> searchNonDeleteUserByName(String name) throws DaoException;

    /**
     * Check authorisation optional.
     *
     * @param login    the login
     * @param password the password
     * @return the optional
     * @throws DaoException the dao exception
     */
    Optional<User> checkAuthorisation(String login, String password) throws DaoException;

    /**
     * Update photo by id boolean.
     *
     * @param path the path
     * @param id   the id
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean updatePhotoById(String path, long id) throws DaoException;

    /**
     * Check is admin boolean.
     *
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean checkIsAdmin() throws DaoException;

    /**
     * Find user cash by id double.
     *
     * @param userId the user id
     * @return the double
     * @throws DaoException the dao exception
     */
    double findUserCashById(long userId) throws DaoException;
}
