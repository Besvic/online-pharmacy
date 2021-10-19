package com.pharmacy.traning.model.service.impl;

import com.pharmacy.traning.exception.ServiceException;
import com.pharmacy.traning.model.entity.Product;
import com.pharmacy.traning.model.service.ServiceProduct;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.when;

class ServiceProductImplTest {

    @Mock
    private static ServiceProduct instanceMock;
    private static final ServiceProduct instanceProduct = ServiceProductImpl.getInstance();

    @BeforeEach
    private void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    //Correct result

    @Test
    void createProduct() throws ServiceException {
        when(instanceMock.createProduct(any(Product.class), anyString(), anyString(), anyString())).thenReturn(true);
        boolean actual = instanceMock.createProduct(any(Product.class), anyString(), anyString(), anyString());
        assertTrue(actual);
    }

    @Test
    void deleteProductById() throws ServiceException {
        when(instanceMock.deleteProductById(anyLong())).thenReturn(true);
        boolean actual = instanceMock.deleteProductById(anyLong());
        assertTrue(actual);
    }

    @Test
    void restoreProductById() throws ServiceException {
        when(instanceMock.restoreProductById(anyLong())).thenReturn(true);
        boolean actual = instanceMock.restoreProductById(anyLong());
        assertTrue(actual);
    }

    @Test
    void reallyDeleteProductById() throws ServiceException {
        when(instanceMock.reallyDeleteProductById(anyLong())).thenReturn(true);
        boolean actual = instanceMock.reallyDeleteProductById(anyLong());
        assertTrue(actual);
    }

    @Test
    void findAllProduct() throws ServiceException {
        when(instanceMock.findAllProduct()).thenReturn(List.of(new Product.ProductBuilder().createProduct()));
        List<Product> actual = instanceMock.findAllProduct();
        List<Product> expected = List.of(new Product.ProductBuilder().createProduct());
        assertEquals(expected, actual);
    }

    @Test
    void searchProductByName() throws ServiceException {
        when(instanceMock.searchProductByName(anyString())).thenReturn(List.of(new Product.ProductBuilder().createProduct()));
        List<Product> actual = instanceMock.searchProductByName(anyString());
        List<Product> expected = List.of(new Product.ProductBuilder().createProduct());
        assertEquals(expected, actual);
    }

    @Test
    void findAllDeleteProduct() throws ServiceException {
        when(instanceMock.findAllDeleteProduct()).thenReturn(List.of(new Product.ProductBuilder().createProduct()));
        List<Product> actual = instanceMock.findAllDeleteProduct();
        List<Product> expected = List.of(new Product.ProductBuilder().createProduct());
        assertEquals(expected, actual);
    }

    @Test
    void searchDeleteProductByName() throws ServiceException {
        when(instanceMock.searchDeleteProductByName(anyString())).thenReturn(List.of(new Product.ProductBuilder().createProduct()));
        List<Product> actual = instanceMock.searchDeleteProductByName(anyString());
        List<Product> expected = List.of(new Product.ProductBuilder().createProduct());
        assertEquals(expected, actual);
    }

    @Test
    void addProductQuantityByProductId() throws ServiceException {
        when(instanceMock.addProductQuantityByProductId(anyString(), anyString())).thenReturn(true);
        boolean actual = instanceMock.addProductQuantityByProductId(anyString(), anyString());
        assertTrue(actual);
    }

    @Test
    void changeProduct() throws ServiceException {
        when(instanceMock.changeProduct(any(Product.class), anyString(), anyString(), anyString())).thenReturn(true);
        boolean actual = instanceMock.changeProduct(any(Product.class), anyString(), anyString(), anyString());
        assertTrue(actual);
    }

    @Test
    void findProductById() throws ServiceException {
        when(instanceMock.findProductById(anyString())).thenReturn(new Product.ProductBuilder().createProduct());
        Product actual = instanceMock.findProductById(anyString());
        Product expected = new Product.ProductBuilder().createProduct();
        assertEquals(expected, actual);
    }

    //Incorrect result

    @Test
    void createProductThrowException() {
        assertThrows(ServiceException.class, ()->{
           instanceProduct.changeProduct(new Product.ProductBuilder().createProduct(), "dosage", "quantity", "price");
        });
    }

    @Test
    void deleteProductByIdThrowException() throws ServiceException {
        when(instanceMock.deleteProductById(anyLong())).thenReturn(false);
        boolean actual = instanceMock.deleteProductById(anyLong());
        assertFalse(actual);
    }

    @Test
    void restoreProductByIdThrowException() throws ServiceException {
        when(instanceMock.restoreProductById(anyLong())).thenReturn(false);
        boolean actual = instanceMock.restoreProductById(anyLong());
        assertFalse(actual);
    }

    @Test
    void reallyDeleteProductByIdThrowException() throws ServiceException {
        when(instanceMock.reallyDeleteProductById(anyLong())).thenReturn(true);
        boolean actual = instanceMock.reallyDeleteProductById(anyLong());
        assertTrue(actual);
    }

    @Test
    void findAllProductThrowException() throws ServiceException {
        when(instanceMock.findAllProduct()).thenReturn(List.of());
        List<Product> actual = instanceMock.findAllProduct();
        List<Product> expected = List.of();
        assertEquals(expected, actual);
    }

    @Test
    void searchProductByNameThrowException() {
        assertThrows(ServiceException.class, ()->{
            instanceProduct.searchProductByName("");
        });
    }

    @Test
    void findAllDeleteProductThrowException() throws ServiceException {
        when(instanceMock.findAllDeleteProduct()).thenReturn(List.of());
        List<Product> actual = instanceMock.findAllDeleteProduct();
        List<Product> expected = List.of();
        assertEquals(expected, actual);
    }

    @Test
    void searchDeleteProductByNameThrowException() {
        assertThrows(ServiceException.class, ()->{
            instanceProduct.searchDeleteProductByName("");
        });
    }

    @Test
    void addProductQuantityByProductIdThrowException() {
        assertThrows(ServiceException.class, ()->{
            instanceProduct.addProductQuantityByProductId("product", "12");
        });
    }

    @Test
    void changeProductThrowException() {
        assertThrows(ServiceException.class, ()->{
            instanceProduct.changeProduct(new Product.ProductBuilder().createProduct(), "dosage", "12", "price");
        });
    }

    @Test
    void findProductByIdThrowException() {
        assertThrows(ServiceException.class, ()->{
            instanceProduct.findProductById("id");
        });
    }
}