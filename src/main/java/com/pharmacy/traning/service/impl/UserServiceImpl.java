package com.pharmacy.traning.service.impl;

import com.pharmacy.traning.exception.DaoException;
import com.pharmacy.traning.exception.ServiceException;
import com.pharmacy.traning.model.dao.impl.UserDaoImpl;
import com.pharmacy.traning.model.entity.User;
import com.pharmacy.traning.model.util.CryptorPassword;
import com.pharmacy.traning.service.UserService;
import com.pharmacy.traning.validator.impl.UserValidatorImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class UserServiceImpl implements UserService {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public boolean registration(User user) throws ServiceException {
        UserDaoImpl userDao = new UserDaoImpl();
        if (!new UserValidatorImpl().isNull(user)) {
            try {
                user.setPassword(CryptorPassword.getInstance().encryptor(user.getPassword()));
                return userDao.createUser(user);
            } catch (DaoException e) {
                logger.error("Not available create person, because this email already saved!", e);
                throw new ServiceException("Not available create person, because this email already saved!", e);
            }
        }
        else {
            logger.error("Not available create person, because this data is null!");
            throw new ServiceException("Not available create person, because this data is null!");
        }
    }

    @Override
    public Optional<User> signIn(String email, String password) throws ServiceException {
        return Optional.empty();
    }
}
