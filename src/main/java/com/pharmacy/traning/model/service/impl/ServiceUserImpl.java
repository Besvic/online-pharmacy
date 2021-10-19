package com.pharmacy.traning.model.service.impl;

import com.pharmacy.traning.exception.DaoException;
import com.pharmacy.traning.exception.ServiceException;
import com.pharmacy.traning.model.dao.UserDao;
import com.pharmacy.traning.model.dao.impl.UserDaoImpl;
import com.pharmacy.traning.model.entity.CreditCard;
import com.pharmacy.traning.model.entity.User;
import com.pharmacy.traning.model.entity.UserStatus;
import com.pharmacy.traning.model.util.CryptorPassword;
import com.pharmacy.traning.model.service.ServiceUser;
import com.pharmacy.traning.model.validator.Validator;
import com.pharmacy.traning.model.validator.impl.ValidatorImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

import static com.pharmacy.traning.controller.command.SessionAttribute.ADMIN;

/**
 * @author Besarab Victor
 * The type Service user.
 */
public class ServiceUserImpl implements ServiceUser {

    private static final Logger logger = LogManager.getLogger();
    private static final UserDao userDao = UserDaoImpl.getInstance();
    private static final Validator validator = ValidatorImpl.getInstance();
    private static ServiceUserImpl instance;

    /**
     * Get instance service user.
     *
     * @return the service user
     */
    public static ServiceUserImpl getInstance(){
        if (instance == null)
            instance = new ServiceUserImpl();
        return instance;
    }

    private ServiceUserImpl(){

    }

    @Override
    public boolean registration(User user) throws ServiceException {
        if (user != null && validator.isName(user.getName()) &&
                validator.isEmail(user.getLogin()) && validator.isPassword(user.getPassword())) {
            CryptorPassword crypt = CryptorPassword.getInstance();
            try {
                if (user.getPosition().getValue().equals(ADMIN)){
                    if (!userDao.checkIsAdmin()) {
                        user.setPassword(crypt.encryptor(user.getPassword()));
                        return userDao.createUser(user);
                    }
                    logger.error("First administrator completed registration! ");
                    throw new ServiceException("First administrator completed registration! ");
                } else {
                    user.setPassword(crypt.encryptor(user.getPassword()));
                    if (userDao.createUser(user)){
                        return true;
                    }
                    logger.error("This is email used! ");
                    throw new ServiceException("This is email used! ");
                }
            } catch (DaoException e) {
                throw new ServiceException("ServiceException in method registration. " + e);
            }
        }
        logger.error("Not available create person, because this data incorrect! ");
        throw new ServiceException("Not available create person, because this data incorrect! ");
    }

    @Override
    public boolean updatePhotoById(String path, long id) throws ServiceException {
        try {
            return userDao.updatePhotoById(path, id);
        } catch (DaoException e) {
            throw new ServiceException("ServiceException in method updatePhotoById. " + e);
        }
    }

    @Override
    public boolean updateUserById(User user, String pass, String name) throws ServiceException {
        if (validator.isName(name)){
            user.setName(name);
        }
        user.setPassword(pass);
        try {
            return userDao.updateUserById(user);
        } catch (DaoException e) {
            throw new ServiceException("ServiceException in method updateUserById. " + e);
        }
    }

    @Override
    public boolean changeUserStatusByUserId(String strId, String status) throws ServiceException {
        if (validator.isInt(strId) && !status.isEmpty()) {
            long userId = Long.parseLong(strId);
            UserStatus currentStatus = UserStatus.valueOf(status);
            try {
                return currentStatus.equals(UserStatus.ACTIVE) ? userDao.changeUserStatusOnInRegister(userId)
                        : userDao.changeUserStatusOnActive(userId);
            } catch (DaoException e) {
                throw new ServiceException("ServiceException in method changeUserStatusByUserId. " + e);
            }
        }
        logger.error("Update page, please!");
        throw new ServiceException("Update page, please!");
    }

    @Override
    public boolean deleteUserByUserId(String strId) throws ServiceException {
        try {
            if (validator.isInt(strId)) {
                long userId = Long.parseLong(strId);
                return userDao.deleteUserById(userId);
            }
        } catch (DaoException e) {
            throw new ServiceException("ServiceException in method deleteUserByUserId. " + e);
        }
        logger.error("Update page, please!");
        throw new ServiceException("Update page, please!");
    }

    @Override
    public List<User> findAllDeleteUser() throws ServiceException {
        try {
            return userDao.findAllDeleteUser();
        } catch (DaoException e) {
            throw new ServiceException("ServiceException in method findAllDeleteUser. " + e);
        }
    }

    @Override
    public List<User> findAllNonDeleteUser() throws ServiceException {
        try {
            return userDao.findAllNonDeleteUser();
        } catch (DaoException e) {
            throw new ServiceException("ServiceException in method findAllNonDeleteUser. " + e);
        }
    }

    @Override
    public List<User> searchDeleteUserByName(String name) throws ServiceException {
        try {
            if (validator.isName(name)) {
                return userDao.searchDeleteUserByName(name);
            }
        } catch (DaoException e) {
            throw new ServiceException("ServiceException in method searchDeleteUserByName. " + e);
        }
        logger.error("Incorrect input string! ");
        throw new ServiceException("Incorrect input string! ");
    }

    @Override
    public List<User> searchNonDeleteUserByName(String name) throws ServiceException {
        try {
            if (validator.isName(name)) {
                return userDao.searchNonDeleteUserByName(name);
            }
        } catch (DaoException e) {
            throw new ServiceException("ServiceException in method searchDeleteUserByName. " + e);
        }
        logger.error("Incorrect input string!");
        throw new ServiceException("Incorrect input string!");
    }

    @Override
    public double findUserCashById(long userId) throws ServiceException {
        try {
            return userDao.findUserCashById(userId);
        } catch (DaoException e) {
            throw new ServiceException("ServiceException in method findUserCashById. " + e);
        }
    }

    // TODO: 13.10.2021 del
    /*@Override
    public List<User> findAllInRegisterUser() throws ServiceException, DaoException {
        List<User> userList = userDao.findAllInRegisterUser();
        if (userList.isEmpty()){
            logger.error("List is empty.");
            throw new ServiceException("List is empty.");
        }
        return userList;
    }*/

    @Override
    public boolean updateCashById(CreditCard creditCard, long id) throws ServiceException {
        try {
            if (validator.isCreditCode(creditCard.getNumber()) &&
                    validator.isOnlyUpperCaseLetter(creditCard.getName())) {
                return userDao.updateCashById(creditCard.getCash(), id);
            }
        } catch (DaoException e) {
            throw new ServiceException("ServiceException in method updateCashById. " + e);
        }
        logger.error("Check you input data.");
        throw new ServiceException("Check you input data.");
    }

    @Override
    public Optional<User> signIn(String email, String password) throws ServiceException {
        try {
            if (validator.isEmail(email) && validator.isPassword(password)){
                String hashPassword = CryptorPassword.getInstance().encryptor(password);
                return userDao.checkAuthorisation(email, hashPassword);
            }
        } catch (DaoException e) {
            throw new ServiceException("ServiceException in method signIn. " + e);
        }
        logger.warn("Enter is not successful, email or password entered incorrect.");
        throw new ServiceException("Enter is not successful, email or password entered incorrect.");
    }
}
