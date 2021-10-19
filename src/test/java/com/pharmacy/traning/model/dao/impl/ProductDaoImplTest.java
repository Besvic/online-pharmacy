package com.pharmacy.traning.model.dao.impl;

import com.pharmacy.traning.exception.DaoException;
import com.pharmacy.traning.model.entity.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.when;

class ProductDaoImplTest {

    @Mock
    private ProductDaoImpl instance;

    @BeforeEach
    private void SetUp(){
        MockitoAnnotations.initMocks(this);
    }

    // Correct result

    @Test
    void addProduct() throws DaoException {
        when(instance.addProduct(any(Product.class))).thenReturn(true);
        boolean actual = instance.addProduct(any(Product.class));
        assertTrue(actual);
    }

    @Test
    void findAllProduct() throws DaoException {
        when(instance.findAllProduct()).thenReturn(List.of(new Product.ProductBuilder().createProduct()));
        List<Product> actual = instance.findAllProduct();
        List<Product> expected = List.of(new Product.ProductBuilder().createProduct());
        assertEquals(expected, actual);
    }

    @Test
    void searchProductByName() throws DaoException {
        when(instance.searchProductByName(anyString())).thenReturn(List.of(new Product.ProductBuilder().createProduct()));
        List<Product> actual = instance.searchProductByName(anyString());
        List<Product> expected = List.of(new Product.ProductBuilder().createProduct());
        assertEquals(expected, actual);
    }

    @Test
    void findAllDeleteProduct() throws DaoException {
        when(instance.findAllDeleteProduct()).thenReturn(List.of(new Product.ProductBuilder().createProduct()));
        List<Product> actual = instance.findAllDeleteProduct();
        List<Product> expected = List.of(new Product.ProductBuilder().createProduct());
        assertEquals(expected, actual);
    }

    @Test
    void searchDeleteProductByName() throws DaoException {
        when(instance.searchDeleteProductByName(anyString())).thenReturn(List.of(new Product.ProductBuilder().createProduct()));
        List<Product> actual = instance.searchDeleteProductByName(anyString());
        List<Product> expected = List.of(new Product.ProductBuilder().createProduct());
        assertEquals(expected, actual);
    }

    @Test
    void deleteProductById() throws DaoException {
        when(instance.deleteProductById(anyLong())).thenReturn(true);
        boolean actual = instance.deleteProductById(anyLong());
        assertTrue(actual);
    }

    @Test
    void restoreProductById() throws DaoException {
        when(instance.restoreProductById(anyLong())).thenReturn(true);
        boolean actual = instance.restoreProductById(anyLong());
        assertTrue(actual);
    }

    @Test
    void reallyDeleteProductById() throws DaoException {
        when(instance.reallyDeleteProductById(anyLong())).thenReturn(true);
        boolean actual = instance.reallyDeleteProductById(anyLong());
        assertTrue(actual);
    }

    @Test
    void changeProductByProductId() throws DaoException {
        when(instance.changeProductByProductId(any(Product.class))).thenReturn(true);
        boolean actual = instance.changeProductByProductId(any(Product.class));
        assertTrue(actual);
    }

    @Test
    void addProductQuantityByProductId() throws DaoException {
        when(instance.addProductQuantityByProductId(anyInt(), anyLong())).thenReturn(true);
        boolean actual = instance.addProductQuantityByProductId(anyInt(), anyLong());
        assertTrue(actual);
    }

    @Test
    void reduceProductQuantityByProductId() throws DaoException {
        when(instance.reduceProductQuantityByProductId(anyInt(), anyLong(), any(Connection.class))).thenReturn(true);
        boolean actual = instance.reduceProductQuantityByProductId(anyInt(), anyLong(), any(Connection.class));
        assertTrue(actual);
    }

    @Test
    void findProductById() throws DaoException {
        when(instance.findProductById(anyLong())).thenReturn(Optional.ofNullable(new Product.ProductBuilder().createProduct()));
        Optional<Product> actual = instance.findProductById(anyLong());
        Optional<Product> expected = Optional.ofNullable(new Product.ProductBuilder().createProduct());
        assertEquals(expected, actual);
    }

    //Incorrect result

    @Test
    void addProductFalse() throws DaoException {
        when(instance.addProduct(any(Product.class))).thenReturn(false);
        boolean actual = instance.addProduct(any(Product.class));
        assertFalse(actual);
    }

    @Test
    void findAllProductFalse() throws DaoException {
        when(instance.findAllProduct()).thenReturn(List.of());
        List<Product> actual = instance.findAllProduct();
        List<Product> expected = List.of();
        assertEquals(expected, actual);
    }

    @Test
    void searchProductByNameFalse() throws DaoException {
        when(instance.searchProductByName(anyString())).thenReturn(List.of());
        List<Product> actual = instance.searchProductByName(anyString());
        List<Product> expected = List.of();
        assertEquals(expected, actual);
    }

    @Test
    void findAllDeleteProductFalse() throws DaoException {
        when(instance.findAllDeleteProduct()).thenReturn(List.of());
        List<Product> actual = instance.findAllDeleteProduct();
        List<Product> expected = List.of();
        assertEquals(expected, actual);
    }

    @Test
    void searchDeleteProductByNameFalse() throws DaoException {
        when(instance.searchDeleteProductByName(anyString())).thenReturn(List.of());
        List<Product> actual = instance.searchDeleteProductByName(anyString());
        List<Product> expected = List.of();
        assertEquals(expected, actual);
    }

    @Test
    void deleteProductByIdFalse() throws DaoException {
        when(instance.deleteProductById(anyLong())).thenReturn(false);
        boolean actual = instance.deleteProductById(anyLong());
        assertFalse(actual);
    }

    @Test
    void restoreProductByIdFalse() throws DaoException {
        when(instance.restoreProductById(anyLong())).thenReturn(false);
        boolean actual = instance.restoreProductById(anyLong());
        assertFalse(actual);
    }

    @Test
    void reallyDeleteProductByIdFalse() throws DaoException {
        when(instance.reallyDeleteProductById(anyLong())).thenReturn(false);
        boolean actual = instance.reallyDeleteProductById(anyLong());
        assertFalse(actual);
    }

    @Test
    void changeProductByProductIdFalse() throws DaoException {
        when(instance.changeProductByProductId(any(Product.class))).thenReturn(false);
        boolean actual = instance.changeProductByProductId(any(Product.class));
        assertFalse(actual);
    }

    @Test
    void addProductQuantityByProductIdFalse() throws DaoException {
        when(instance.addProductQuantityByProductId(anyInt(), anyLong())).thenReturn(false);
        boolean actual = instance.addProductQuantityByProductId(anyInt(), anyLong());
        assertFalse(actual);
    }

    @Test
    void reduceProductQuantityByProductIdFalse() throws DaoException {
        when(instance.reduceProductQuantityByProductId(anyInt(), anyLong(), any(Connection.class))).thenReturn(false);
        boolean actual = instance.reduceProductQuantityByProductId(anyInt(), anyLong(), any(Connection.class));
        assertFalse(actual);
    }

    @Test
    void findProductByIdFalse() throws DaoException {
        when(instance.findProductById(anyLong())).thenReturn(Optional.empty());
        Optional<Product> actual = instance.findProductById(anyLong());
        Optional<Product> expected = Optional.empty();
        assertEquals(expected, actual);
    }
}