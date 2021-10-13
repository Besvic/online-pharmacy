package com.pharmacy.traning.service;

import com.pharmacy.traning.exception.DaoException;
import com.pharmacy.traning.exception.ServiceException;
import com.pharmacy.traning.model.entity.CreditCard;
import com.pharmacy.traning.model.entity.User;
import com.pharmacy.traning.model.entity.UserStatus;

import java.util.List;
import java.util.Optional;

/**
 * The interface Service user.
 */
public interface ServiceUser {

    /**
     * Registration boolean.
     *
     * @param user the user
     * @return the boolean
     * @throws ServiceException the service exception
     * @throws DaoException     the dao exception
     */
    boolean registration(User user) throws ServiceException, DaoException;

    /**
     * Update photo by id boolean.
     *
     * @param path the path
     * @param id   the id
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean updatePhotoById(String path, long id) throws ServiceException;

    /**
     * Update user by id boolean.
     *
     * @param user the user
     * @param pass the pass
     * @param name the name
     * @return the boolean
     * @throws ServiceException the service exception
     * @throws DaoException     the dao exception
     */
    boolean updateUserById(User user, String pass, String name) throws ServiceException, DaoException;

    /**
     * Change user status by user id boolean.
     *
     * @param userId        the user id
     * @param currentStatus the current status
     * @return the boolean
     * @throws ServiceException the service exception
     * @throws DaoException     the dao exception
     */
    boolean changeUserStatusByUserId(String userId, String currentStatus) throws ServiceException, DaoException;

    /**
     * Delete user by user id boolean.
     *
     * @param strId the str id
     * @return the boolean
     * @throws ServiceException the service exception
     * @throws DaoException     the dao exception
     */
    boolean deleteUserByUserId(String strId) throws ServiceException, DaoException;

    /**
     * Find all delete user list.
     *
     * @return the list
     * @throws ServiceException the service exception
     * @throws DaoException     the dao exception
     */
    List<User> findAllDeleteUser() throws ServiceException, DaoException;

    /**
     * Find all non delete user list.
     *
     * @return the list
     * @throws ServiceException the service exception
     * @throws DaoException     the dao exception
     */
    List<User> findAllNonDeleteUser() throws ServiceException, DaoException;

    /**
     * Search delete user by name list.
     *
     * @param name the name
     * @return the list
     * @throws ServiceException the service exception
     * @throws DaoException     the dao exception
     */
    List<User> searchDeleteUserByName(String name) throws ServiceException, DaoException;

    /**
     * Search non delete user by name list.
     *
     * @param name the name
     * @return the list
     * @throws ServiceException the service exception
     * @throws DaoException     the dao exception
     */
    List<User> searchNonDeleteUserByName(String name) throws ServiceException, DaoException;

    /**
     * Find user cash by id double.
     *
     * @param userId the user id
     * @return the double
     * @throws ServiceException the service exception
     * @throws DaoException     the dao exception
     */
    double findUserCashById(long userId) throws ServiceException, DaoException;
   /* List<User> findAllInRegisterUser() throws ServiceException, DaoException;*/

    /**
     * Update cash by id boolean.
     *
     * @param creditCard the credit card
     * @param id         the id
     * @return the boolean
     * @throws ServiceException the service exception
     * @throws DaoException     the dao exception
     */
    boolean updateCashById(CreditCard creditCard, long id) throws ServiceException, DaoException;

    /**
     * Sign in optional.
     *
     * @param email    the email
     * @param password the password
     * @return the optional
     * @throws ServiceException the service exception
     * @throws DaoException     the dao exception
     */
    Optional<User> signIn(String email, String password) throws ServiceException, DaoException;

}
