package com.example.online_pharmacy.model.dao.impl;

import com.example.online_pharmacy.entity.Basket;
import com.example.online_pharmacy.exception.DaoException;
import com.example.online_pharmacy.model.dao.BasketDao;
import com.example.online_pharmacy.model.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.online_pharmacy.model.dao.ColumnName.*;

public class BasketDaoImpl implements BasketDao {

    private static final Logger logger = LogManager.getLogger();

    private static final String SQL_FIND_BY_BASKET_ID = """
            select basket_id, basket_order_id, basket_user_id
            from basket
            where basket_id = ?;""";
    private static final String SQL_FIND_BY_BASKET_USER_ID = """
            select basket_id, basket_order_id, basket_user_id
            from basket
            where basket_user_id = ?;""";
    private static final String SQL_ADD_BASKET = """
            insert into basket (basket_order_id, basket_user_id)
            values (?, ?);""";

    @Override
    public Optional<Basket> findByBasketId(int basketId) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_BASKET_ID)){
            statement.setInt(1, basketId);
            try (ResultSet result = statement.executeQuery()){
                if (result.next()){
                    return Optional.of(new Basket.BasketBuilder()
                            .setId(result.getInt(BASKET_ID))
                            .setOrderId(result.getInt(BASKET_ORDER_ID))
                            .setUserId(result.getInt(BASKET_USER_ID))
                            .createBasket());
                }
            }
        } catch (SQLException throwables) {
            logger.error("PrepareStatement didn't connection or this function is not available." + throwables);
            new DaoException("PrepareStatement didn't connection or this function is not available.", throwables);
        }
        return Optional.empty();
    }

    @Override
    public List<Basket> findByBasketUserId(int userId) throws DaoException {
        List<Basket> basketList = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_BASKET_USER_ID)){
            statement.setInt(1, userId);
            try (ResultSet result = statement.executeQuery()){
                while (result.next()){
                    basketList.add(new Basket.BasketBuilder()
                            .setId(result.getInt(BASKET_ID))
                            .setOrderId(result.getInt(BASKET_ORDER_ID))
                            .setUserId(result.getInt(BASKET_USER_ID))
                            .createBasket());
                }
            }
        } catch (SQLException throwables) {
            logger.error("PrepareStatement didn't connection or this function is not available." + throwables);
            new DaoException("PrepareStatement didn't connection or this function is not available.", throwables);
        }
        return basketList;
    }

    @Override
    public boolean addBasket(Basket basket) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_ADD_BASKET)){
            statement.setLong(1, basket.getOrderId());
            statement.setLong(2, basket.getUserId());
            if (statement.executeUpdate() != 0)
                return true;
        } catch (SQLException throwables) {
            logger.error("PrepareStatement didn't connection or this function is not available." + throwables);
            new DaoException("PrepareStatement didn't connection or this function is not available.", throwables);
        }
        return false;
    }
}
