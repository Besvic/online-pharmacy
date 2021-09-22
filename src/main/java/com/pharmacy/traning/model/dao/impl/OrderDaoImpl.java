package com.pharmacy.traning.model.dao.impl;

import com.pharmacy.traning.model.entity.Order;
import com.pharmacy.traning.model.entity.OrderStatus;
import com.pharmacy.traning.exception.DaoException;
import com.pharmacy.traning.model.dao.OrderDao;
import com.pharmacy.traning.model.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.pharmacy.traning.model.dao.ColumnName.*;

public class OrderDaoImpl implements OrderDao {

    private static final Logger logger = LogManager.getLogger();
    private static final OrderDaoImpl instance = new OrderDaoImpl();

    public static OrderDaoImpl getInstance(){
        return instance;
    }

    private OrderDaoImpl(){

    }

    private static final String SQL_ADD_ORDER = """
            insert into `order` (order_product_id, order_user_id, order_status, order_quantity, order_date)
            values (?, ?, ?, ?, ?);""";
    private static final String SQL_FIND_ORDER_BY_ID = """
            select order_id, order_product_id, order_user_id, order_status, order_quantity, order_date
            from `order`
            where order_id = ?;""";
    private static final String SQL_FIND_ORDER_BY_USER_ID = """
            select order_id, order_product_id, order_user_id, order_status, order_quantity, order_date
            from `order`
            where order_user_id = ?;""";
    private static final String SQL_FIND_ORDER_BY_PRODUCT_ID = """
            select order_id, order_product_id, order_user_id, order_status, order_quantity, order_date
            from `order`
            where order_product_id = ?;""";
    private static final String SQL_FIND_ORDER_BY_STATUS = """
            select order_id, order_product_id, order_user_id, order_status, order_quantity, order_date
            from `order`
            where order_status = ?;""";

    @Override
    public boolean addOrder(Order order) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_ADD_ORDER)){
            statement.setLong(1, order.getProductId());
            statement.setLong(2, order.getUserId());
            statement.setString(3, String.valueOf(order.getStatus()));
            statement.setInt(4, order.getQuantity());
            statement.setString(5, String.valueOf(order.getDate()));
            if (statement.executeUpdate() != 0)
                return true;
        } catch (SQLException throwables) {
            logger.error("PrepareStatement didn't connection or this function is not available." + throwables);
            throw new DaoException("PrepareStatement didn't connection or this function is not available.", throwables);
        }
        return false;
    }

    @Override
    public Optional<Order> findOrderById(int orderId) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_ORDER_BY_ID)){
            statement.setLong(1, orderId);
            try (ResultSet result = statement.executeQuery()){
                if (result.next()){
                    return Optional.of(new Order.OrderBuilder()
                    .setId(result.getLong(ORDER_ID))
                    .setUserId(result.getLong(ORDER_USER_ID))
                    .setProductId(result.getLong(ORDER_PRODUCT_ID))
                    .setQuantity(result.getInt(ORDER_QUANTITY))
                    .setStatus(result.getString(ORDER_STATUS))
                    .setDate(LocalDate.parse(result.getString(ORDER_DATE)))
                    .createOrder());
                }
            }
        } catch (SQLException throwables) {
            logger.error("PrepareStatement didn't connection or this function is not available." + throwables);
            throw new DaoException("PrepareStatement didn't connection or this function is not available.", throwables);
        }
        return Optional.empty();
    }

    @Override
    public List<Order> findOrderByUserId(int userId) throws DaoException {
        List<Order> orderList = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_ORDER_BY_USER_ID)){
            statement.setLong(1, userId);
            try (ResultSet result = statement.executeQuery()){
                while (result.next()){
                    orderList.add(new Order.OrderBuilder()
                            .setId(result.getLong(ORDER_ID))
                            .setUserId(result.getLong(ORDER_USER_ID))
                            .setProductId(result.getLong(ORDER_PRODUCT_ID))
                            .setQuantity(result.getInt(ORDER_QUANTITY))
                            .setStatus(result.getString(ORDER_STATUS))
                            .setDate(LocalDate.parse(result.getString(ORDER_DATE)))
                            .createOrder());
                }
            }
        } catch (SQLException throwables) {
            logger.error("PrepareStatement didn't connection or this function is not available." + throwables);
            throw new DaoException("PrepareStatement didn't connection or this function is not available.", throwables);
        }
        return orderList;
    }

    @Override
    public List<Order> findOrderByProductId(int productId) throws DaoException {
        List<Order> orderList = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_ORDER_BY_PRODUCT_ID)){
            statement.setLong(1, productId);
            try (ResultSet result = statement.executeQuery()){
                while (result.next()){
                    orderList.add(new Order.OrderBuilder()
                            .setId(result.getLong(ORDER_ID))
                            .setUserId(result.getLong(ORDER_USER_ID))
                            .setProductId(result.getLong(ORDER_PRODUCT_ID))
                            .setQuantity(result.getInt(ORDER_QUANTITY))
                            .setStatus(result.getString(ORDER_STATUS))
                            .setDate(LocalDate.parse(result.getString(ORDER_DATE)))
                            .createOrder());
                }
            }
        } catch (SQLException throwables) {
            logger.error("PrepareStatement didn't connection or this function is not available." + throwables);
            throw new DaoException("PrepareStatement didn't connection or this function is not available.", throwables);
        }
        return orderList;
    }

    @Override
    public List<Order> findOrderByStatus(String orderStatus) throws DaoException {
        List<Order> orderList = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_ORDER_BY_STATUS)){
            statement.setString(1, orderStatus);
            try (ResultSet result = statement.executeQuery()){
                while (result.next()){
                    orderList.add(new Order.OrderBuilder()
                            .setId(result.getLong(ORDER_ID))
                            .setUserId(result.getLong(ORDER_USER_ID))
                            .setProductId(result.getLong(ORDER_PRODUCT_ID))
                            .setQuantity(result.getInt(ORDER_QUANTITY))
                            .setStatus(result.getString(ORDER_STATUS))
                            .setDate(LocalDate.parse(result.getString(ORDER_DATE)))
                            .createOrder());
                }
            }
        } catch (SQLException throwables) {
            logger.error("PrepareStatement didn't connection or this function is not available." + throwables);
            throw new DaoException("PrepareStatement didn't connection or this function is not available.", throwables);
        }
        return orderList;
    }
}
