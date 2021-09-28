package com.pharmacy.traning.model.dao.impl;

import com.pharmacy.traning.model.entity.Product;
import com.pharmacy.traning.exception.DaoException;
import com.pharmacy.traning.model.dao.ProductDao;
import com.pharmacy.traning.model.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.pharmacy.traning.model.dao.ColumnName.*;

public class ProductDaoImpl implements ProductDao {

    private static final Logger logger = LogManager.getLogger();
    private static final ProductDaoImpl instance = new ProductDaoImpl();

    public static ProductDaoImpl getInstance(){
        return instance;
    }

    private ProductDaoImpl(){

    }

    private static final String SQL_ADD_PRODUCT = """
            insert into product (product_name, product_dosage, product_manufacture, product_quantity,
            product_price, product_date_of_delivery, product_measure)
            values (?, ?, ?, ?, ?, ?, ?);""";
    private static final String SQL_FIND_ALL_PRODUCT = """
            select product_id, product_name, product_dosage, product_manufacture, product_quantity,
            product_price, product_date_of_delivery, product_measure
            from product
            where product_status = 'actual';""";
    private static final String SQL_FIND_ALL_DELETE_PRODUCT = """
            select product_id, product_name, product_dosage, product_manufacture, product_quantity,
            product_price, product_date_of_delivery, product_measure
            from product
            where product_status = 'delete';""";
    private static final String SQL_DELETE_PRODUCT_BY_PRODUCT_ID = """
            update product
            set product_status = 'delete'
            where product_id = ?""";
    private static final String SQL_CHANGE_PRODUCT_BY_PRODUCT_ID = """
            update product
            set product_name = ?, product_dosage = ?, product_manufacture = ?,
            product_price = ?, product_date_of_delivery = ?, product_measure = ?
            where product_id = ?;""";
    private static final String SQL_ADD_PRODUCT_QUANTITY_BY_PRODUCT_ID = """
            update product
            set product_quantity = product_quantity + ?
            where product_id = ?;""";

    private static final String SQL_REDUCE_PRODUCT_QUANTITY_BY_PRODUCT_ID = """
            update product
            set product_quantity = product_quantity - ?
            where product_id = ?;""";
    private static final String SQL_FIND_PRODUCT_BY_ID = """
            select product_id, product_name, product_dosage, product_manufacture, product_quantity,
            product_price, product_date_of_delivery, product_measure
            from product
            where product_id = ?;""";
    private static final String SQL_FIND_PRODUCT_UNDER_PRODUCT_PRICE = """
            select product_id, product_name, product_dosage, product_manufacture, product_quantity,
            product_price, product_date_of_delivery, product_measure, product_photo
            from product
            where product_price <= ?;""";

    private static final String SQL_REALLY_DELETE_PRODUCT_BY_ID = """
            delete from product
            where product_id = ?;""";

    private static final String SQL_RESTORE_PRODUCT_BY_ID = """
            update product
            set product_status = 'actual'
            where product_id = ?;""";





