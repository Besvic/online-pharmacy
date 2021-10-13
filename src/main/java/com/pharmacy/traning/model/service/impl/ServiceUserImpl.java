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
 * The type Service user.
 */
public class ServiceUserImpl implements ServiceUser {

    /**
     * The Logger.
     */
    private static final Logger logger = LogManager.getLogger();
    /**
     * The Instance.
     */
    private static ServiceUserImpl instance;
    /**
     * The User dao.
     */
    private static final UserDao userDao = UserDaoImpl.getInstance();
    /**
     * The Validator.
     */
    private static final Validator validator = ValidatorImpl.getInstance();
    /**
     * The Crypt.
     */
    private static final CryptorPassword crypt = CryptorPassword.getInstance();

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

    /**
     * Registration boolean.
     *
     * @param user the user
     * @return the boolean
     * @throws ServiceException the service exception
     * @throws DaoException     the dao exception
     */
    @Override
    public boolean registration(User user) throws ServiceException, DaoException {
        if ( validator.isName(user.getName()) &&
                validator.isEmail(user.getLogin()) && validator.isPassword(user.getPassword())) {
                if (user.getPosition().getValue().equals(ADMIN) && !userDao.checkIsAdmin()){
                    user.setPassword(crypt.encryptor(user.getPassword()));
                    return userDao.createUser(user);
                }
                logger.error("First administrator completed registration!");
                throw new ServiceException("First administrator completed registration!");
        }
        else {
            logger.error("Not available create person, because this data is null!");
            throw new ServiceException("Not available create person, because this data is null!");
        }
    }

    /**
     * Update photo by id boolean.
     *
     * @param path the path
     * @param id   the id
     * @return the boolean
     * @throws ServiceException the service exception
     */
    @Override
    public boolean updatePhotoById(String path, long id) throws ServiceException {
        try {
            return userDao.updatePhotoById(path, id);
        } catch (DaoException e) {
            logger.error("Not available updatePhotoById!", e);
            throw new ServiceException("Not available updatePhotoById!", e);
        }
    }

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
    @Override
    public boolean updateUserById(User user, String pass, String name) throws ServiceException, DaoException {
        if (validator.isName(name)){
            user.setName(name);
        }
        user.setPassword(pass);
        return userDao.updateUserById(user);
    }

    /**
     * Change user status by user id boolean.
     *
     * @param strId  the str id
     * @param status the status
     * @return the boolean
     * @throws ServiceException the service exception
     * @throws DaoException     the dao exception
     */
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

    /**
     * Delete user by user id boolean.
     *
     * @param strId the str id
     * @return the boolean
     * @throws ServiceException the service exception
     * @throws DaoException     the dao exception
     */
    @Override
    public boolean deleteUserByUserId(String strId) throws ServiceException, DaoException {
        if (validator.isInt(strId)) {
            long userId = Long.parseLong(strId);
            return userDao.deleteUserById(userId);
        }
        logger.error("Update page, please!");
        throw new ServiceException("Update page, please!");
    }

    /**
     * Find all delete user list.
     *
     * @return the list
     * @throws DaoException     the dao exception
     * @throws ServiceException the service exception
     */
    @Override
    public List<User> findAllDeleteUser() throws DaoException, ServiceException {
        return userDao.findAllDeleteUser();
    }

    /**
     * Find all non delete user list.
     *
     * @return the list
     * @throws ServiceException the service exception
     * @throws DaoException     the dao exception
     */
    @Override
    public List<User> findAllNonDeleteUser() throws ServiceException, DaoException {
        return userDao.findAllNonDeleteUser();
    }

    /**
     * Search delete user by name list.
     *
     * @param name the name
     * @return the list
     * @throws ServiceException the service exception
     * @throws DaoException     the dao exception
     */
    @Override
    public List<User> searchDeleteUserByName(String name) throws ServiceException, DaoException {
        if (validator.isName(name)) {
            return userDao.searchDeleteUserByName(name);
        }
        logger.error("Incorrect input string!");
        throw new ServiceException("Incorrect input string!");
    }

    /**
     * Search non delete user by name list.
     *
     * @param name the name
     * @return the list
     * @throws ServiceException the service exception
     * @throws DaoException     the dao exception
     */
    @Override
    public List<User> searchNonDeleteUserByName(String name) throws ServiceException, DaoException {
        if (validator.isName(name)) {
            return userDao.searchNonDeleteUserByName(name);
        }
        logger.error("Incorrect input string!");
        throw new ServiceException("Incorrect input string!");
    }

    /**
     * Find user cash by id double.
     *
     * @param userId the user id
     * @return the double
     * @throws ServiceException the service exception
     * @throws DaoException     the dao exception
     */
    @Override
    public double findUserCashById(long userId) throws ServiceException, DaoException {
        return userDao.findUserCashById(userId);
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

    /**
     * Update cash by id boolean.
     *
     * @param creditCard the credit card
     * @param id         the id
     * @return the boolean
     * @throws ServiceException the service exception
     * @throws DaoException     the dao exception
     */
    @Override
    public boolean updateCashById(CreditCard creditCard, long id) throws ServiceException, DaoException {
        if (validator.isCreditCode(creditCard.getNumber()) &&
                validator.isOnlyUpperCaseLetter(creditCard.getName())){
            return userDao.updateCashById(creditCard.getCash(), id);
        }
        logger.error("Check you input data.");
        throw new ServiceException("Check you input data.");
    }

    /**
     * Sign in optional.
     *
     * @param email    the email
     * @param password the password
     * @return the optional
     * @throws ServiceException the service exception
     * @throws DaoException     the dao exception
     */
    @Override
    public Optional<User> signIn(String email, String password) throws ServiceException, DaoException {
        if (validator.isEmail(email) && validator.isPassword(password)){
            String hashPassword = CryptorPassword.getInstance().encryptor(password);
            return userDao.checkAuthorisation(email, hashPassword);
        }
        logger.warn("Enter is not successful, email or password entered incorrect.");
        throw new ServiceException("Enter is not successful, email or password entered incorrect.");
    }
}
