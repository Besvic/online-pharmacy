package com.pharmacy.traning.model.dao.impl;

import com.pharmacy.traning.exception.DaoException;
import com.pharmacy.traning.model.entity.Pharmacy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.when;


class PharmacyDaoImplTest {

    @Mock
    private PharmacyDaoImpl instance;

    @BeforeEach
    private void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    //Correct result
    @Test
    void createPharmacy() throws DaoException {
        when(instance.createPharmacy(any(Pharmacy.class))).thenReturn(true);
        boolean actual = instance.createPharmacy(any(Pharmacy.class));
        assertTrue(actual);
    }

    @Test
    void deletePharmacy() throws DaoException {
        when(instance.deletePharmacy(anyLong())).thenReturn(true);
        boolean actual = instance.deletePharmacy(anyLong());
        assertTrue(actual);
    }

    @Test
    void restorePharmacy() throws DaoException {
        when(instance.restorePharmacy(anyLong())).thenReturn(true);
        boolean actual = instance.restorePharmacy(anyLong());
        assertTrue(actual);
    }

    @Test
    void findAllActualPharmacy() throws DaoException {
        when(instance.findAllActualPharmacy()).thenReturn(List.of(new Pharmacy.PharmacyBuilder().createPharmacy()));
        List<Pharmacy> actual = instance.findAllActualPharmacy();
        List<Pharmacy> expected = List.of(new Pharmacy.PharmacyBuilder().createPharmacy());
        assertEquals(expected, actual);
    }

    @Test
    void findAllPharmacy() throws DaoException {
        when(instance.findAllPharmacy()).thenReturn(List.of(new Pharmacy.PharmacyBuilder().createPharmacy()));
        List<Pharmacy> actual = instance.findAllPharmacy();
        List<Pharmacy> expected = List.of(new Pharmacy.PharmacyBuilder().createPharmacy());
        assertEquals(expected, actual);
    }

    // Incorrect result

    @Test
    void createPharmacyFalse() throws DaoException {
        when(instance.createPharmacy(any(Pharmacy.class))).thenReturn(false);
        boolean actual = instance.createPharmacy(any(Pharmacy.class));
        assertFalse(actual);
    }

    @Test
    void deletePharmacyFalse() throws DaoException {
        when(instance.deletePharmacy(anyLong())).thenReturn(false);
        boolean actual = instance.deletePharmacy(anyLong());
        assertFalse(actual);
    }

    @Test
    void restorePharmacyFalse() throws DaoException {
        when(instance.restorePharmacy(anyLong())).thenReturn(false);
        boolean actual = instance.restorePharmacy(anyLong());
        assertFalse(actual);
    }

    @Test
    void findAllActualPharmacyFalse() throws DaoException {
        when(instance.findAllActualPharmacy()).thenReturn(List.of());
        List<Pharmacy> actual = instance.findAllActualPharmacy();
        List<Pharmacy> expected = List.of();
        assertEquals(expected, actual);
    }

    @Test
    void findAllPharmacyFalse() throws DaoException {
        when(instance.findAllPharmacy()).thenReturn(List.of());
        List<Pharmacy> actual = instance.findAllPharmacy();
        List<Pharmacy> expected = List.of();
        assertEquals(expected, actual);
    }
}