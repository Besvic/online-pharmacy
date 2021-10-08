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
            select order_id, product_quantity
            from `order`
            join product on order_product_id = product_id
            where order_user_id = ? and order_product_id = ? and order_status = 'not completed';""";

    private static final String SQL_COMPLETED_ORDER = """
         
            update `order`
            set order_status = 'completed', order_pharmacy_id = ?
            where order_id = ?;""";



    private static final String SQL_FIND_ORDER_BY_ID = """
           select order_id, user_id, user_cash,  order_quantity, product_price * order_quantity as product_price, product_id, product_quantity
           from `order`
           
           join product on product_id = `order`.order_product_id
           join users on user_id = `order`.order_user_id
           where order_id = ?;""";



    private static final String SQL_FIND_ALL_ORDER_BY_USER_ID = """
           select order_id, order_quantity, order_quantity * pr.product_price as product_price, product_id, product_name
           from `order`
           join product pr on pr.product_id = `order`.order_product_id
           where order_user_id = ? and order_status = 'not completed' and product_status = 'actual';""";

    private static final String SQL_FIND_ALL_COMPLETED_ORDER_BY_USER_ID = """
            select order_id, order_date, user_cash, user_name, user_login, order_quantity, product_name, product_price * order_quantity as product_price, pharmacy_city, pharmacy_street, pharmacy_number
            from `order`
            join pharmacy on pharmacy_id = `order`.order_pharmacy_id
            join product on product_id = `order`.order_product_id
            join users on user_id = `order`.order_user_id
            where user_id = ? and order_date = ? and order_status = 'completed'
            order by order_date desc, product_price desc;""";
    
    private static final String SQL_FIND_ALL_COMPLETED_ORDER = """
            select order_date, user_cash, user_name, sum(order_quantity) as order_quantity, sum(product_price * order_quantity) as product_price, user_id/*, order_id, user_login, product_name,  pharmacy_city, pharmacy_street, pharmacy_number*/
            from `order`
            join pharmacy on pharmacy_id = `order`.order_pharmacy_id
            join product on product_id = `order`.order_product_id
            join users on user_id = `order`.order_user_id
            where order_status = 'completed'
            group by order_date, user_id
            order by order_date desc, product_price desc;""";

    private static final String SQL_FIND_ORDER_BY_STATUS = """
            select order_id, order_product_id, order_user_id, order_status, order_quantity, order_date
            from `order`
            where order_status = ?;""";

    private static final String SQL_DELETE_ORDER_BY_ID = """
            delete from `order`
            where order_id = ?;""";



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
    public boolean deleteOrderById(long orderId) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_DELETE_ORDER_BY_ID)){
            statement.setLong(1, orderId);
            if (statement.executeUpdate() != 0)
                return true;
        } catch (SQLException throwables) {
            logger.error("PrepareStatement didn't connection or this function is not available." + throwables);
            throw new DaoException("PrepareStatement didn't connection or this function is not available.", throwables);
        }
        return false;
    }

    @Override
    public boolean completedOrder(long orderId, long pharmacyId, Connection connection) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_COMPLETED_ORDER)){
            statement.setLong(1, pharmacyId);
            statement.setLong(2, orderId);
            if (statement.executeUpdate() != 0)
                return true;
        } catch (SQLException throwables) {
            logger.error("PrepareStatement didn't connection or this function is not available." + throwables);
            throw new DaoException("PrepareStatement didn't connection or this function is not available.", throwables);
        }return false;
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
                            .setProduct(new Product.ProductBuilder()
                                    .setQuantity(result.getInt(PRODUCT_QUANTITY))
                                    .createProduct())
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
    public List<Order> findAllCompletedOrder() throws DaoException {
        List<Order> orderList = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL_COMPLETED_ORDER);
             ResultSet result = statement.executeQuery() ){
                while (result.next()){
                    orderList.add(new Order.OrderBuilder()
                            .setDate(result.getDate(ORDER_DATE).toLocalDate())
                            .setUser(new User.UserBuilder()
                                    .setCash(result.getDouble(USER_CASH))
                                    .setName(result.getString(USER_NAME))
                                    .setId(result.getLong(USER_ID))
                                    .createUser())
                            .setProduct(new Product.ProductBuilder()
                                    .setPrice(result.getDouble(PRODUCT_PRICE))
                                    .createProduct())
                            .setQuantity(result.getInt(ORDER_QUANTITY))
                            .createOrder());

            }
        } catch (SQLException throwables) {
            logger.error("PrepareStatement didn't connection or this function is not available." + throwables);
            throw new DaoException("PrepareStatement didn't connection or this function is not available.", throwables);
        }
        return orderList;
    }

    @Override
    public List<Order> findAllCompletedOrderByUserId(long userId, LocalDate date) throws DaoException {
        List<Order> orderList = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL_COMPLETED_ORDER_BY_USER_ID)){
            statement.setLong(1, userId);
            statement.setDate(2, Date.valueOf(date));
             try(ResultSet result = statement.executeQuery() ){
            while (result.next()) {
                orderList.add(new Order.OrderBuilder()
                        .setId(result.getLong(ORDER_ID))
                        .setDate(result.getDate(ORDER_DATE).toLocalDate())
                        .setUser(new User.UserBuilder()
                                .setCash(result.getDouble(USER_CASH))
                                .setName(result.getString(USER_NAME))
                                .setLogin(result.getString(USER_LOGIN))
                                .createUser())
                        .setProduct(new Product.ProductBuilder()
                                .setPrice(result.getDouble(PRODUCT_PRICE))
                                .setName(result.getString(PRODUCT_NAME))
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
    public Optional<Order> findOrderById(long orderId, Connection connection) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_ORDER_BY_ID)){
            statement.setLong(1, orderId);
            try (ResultSet result = statement.executeQuery()){
                if (result.next()){
                    return Optional.ofNullable(new Order.OrderBuilder()
                    .setId(result.getLong(ORDER_ID))
                    .setUser(new User.UserBuilder()
                            .setId(result.getLong(USER_ID))
                            .setCash(result.getDouble(USER_CASH))
                            .createUser())
                    .setQuantity(result.getInt(ORDER_QUANTITY))
                    .setProduct( new Product.ProductBuilder()
                            .setPrice(result.getDouble(PRODUCT_PRICE))
                            .setId(result.getInt(PRODUCT_ID))
                            .setQuantity(result.getInt(PRODUCT_QUANTITY))
                            .createProduct())
//                    .setPharmacy(new Pharmacy.PharmacyBuilder()
//                            .setId(result.getLong(PHARMACY_ID))
//                            .setNumber(result.getInt(PHARMACY_NUMBER))
//                            .setStreet(result.getString(PHARMACY_STREET))
//                            .setCity(result.getString(PHARMACY_CITY))
//                            .createPharmacy())
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
