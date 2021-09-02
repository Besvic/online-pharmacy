package com.example.online_pharmacy.model.dao;

import com.example.online_pharmacy.entity.User;
import com.example.online_pharmacy.exception.DaoException;

import java.util.List;

public interface UserDao {

    boolean createUser(User user) throws DaoException;
    boolean deleteUserById(int id) throws DaoException;
    List<User> findUserByPosition(String position) throws DaoException;
    boolean checkAuthorisation(String login, String password) throws DaoException;




}
