package com.pharmacy.traning.service.impl;

import com.pharmacy.traning.exception.DaoException;
import com.pharmacy.traning.exception.ServiceException;
import com.pharmacy.traning.model.dao.impl.UserDaoImpl;
import com.pharmacy.traning.model.entity.CreditCard;
import com.pharmacy.traning.model.entity.User;
import com.pharmacy.traning.model.entity.UserStatus;
import com.pharmacy.traning.model.util.CryptorPassword;
import com.pharmacy.traning.service.ServiceUser;
import com.pharmacy.traning.validator.Validator;
import com.pharmacy.traning.validator.ValidatorUser;
import com.pharmacy.traning.validator.impl.ValidatorImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

import static com.pharmacy.traning.controller.comand.SessionAttribute.ADMIN;

public class ServiceUserImpl implements ServiceUser {

    private static final Logger logger = LogManager.getLogger();
    private static ServiceUserImpl instance;
    private static final UserDaoImpl userDao = UserDaoImpl.getInstance();
    private static final Validator validator = ValidatorImpl.getInstance();

    public static ServiceUserImpl getInstance(){
        if (instance == null)
            instance = new ServiceUserImpl();
        return instance;
    }

    private ServiceUserImpl(){

    }

    @Override
    public boolean registration(User user) throws ServiceException {
        if (ValidatorImpl.getInstance().isNullObject(user)) {
            try {
                if (user.getPosition().equals(ADMIN) && userDao.checkIsAdmin()){
                    logger.error("First administrator completed registration!");
                    return false;
                }
                user.setPassword(CryptorPassword.getInstance().encryptor(user.getPassword()));
                return userDao.createUser(user);
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
            return userDao.updatePhotoById(path, id);
        } catch (DaoException e) {
            logger.error("Not available updatePhotoById!", e);
            throw new ServiceException("Not available updatePhotoById!", e);
        }
    }

    @Override
    public boolean updateUserById(User user, String pass, String name) throws ServiceException, DaoException {
        if (validator.isName(name)){
            user.setName(name);
        }
        user.setPassword(pass);
        return userDao.updateUserById(user);
    }

    @Override
    public boolean changeUserStatusByUserId(String strId, String status) throws ServiceException, DaoException {
        if (validator.isInt(strId) && !status.isEmpty()) {
            long userId = Long.parseLong(strId);
            UserStatus currentStatus = UserStatus.valueOf(status);
            return currentStatus.equals(UserStatus.ACTIVE) ? userDao.changeUserStatusOnInRegister(userId) : userDao.changeUserStatusOnActive(userId);
        }
        logger.error("Update page, please!");
        throw new ServiceException("Update page, please!");
    }

    @Override
    public boolean deleteUserByUserId(String strId) throws ServiceException, DaoException {
        if (validator.isInt(strId)) {
            long userId = Long.parseLong(strId);
            return userDao.deleteUserById(userId);
        }
        logger.error("Update page, please!");
        throw new ServiceException("Update page, please!");
    }

    @Override
    public List<User> findAllDeleteUser() throws DaoException, ServiceException {
        return userDao.findAllDeleteUser();
    }

    @Override
    public List<User> findAllNonDeleteUser() throws ServiceException, DaoException {
        return userDao.findAllNonDeleteUser();
    }

    @Override
    public List<User> searchDeleteUserByName(String name) throws ServiceException, DaoException {
        if (validator.isName(name)) {
            return userDao.searchDeleteUserByName(name);
        }
        logger.error("Incorrect input string!");
        throw new ServiceException("Incorrect input string!");
    }

    @Override
    public List<User> searchNonDeleteUserByName(String name) throws ServiceException, DaoException {
        if (validator.isName(name)) {
            return userDao.searchNonDeleteUserByName(name);
        }
        logger.error("Incorrect input string!");
        throw new ServiceException("Incorrect input string!");
    }

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
    public boolean updateCashById(CreditCard creditCard, long id) throws ServiceException, DaoException {
        if (validator.isCreditCode(creditCard.getNumber()) &&
                validator.isOnlyUpperCaseLetter(creditCard.getName())){
            return userDao.updateCashById(creditCard.getCash(), id);
        }
        logger.error("Check you input data.");
        throw new ServiceException("Check you input data.");
    }

    @Override
    public Optional<User> signIn(String email, String password) throws ServiceException {
        if (!email.isEmpty() && !password.isEmpty()){
            try {
                String hashPassword = CryptorPassword.getInstance().encryptor(password);
                return userDao.checkAuthorisation(email, hashPassword);
            } catch (DaoException e) {
                logger.warn("Enter is not successful, email or password entered incorrect.", e);
                throw new ServiceException("Enter is not successful, email or password entered incorrect.", e);
            }
        }
        return Optional.empty();
    }
}
