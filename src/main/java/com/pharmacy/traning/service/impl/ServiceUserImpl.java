package com.pharmacy.traning.service.impl;

import com.pharmacy.traning.exception.DaoException;
import com.pharmacy.traning.exception.ServiceException;
import com.pharmacy.traning.model.dao.impl.UserDaoImpl;
import com.pharmacy.traning.model.entity.User;
import com.pharmacy.traning.model.util.CryptorPassword;
import com.pharmacy.traning.service.ServiceUser;
import com.pharmacy.traning.validator.impl.UserValidatorImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class ServiceUserImpl implements ServiceUser {

    private static final Logger logger = LogManager.getLogger();
    private static ServiceUserImpl instance;

    public static ServiceUserImpl getInstance(){
        if (instance == null)
            instance = new ServiceUserImpl();
        return instance;
    }

    private ServiceUserImpl(){

    }

    @Override
    public boolean registration(User user) throws ServiceException {
        if (UserValidatorImpl.getInstance().isNullObject(user)) {
            try {
                user.setPassword(CryptorPassword.getInstance().encryptor(user.getPassword()));
                return UserDaoImpl.getInstance().createUser(user);
            } catch (DaoException e) {
                logger.warn("Not available create person, because this email already saved!", e);
                throw new ServiceException("Not available create person, because this email already saved!", e);
            }
        }
        else {
            logger.error("Not available create person, because this data is null!");
            throw new ServiceException("Not available create person, because this data is null!");
        }
    }

    @Override
    public boolean updatePhotoById(String path, long id) throws ServiceException {
        try {
            return UserDaoImpl.getInstance().updatePhotoById(path, id);
        } catch (DaoException e) {
            logger.error("Not available updatePhotoById!", e);
            throw new ServiceException("Not available updatePhotoById!", e);
        }
    }

    @Override
    public boolean updateUserById(User user, long id) throws ServiceException {
        if (UserValidatorImpl.getInstance().isNullObject(user)){
            try {
                return UserDaoImpl.getInstance().updateUserById(user, id);
            } catch (DaoException e) {
                logger.error("Not available updateUserById!", e);
                throw new ServiceException("Not available updateUserById!", e);
            }
        }
        return false;
    }

    @Override
    public boolean updateCashById(Double cash, long id) throws ServiceException {
        return false;
    }

    @Override
    public Optional<User> signIn(String email, String password) throws ServiceException {
        if (!email.isEmpty() && !password.isEmpty()){
            try {
                String hashPassword = CryptorPassword.getInstance().encryptor(password);
                return UserDaoImpl.getInstance().checkAuthorisation(email, hashPassword);
            } catch (DaoException e) {
                logger.warn("Enter is not successful, email or password entered incorrect.", e);
                throw new ServiceException("Enter is not successful, email or password entered incorrect.", e);
            }
        }
        return Optional.empty();
    }
}
