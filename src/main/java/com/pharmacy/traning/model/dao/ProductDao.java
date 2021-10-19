package com.pharmacy.traning.model.dao;

import com.pharmacy.traning.model.entity.Product;
import com.pharmacy.traning.exception.DaoException;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

/**
 * @author Besarab Victor
 * The interface Product dao find data in database.
 */
public interface ProductDao {

    /**
     * Add product boolean.
     *
     * @param product the product
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean addProduct(Product product) throws DaoException;

    /**
     * Find all product list.
     *
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Product> findAllProduct() throws DaoException;

    /**
     * Search product by name list.
     *
     * @param name the name
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Product> searchProductByName(String name) throws DaoException;

    /**
     * Find all delete product list.
     *
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Product> findAllDeleteProduct() throws DaoException;

    /**
     * Search delete product by name list.
     *
     * @param name the name
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Product> searchDeleteProductByName(String name) throws DaoException;

    /**
     * Delete product by id boolean.
     *
     * @param id the id
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean deleteProductById(long id) throws DaoException;

    /**
     * Really delete product by id boolean.
     *
     * @param id the id
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean reallyDeleteProductById(long id) throws DaoException;

    /**
     * Restore product by id boolean.
     *
     * @param id the id
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean restoreProductById(long id) throws DaoException;

    /**
     * Change product by product id boolean.
     *
     * @param product the product
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean changeProductByProductId(Product product) throws DaoException;

    /**
     * Add product quantity by product id boolean.
     *
     * @param productQuantity the product quantity
     * @param productId       the product id
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean addProductQuantityByProductId(int productQuantity, long productId) throws DaoException;

    /**
     * Reduce product quantity by product id boolean.
     *
     * @param productQuantity the product quantity
     * @param productId       the product id
     * @param connection      the connection
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean reduceProductQuantityByProductId(int productQuantity, long productId, Connection connection) throws DaoException;

    /**
     * Find product by id optional.
     *
     * @param productId the product id
     * @return the optional
     * @throws DaoException the dao exception
     */
    Optional<Product> findProductById(long productId) throws DaoException;


}
