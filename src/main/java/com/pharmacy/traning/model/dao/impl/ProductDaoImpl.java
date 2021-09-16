package com.pharmacy.traning.model.dao.impl;

import com.pharmacy.traning.model.entity.Product;
import com.pharmacy.traning.exception.DaoException;
import com.pharmacy.traning.model.dao.ProductDao;
import com.pharmacy.traning.model.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ProductDaoImpl implements ProductDao {

    private static final Logger logger = LogManager.getLogger();

    private static final String SQL_ADD_PRODUCT = """
            insert into product (product_name, product_dosage, product_manufacture_id, product_quantity,
                                 product_price, product_date_of_delivery, product_measure_id, product_photo)
            values (?, ?, ?, ?, ?, ?, ?, ?);""";
    private static final String SQL_CHANGE_PRODUCT_NAME_BY_PRODUCT_ID = """
            update product
            set product_name = ?
            where product_id = ?;""";
    private static final String SQL_ADD_PRODUCT_QUANTITY_BY_PRODUCT_ID = """
            update product
            set product_quantity = product_quantity + ?
            where product_id = ?;""";
    private static final String SQL_CHANGE_PRODUCT_PRICE_BY_PRODUCT_ID = """
            update product
            set product_price = ?
            where product_id = ?;""";
    private static final String SQL_FIND_PRODUCT_UNDER_PRODUCT_PRICE = """
            select product_id, product_name, product_dosage, product_manufacture_id, product_quantity,
                   product_price, product_date_of_delivery, product_measure_id, product_photo
            from product
            where product_price <= ?;""";
    @Override
    public boolean addProduct(Product product) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_ADD_PRODUCT)){
            statement.setString(1, product.getName());
            statement.setDouble(2, product.getDosage());
            statement.setLong(3, product.getManufactureId());
            statement.setInt(4, product.getQuantity());
            statement.setDouble(5, product.getPrice());
            statement.setDate(6, Date.valueOf(product.getDateOfDelivery()));
            statement.setLong(7, product.getMeasureId());
            // TODO: 03.09.2021 добавить запись на фотку
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean changeProductNameByProductId(String productName, int productId) throws DaoException {
        return false;
    }

    @Override
    public boolean addProductQuantityByProductId(int productQuantity, int productId) throws DaoException {
        return false;
    }

    @Override
    public boolean changeProductPriceByProductId(double productPrice, int productId) throws DaoException {
        return false;
    }

    @Override
    public List<Product> findProductUnderProductPrice(double productPrice) throws DaoException {
        return null;
    }
}
