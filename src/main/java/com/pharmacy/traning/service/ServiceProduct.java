package com.pharmacy.traning.service;

import com.pharmacy.traning.exception.DaoException;
import com.pharmacy.traning.exception.ServiceException;
import com.pharmacy.traning.model.entity.Product;

import java.util.List;
import java.util.Optional;

/**
 * The interface Service product.
 */
public interface ServiceProduct {
    /**
     * Create product boolean.
     *
     * @param product the product
     * @param dosage  the dosage
     * @param price   the price
     * @param number  the number
     * @return the boolean
     * @throws ServiceException the service exception
     * @throws DaoException     the dao exception
     */
    boolean createProduct(Product product, String dosage, String price, String number) throws ServiceException, DaoException;

    /**
     * Delete product by id boolean.
     *
     * @param id the id
     * @return the boolean
     * @throws ServiceException the service exception
     * @throws DaoException     the dao exception
     */
    boolean deleteProductById(long id) throws ServiceException, DaoException;

    /**
     * Restore product by id boolean.
     *
     * @param id the id
     * @return the boolean
     * @throws ServiceException the service exception
     * @throws DaoException     the dao exception
     */
    boolean restoreProductById(long id) throws ServiceException, DaoException;

    /**
     * Really delete product by id boolean.
     *
     * @param id the id
     * @return the boolean
     * @throws ServiceException the service exception
     * @throws DaoException     the dao exception
     */
    boolean reallyDeleteProductById(long id) throws ServiceException, DaoException;

    /**
     * Find all product list.
     *
     * @return the list
     * @throws ServiceException the service exception
     * @throws DaoException     the dao exception
     */
    List<Product> findAllProduct() throws ServiceException, DaoException;

    /**
     * Search product by name list.
     *
     * @param name the name
     * @return the list
     * @throws ServiceException the service exception
     * @throws DaoException     the dao exception
     */
    List<Product> searchProductByName(String name) throws ServiceException, DaoException;

    /**
     * Find all delete product list.
     *
     * @return the list
     * @throws ServiceException the service exception
     * @throws DaoException     the dao exception
     */
    List<Product> findAllDeleteProduct() throws ServiceException, DaoException;

    /**
     * Search delete product by name list.
     *
     * @param name the name
     * @return the list
     * @throws ServiceException the service exception
     * @throws DaoException     the dao exception
     */
    List<Product> searchDeleteProductByName(String name) throws ServiceException, DaoException;

    /**
     * Add product quantity by product id boolean.
     *
     * @param productQuantity the product quantity
     * @param id              the id
     * @return the boolean
     * @throws ServiceException the service exception
     * @throws DaoException     the dao exception
     */
    boolean addProductQuantityByProductId(String productQuantity, String id) throws ServiceException, DaoException;

    /**
     * Change product boolean.
     *
     * @param product     the product
     * @param strDosage   the str dosage
     * @param strQuantity the str quantity
     * @param strPrice    the str price
     * @return the boolean
     * @throws ServiceException the service exception
     * @throws DaoException     the dao exception
     */
    boolean changeProduct(Product product, String strDosage, String strQuantity, String strPrice) throws ServiceException, DaoException;

    /**
     * Find product by id product.
     *
     * @param id the id
     * @return the product
     * @throws ServiceException the service exception
     * @throws DaoException     the dao exception
     */
    Product findProductById(String id) throws ServiceException, DaoException;




}
