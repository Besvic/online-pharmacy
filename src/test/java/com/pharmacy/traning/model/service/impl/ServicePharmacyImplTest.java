package com.pharmacy.traning.model.service.impl;

import com.pharmacy.traning.exception.ServiceException;
import com.pharmacy.traning.model.entity.Pharmacy;
import com.pharmacy.traning.model.service.ServicePharmacy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.when;

class ServicePharmacyImplTest {

    @Mock
    private static ServicePharmacyImpl instanceMock;
    private static final ServicePharmacy instanceOrder = new ServicePharmacyImpl();

    @BeforeEach
    private void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    // Correct result

    @Test
    void createPharmacy() throws ServiceException {
        when(instanceMock.createPharmacy(any(Pharmacy.class), anyString())).thenReturn(true);
        boolean actual = instanceMock.createPharmacy(any(Pharmacy.class), anyString());
        assertTrue(actual);
    }

    @Test
    void deletePharmacy() throws ServiceException {
        when(instanceMock.deletePharmacy(anyLong())).thenReturn(true);
        boolean actual = instanceMock.deletePharmacy(anyLong());
        assertTrue(actual);
    }

    @Test
    void restorePharmacy() throws ServiceException {
        when(instanceMock.restorePharmacy(anyLong())).thenReturn(true);
        boolean actual = instanceMock.restorePharmacy(anyLong());
        assertTrue(actual);
    }

    @Test
    void findAllActualPharmacy() throws ServiceException {
        when(instanceMock.findAllActualPharmacy()).thenReturn(List.of(new Pharmacy.PharmacyBuilder().createPharmacy()));
        List<Pharmacy> actual = instanceMock.findAllActualPharmacy();
        List<Pharmacy> expected = List.of(new Pharmacy.PharmacyBuilder().createPharmacy());
        assertEquals(expected, actual);
    }

    @Test
    void findAllPharmacy() throws ServiceException {
        when(instanceMock.findAllPharmacy()).thenReturn(List.of(new Pharmacy.PharmacyBuilder().createPharmacy()));
        List<Pharmacy> actual = instanceMock.findAllPharmacy();
        List<Pharmacy> expected = List.of(new Pharmacy.PharmacyBuilder().createPharmacy());
        assertEquals(expected, actual);
    }

    // Incorrect result

    @Test
    void createPharmacyThrowException() {
        assertThrows(ServiceException.class, ()->{
            instanceOrder.createPharmacy(new Pharmacy.PharmacyBuilder().createPharmacy(), "string");
        });
    }

    @Test
    void deletePharmacyThrowException() throws ServiceException {
        when(instanceMock.deletePharmacy(anyLong())).thenThrow(ServiceException.class);
        assertThrows(ServiceException.class, ()->{
            instanceMock.deletePharmacy(anyLong());
        });
    }

    @Test
    void restorePharmacyThrowException() throws ServiceException {
        when(instanceMock.restorePharmacy(anyLong())).thenThrow(ServiceException.class);
        assertThrows(ServiceException.class, ()->{
            instanceMock.restorePharmacy(anyLong());
        });
    }

    @Test
    void findAllActualPharmacyThrowException() throws ServiceException {
        when(instanceMock.findAllActualPharmacy()).thenThrow(ServiceException.class);
        assertThrows(ServiceException.class, ()->{
            instanceMock.findAllActualPharmacy();
        });
    }

    @Test
    void findAllPharmacyThrowException() throws ServiceException {
        when(instanceMock.findAllPharmacy()).thenThrow(ServiceException.class);
        assertThrows(ServiceException.class, ()->{
            instanceMock.findAllPharmacy();
        });
    }
}
