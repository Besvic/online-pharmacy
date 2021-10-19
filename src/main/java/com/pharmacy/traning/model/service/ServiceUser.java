package com.pharmacy.traning.model.service;

import com.pharmacy.traning.exception.ServiceException;
import com.pharmacy.traning.model.entity.CreditCard;
import com.pharmacy.traning.model.entity.User;

import java.util.List;
import java.util.Optional;

/**
 * @author Besarab Victor
 * The interface Service user definition all general methods for validate data before using Dao.
 */
public interface ServiceUser {

    /**
     * Registration check input data and send to dao method.
     *
     * @param user the user
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean registration(User user) throws ServiceException;

    /**
     * Update photo by id check input data and send to dao method.
     *
     * @param path the path
     * @param id   the id
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean updatePhotoById(String path, long id) throws ServiceException;

    /**
     * Update user by id check input data and send to dao method.
     *
     * @param user the user
     * @param pass the pass
     * @param name the name
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean updateUserById(User user, String pass, String name) throws ServiceException;

    /**
     * Change user status by user id check input data and send to dao method.
     *
     * @param userId        the user id
     * @param currentStatus the current status
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean changeUserStatusByUserId(String userId, String currentStatus) throws ServiceException;

    /**
     * Delete user by user id check input data and send to dao method.
     *
     * @param strId the str id
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean deleteUserByUserId(String strId) throws ServiceException;

    /**
     * Find all delete user check input data and send to dao method.
     *
     * @return the list
     * @throws ServiceException the service exception
     */
    List<User> findAllDeleteUser() throws ServiceException;

    /**
     * Find all non delete user check input data and send to dao method.
     *
     * @return the list
     * @throws ServiceException the service exception
     */
    List<User> findAllNonDeleteUser() throws ServiceException;

    /**
     * Search delete user by name check input data and send to dao method.
     *
     * @param name the name
     * @return the list
     * @throws ServiceException the service exception
     */
    List<User> searchDeleteUserByName(String name) throws ServiceException;

    /**
     * Search non delete user by name check input data and send to dao method.
     *
     * @param name the name
     * @return the list
     * @throws ServiceException the service exception
     */
    List<User> searchNonDeleteUserByName(String name) throws ServiceException;

    /**
     * Find user cash by id check input data and send to dao method.
     *
     * @param userId the user id
     * @return the double
     * @throws ServiceException the service exception
     */
    double findUserCashById(long userId) throws ServiceException;

   /* List<User> findAllInRegisterUser() throws ServiceException;*/

    /**
     * Update cash by id check input data and send to dao method.
     *
     * @param creditCard the credit card
     * @param id         the id
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean updateCashById(CreditCard creditCard, long id) throws ServiceException;

    /**
     * Sign in check input data and send to dao method.
     *
     * @param email    the email
     * @param password the password
     * @return the optional
     * @throws ServiceException the service exception
     */
    Optional<User> signIn(String email, String password) throws ServiceException;

}
