package com.pharmacy.traning.model.service;

import com.pharmacy.traning.exception.ServiceException;
import com.pharmacy.traning.model.entity.Product;

import java.util.List;

/**
  * @author Besarab Victor
  * The interface Service product definition all general methods for validate data before using Dao.
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
     */
    boolean createProduct(Product product, String dosage, String price, String number) throws ServiceException;

    /**
     * Delete product by id boolean.
     *
     * @param id the id
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean deleteProductById(long id) throws ServiceException;

    /**
     * Restore product by id boolean.
     *
     * @param id the id
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean restoreProductById(long id) throws ServiceException;

    /**
     * Really delete product by id check input data and send to dao method.
     *
     * @param id the id
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean reallyDeleteProductById(long id) throws ServiceException;

    /**
     * Find all product check input data and send to dao method.
     *
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Product> findAllProduct() throws ServiceException;

    /**
     * Search product by name check input data and send to dao method.
     *
     * @param name the name
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Product> searchProductByName(String name) throws ServiceException;

    /**
     * Find all delete product check input data and send to dao method.
     *
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Product> findAllDeleteProduct() throws ServiceException;

    /**
     * Search delete product by name check input data and send to dao method.
     *
     * @param name the name
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Product> searchDeleteProductByName(String name) throws ServiceException;

    /**
     * Add product quantity by product id check input data and send to dao method.
     *
     * @param productQuantity the product quantity
     * @param id              the id
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean addProductQuantityByProductId(String productQuantity, String id) throws ServiceException;

    /**
     * Change product check input data and send to dao method.
     *
     * @param product     the product
     * @param strDosage   the str dosage
     * @param strQuantity the str quantity
     * @param strPrice    the str price
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean changeProduct(Product product, String strDosage, String strQuantity, String strPrice) throws ServiceException;

    /**
     * Find product by id check input data and send to dao method.
     *
     * @param id the id
     * @return the product
     * @throws ServiceException the service exception
     */
    Product findProductById(String id) throws ServiceException;




}
