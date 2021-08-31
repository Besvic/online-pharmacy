package com.example.online_pharmacy.model.dao.impl;

import com.example.online_pharmacy.entity.User;
import com.example.online_pharmacy.exception.DaoException;
import com.example.online_pharmacy.model.dao.UserDao;

import java.util.List;

public class UserDaoImpl implements UserDao {
    @Override
    public int createUser(User user) throws DaoException {
        return 0;
    }

    @Override
    public int deleteUserById(int id) throws DaoException {
        return 0;
    }

    @Override
    public List<User> findUserByPosition(String position) throws DaoException {
        return null;
    }

    @Override
    public boolean checkAuthorisation(String login, String password) throws DaoException {
        return false;
    }
}
