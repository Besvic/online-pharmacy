package com.example.online_pharmacy.model.dao;

import com.example.online_pharmacy.entity.User;
import com.example.online_pharmacy.exception.DaoException;

import java.util.List;

public interface UserDao {

    int createUser(User user) throws DaoException;
    int deleteUserById(int id) throws DaoException;
    List<User> findUserByPosition(String positionValue) throws DaoException;
    boolean checkPassword(String password) throws DaoException;



}
