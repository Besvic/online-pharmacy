package com.pharmacy.traning.model.dao;

import com.pharmacy.traning.model.entity.Product;
import com.pharmacy.traning.exception.DaoException;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public interface ProductDao {

    boolean addProduct(Product product) throws DaoException;
    List<Product> findAllProduct() throws DaoException;
    List<Product> searchProductByName(String name) throws DaoException;
    List<Product> findAllDeleteProduct() throws DaoException;
    List<Product> searchDeleteProductByName(String name) throws DaoException;
    boolean deleteProductById(long id) throws DaoException;
    boolean reallyDeleteProductById(long id) throws DaoException;
    boolean restoreProductById(long id) throws DaoException;
    boolean changeProductByProductId(Product product) throws DaoException;
    boolean addProductQuantityByProductId(int productQuantity, long productId) throws DaoException;
    boolean reduceProductQuantityByProductId(int productQuantity, long productId, Connection connection) throws DaoException;
    Optional<Product> findProductById(long productId) throws DaoException;

    //List<Product> findProductByProductDate(LocalDate productDate) throws DaoException;
    List<Product> findProductUnderProductPrice(double productPrice) throws DaoException;

}
