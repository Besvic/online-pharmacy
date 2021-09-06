package com.example.online_pharmacy.model.dao;

import com.example.online_pharmacy.entity.Basket;
import com.example.online_pharmacy.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface BasketDao {
    Optional<Basket> findByBasketId(int basketId) throws DaoException;
    List<Basket> findByBasketUserId(int userId) throws DaoException;
    boolean addBasket(Basket basket) throws DaoException;
}