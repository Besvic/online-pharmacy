package com.example.online_pharmacy.model.dao;

import com.example.online_pharmacy.entity.User;
import com.example.online_pharmacy.exception.DaoException;

import java.util.List;

public interface UserDao {

    int createUser(User user) throws DaoException;
    int deleteUserById(int id) throws DaoException;
    List<User> findUserByPosition(String position) throws DaoException;
    boolean checkAuthorisation(String login, String password) throws DaoException;




}
