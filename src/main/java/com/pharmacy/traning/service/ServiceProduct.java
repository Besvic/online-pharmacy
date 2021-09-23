package com.pharmacy.traning.service;

import com.pharmacy.traning.exception.ServiceException;
import com.pharmacy.traning.model.entity.Product;

public interface ServiceProduct {
    boolean createProduct(Product product) throws ServiceException;
    boolean addProductQuantityByProductId(Product product, long id) throws ServiceException;
    boolean changeProduct(Product product) throws ServiceException;



}