    @Override
    public boolean addProduct(Product product) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_ADD_PRODUCT)){
            statement.setString(1, product.getName());
            statement.setDouble(2, product.getDosage());
            statement.setString(3, product.getManufactureCountry());
            statement.setInt(4, product.getQuantity());
            statement.setDouble(5, product.getPrice());
            statement.setDate(6, Date.valueOf(product.getDateOfDelivery()));
            statement.setString(7, product.getMeasure());
            if (statement.executeUpdate() != 0) {
                return true;
            }
        } catch (SQLException throwables) {
            logger.error("PrepareStatement didn't connection or this function is not available." + throwables);
            throw new DaoException("PrepareStatement didn't connection or this function is not available.", throwables);
        }
        return false;
    }

    @Override
    public List<Product> findAllProduct() throws DaoException {
        return findAllProductByScript(SQL_FIND_ALL_PRODUCT);
    }

    @Override
    public List<Product> findAllDeleteProduct() throws DaoException {
        return findAllProductByScript(SQL_FIND_ALL_DELETE_PRODUCT);
    }

    private List<Product> findAllProductByScript(String script) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(script);
             ResultSet result = statement.executeQuery()){
            List<Product> productList = new ArrayList<>();
            while (result.next()){
                productList.add(new Product.ProductBuilder()
                        .setId(result.getLong(PRODUCT_ID))
                        .setName(result.getString(PRODUCT_NAME))
                        .setQuantity(result.getInt(PRODUCT_QUANTITY))
                        .setDosage(result.getDouble(PRODUCT_DOSAGE))
                        .setMeasure(result.getString(PRODUCT_MEASURE))
                        .setPrice(result.getDouble(PRODUCT_PRICE))
                        .setDateOfDelivery(result.getDate(PRODUCT_DATE_OF_DELIVERY).toLocalDate())
                        .setManufactureCountry(result.getString(PRODUCT_MANUFACTURE))
                        .createProduct());
            }
            return productList;
        } catch (SQLException throwables) {
            logger.error("PrepareStatement didn't connection or this function is not available." + throwables);
            throw new DaoException("PrepareStatement didn't connection or this function is not available.", throwables);
        }
    }

    @Override
    public boolean deleteProductById(long id) throws DaoException {
        return executeScriptById(SQL_DELETE_PRODUCT_BY_PRODUCT_ID, id);
    }

    @Override
    public boolean restoreProductById(long id) throws DaoException {
        return executeScriptById(SQL_RESTORE_PRODUCT_BY_ID, id);
    }

    @Override
    public boolean reallyDeleteProductById(long id) throws DaoException {
        return executeScriptById(SQL_REALLY_DELETE_PRODUCT_BY_ID, id);
    }

    private boolean executeScriptById(String script, long id) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(script)){
            statement.setLong(1, id);
            if (statement.executeUpdate() != 0){
                return true;
            }
        } catch (SQLException throwables) {
            logger.error("PrepareStatement didn't connection or this function is not available." + throwables);
            throw new DaoException("PrepareStatement didn't connection or this function is not available.", throwables);
        }
        return false;
    }


    @Override
    public boolean changeProductByProductId(Product product) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_CHANGE_PRODUCT_BY_PRODUCT_ID)){
            statement.setString(1, product.getName());
            statement.setDouble(2, product.getDosage());
            statement.setString(3, product.getManufactureCountry());
            statement.setDouble(4, product.getPrice());
            statement.setDate(5, Date.valueOf(product.getDateOfDelivery()));
            statement.setString(6, product.getMeasure());
            statement.setLong(7, product.getId());
            if (statement.executeUpdate() != 0) {
                return true;
            }
        } catch (SQLException throwables) {
            logger.error("PrepareStatement didn't connection or this function is not available." + throwables);
            throw new DaoException("PrepareStatement didn't connection or this function is not available.", throwables);
        }
        return false;
    }

    @Override
    public boolean addProductQuantityByProductId(int productQuantity, long productId) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_ADD_PRODUCT_QUANTITY_BY_PRODUCT_ID)){
            statement.setInt(1, productQuantity);
            statement.setLong(2, productId);
            if (statement.executeUpdate() != 0){
                return true;
            }
        } catch (SQLException throwables) {
            logger.error("PrepareStatement didn't connection or this function is not available." + throwables);
            throw new DaoException("PrepareStatement didn't connection or this function is not available.", throwables);
        }
        return false;
    }

    @Override
    public boolean reduceProductQuantityByProductId(int productQuantity, long productId, Connection connection) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_REDUCE_PRODUCT_QUANTITY_BY_PRODUCT_ID)){
            statement.setInt(1, productQuantity);
            statement.setLong(2, productId);
            if (statement.executeUpdate() != 0){
                return true;
            }
        } catch (SQLException throwables) {
            logger.error("PrepareStatement didn't connection or this function is not available." + throwables);
            throw new DaoException("PrepareStatement didn't connection or this function is not available.", throwables);
        }
        return false;    }

    @Override
    public Optional<Product> findProductById(long productId) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_PRODUCT_BY_ID)){
            statement.setLong(1, productId);
            try(ResultSet result = statement.executeQuery()){
                if (result.next()){
                    return Optional.of(new Product.ProductBuilder()
                            .setId(result.getLong(PRODUCT_ID))
                            .setName(result.getString(PRODUCT_NAME))
                            .setQuantity(result.getInt(PRODUCT_QUANTITY))
                            .setDosage(result.getDouble(PRODUCT_DOSAGE))
                            .setMeasure(result.getString(PRODUCT_MEASURE))
                            .setPrice(result.getDouble(PRODUCT_PRICE))
                            .setDateOfDelivery(result.getDate(PRODUCT_DATE_OF_DELIVERY).toLocalDate())
                            .setManufactureCountry(result.getString(PRODUCT_MANUFACTURE))
                            .createProduct());
                }
            }
        } catch (SQLException throwables) {
            logger.error("PrepareStatement didn't connection or this function is not available." + throwables);
            throw new DaoException("PrepareStatement didn't connection or this function is not available.", throwables);
        }
        return Optional.empty();
    }

    @Override
    public List<Product> findProductUnderProductPrice(double productPrice) throws DaoException {
        return null;
    }


}
