package com.pharmacy.traning.service;

import com.pharmacy.traning.exception.DaoException;
import com.pharmacy.traning.exception.ServiceException;
import com.pharmacy.traning.model.entity.CreditCard;
import com.pharmacy.traning.model.entity.User;
import com.pharmacy.traning.model.entity.UserStatus;

import java.util.List;
import java.util.Optional;

public interface ServiceUser {

    boolean registration(Optional<User> user) throws ServiceException, DaoException;
    boolean updatePhotoById(String path, long id) throws ServiceException;
    boolean updateUserById(User user, String pass, String name) throws ServiceException, DaoException;
    boolean changeUserStatusByUserId(String userId, String currentStatus) throws ServiceException, DaoException;
    boolean deleteUserByUserId(String strId) throws ServiceException, DaoException;
    List<User> findAllDeleteUser() throws ServiceException, DaoException;
    List<User> findAllNonDeleteUser() throws ServiceException, DaoException;
    List<User> searchDeleteUserByName(String name) throws ServiceException, DaoException;
    List<User> searchNonDeleteUserByName(String name) throws ServiceException, DaoException;
    double findUserCashById(long userId) throws ServiceException, DaoException;
   /* List<User> findAllInRegisterUser() throws ServiceException, DaoException;*/

    boolean updateCashById(CreditCard creditCard, long id) throws ServiceException, DaoException;
    Optional<User> signIn(String email, String password) throws ServiceException, DaoException;

}
