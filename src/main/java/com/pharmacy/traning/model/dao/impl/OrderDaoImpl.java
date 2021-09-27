package com.pharmacy.traning.model.dao.impl;

import com.pharmacy.traning.model.entity.Order;
import com.pharmacy.traning.exception.DaoException;
import com.pharmacy.traning.model.dao.OrderDao;
import com.pharmacy.traning.model.entity.Pharmacy;
import com.pharmacy.traning.model.entity.Product;
import com.pharmacy.traning.model.entity.User;
import com.pharmacy.traning.model.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.pharmacy.traning.model.dao.ColumnName.*;

public class OrderDaoImpl implements OrderDao {

    private static final Logger logger = LogManager.getLogger();
    private static OrderDao instance;

    public static OrderDao getInstance(){
        if (instance == null){
            instance = new OrderDaoImpl();
        }
        return instance;
    }

    private OrderDaoImpl(){

    }

    private static final String SQL_ADD_ORDER = """
            insert into `order` (order_user_id, order_date, order_product_id, order_quantity)
            values (?, ?, ?, ?);""";


    private static final String SQL_CHANGE_QUANTITY_PRODUCT_IN_ORDER = """
            update `order`
            set order_quantity = ?
            where order_id = ?;""";

    private static final String SQL_IS_ORDER = """
            select order_id
            from `order`
            where order_user_id = ? and order_product_id = ? and order_status = 'not completed';""";



    private static final String SQL_FIND_ORDER_BY_ID = """
           select order_id, user_id, user_cash,  order_quantity, product_price * order_quantity as product_price, product_id, pharmacy_id, pharmacy_city, pharmacy_street, pharmacy_number
           from `order`
           join pharmacy on pharmacy_id = `order`.order_pharmacy_id
           join product on product_id = `order`.order_product_id
           join users on user_id = `order`.order_user_id
           where order_id = ?;""";

    private static final String SQL_FIND_ALL_ORDER_BY_USER_ID = """
           select order_id, order_quantity, order_quantity * pr.product_price as product_price, product_id, product_name, pharmacy_city, pharmacy_street, pharmacy_number
           from `order`
           join pharmacy p on p.pharmacy_id = `order`.order_pharmacy_id
           join product pr on pr.product_id = `order`.order_product_id
           where order_user_id = ? and order_status = 'not completed';""";
    
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
            statement.setLong(1, order.getUser().getId());
            statement.setDate(2, Date.valueOf(LocalDate.now()));
            statement.setLong(3, order.getProduct().getId());
            statement.setInt(4, order.getQuantity());
            if (statement.executeUpdate() != 0)
                return true;
        } catch (SQLException throwables) {
            logger.error("PrepareStatement didn't connection or this function is not available." + throwables);
            throw new DaoException("PrepareStatement didn't connection or this function is not available.", throwables);
        }
        return false;
    }


    @Override
    public boolean addProductQuantityInOrder(long orderId, int quantity) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_CHANGE_QUANTITY_PRODUCT_IN_ORDER)){
            statement.setInt(1, quantity);
            statement.setLong(2, orderId);
            if (statement.executeUpdate() != 0)
                return true;
        } catch (SQLException throwables) {
            logger.error("PrepareStatement didn't connection or this function is not available." + throwables);
            throw new DaoException("PrepareStatement didn't connection or this function is not available.", throwables);
        }
        return false;
    }


    @Override
    public Optional<Order> checkIsOrder(long userId, long productId) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_IS_ORDER)){
            statement.setLong(1, userId);
            statement.setLong(2, productId);
            try (ResultSet result = statement.executeQuery()){
                if (result.next()){
                    return Optional.ofNullable(new Order.OrderBuilder()
                            .setId(result.getLong(ORDER_ID))
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
    public List<Order> findAllNotCompletedOrderById(long userId) throws DaoException {
        List<Order> orderList = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL_ORDER_BY_USER_ID)){
            statement.setLong(1, userId);
            try (ResultSet result = statement.executeQuery()){
                while (result.next()){
                    orderList.add(new Order.OrderBuilder()
                        .setId(result.getLong(ORDER_ID))
                        .setProduct(new Product.ProductBuilder()
                            .setPrice(result.getDouble(PRODUCT_PRICE))
                            .setName(result.getString(PRODUCT_NAME))
                            .setId(result.getLong(PRODUCT_ID))
                            .createProduct())
                        .setPharmacy(new Pharmacy.PharmacyBuilder()
                            .setCity(result.getString(PHARMACY_CITY))
                            .setStreet(result.getString(PHARMACY_STREET))
                            .setNumber(result.getInt(PHARMACY_NUMBER))
                            .createPharmacy())
                        .setQuantity(result.getInt(ORDER_QUANTITY))
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
    public Optional<Order> findOrderById(int orderId) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_ORDER_BY_ID)){
            statement.setLong(1, orderId);
            try (ResultSet result = statement.executeQuery()){
                if (result.next()){
                    return Optional.of(new Order.OrderBuilder()
                    .setId(result.getLong(ORDER_ID))
                    .setUser(new User.UserBuilder()
                            .setId(result.getLong(USER_ID))
                            .setCash(result.getDouble(USER_CASH))
                            .createUser())
                    .setQuantity(result.getInt(ORDER_QUANTITY))
                    .setProduct( new Product.ProductBuilder()
                            .setPrice(result.getDouble(PRODUCT_PRICE))
                            .setId(result.getInt(PRODUCT_ID))
                            .createProduct())
                    .setPharmacy(new Pharmacy.PharmacyBuilder()
                            .setId(result.getLong(PHARMACY_ID))
                            .setNumber(result.getInt(PHARMACY_NUMBER))
                            .setStreet(result.getString(PHARMACY_STREET))
                            .setCity(result.getString(PHARMACY_CITY))
                            .createPharmacy())
                    .createOrder());
                }
            }
        } catch (SQLException throwables) {
            logger.error("PrepareStatement didn't connection or this function is not available." + throwables);
            throw new DaoException("PrepareStatement didn't connection or this function is not available.", throwables);
        }
        return Optional.empty();
    }

    /*
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
    }*/
}
