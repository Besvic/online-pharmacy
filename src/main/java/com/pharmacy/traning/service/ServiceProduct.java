package com.pharmacy.traning.service;

import com.pharmacy.traning.exception.DaoException;
import com.pharmacy.traning.exception.ServiceException;
import com.pharmacy.traning.model.entity.Order;
import com.pharmacy.traning.model.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ServiceProduct {
    boolean createProduct(Optional<Product> product, String dosage, String price, String number) throws ServiceException, DaoException;
    boolean deleteProductById(long id) throws ServiceException, DaoException;
    boolean restoreProductById(long id) throws ServiceException, DaoException;
    boolean reallyDeleteProductById(long id) throws ServiceException, DaoException;
    List<Product> findAllProduct() throws ServiceException, DaoException;
    List<Product> searchProductByName(String name) throws ServiceException, DaoException;
    List<Product> findAllDeleteProduct() throws ServiceException, DaoException;
    List<Product> searchDeleteProductByName(String name) throws ServiceException, DaoException;
    boolean addProductQuantityByProductId(String productQuantity, String id) throws ServiceException, DaoException;
    boolean changeProduct(Optional<Product> product, String strDosage, String strQuantity, String strPrice) throws ServiceException, DaoException;
    Product findProductById(String id) throws ServiceException, DaoException;




}
