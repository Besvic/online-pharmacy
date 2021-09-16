package com.pharmacy.traning.model.dao;

import com.pharmacy.traning.model.entity.Basket;
import com.pharmacy.traning.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface BasketDao {
    Optional<Basket> findByBasketId(int basketId) throws DaoException;
    List<Basket> findByBasketUserId(int userId) throws DaoException;
    boolean addBasket(Basket basket) throws DaoException;
}
