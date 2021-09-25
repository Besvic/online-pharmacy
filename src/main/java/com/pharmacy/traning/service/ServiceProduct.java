package com.pharmacy.traning.service;

import com.pharmacy.traning.exception.DaoException;
import com.pharmacy.traning.exception.ServiceException;
import com.pharmacy.traning.model.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ServiceProduct {
    boolean createProduct(Optional<Product> product) throws ServiceException, DaoException;
    boolean deleteProductById(long id) throws ServiceException;
    boolean restoreProductById(long id) throws ServiceException, DaoException;
    boolean reallyDeleteProductById(long id) throws ServiceException, DaoException;
    List<Product> findAllProduct() throws ServiceException;
    List<Product> findAllDeleteProduct() throws ServiceException, DaoException;
    boolean addProductQuantityByProductId(int productQuantity, long id) throws ServiceException, DaoException;
    boolean changeProduct(Optional<Product> product) throws ServiceException, DaoException;
    Product findProductById(long id) throws ServiceException, DaoException;




}
