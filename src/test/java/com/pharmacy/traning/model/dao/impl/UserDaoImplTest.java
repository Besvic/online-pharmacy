package com.pharmacy.traning.model.dao.impl;

import com.pharmacy.traning.exception.DaoException;
import com.pharmacy.traning.model.entity.User;
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

class UserDaoImplTest {

    @Mock
    private static UserDaoImpl instance;

    @BeforeEach
    private void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    // Correct result

    @Test
    void createUser() throws DaoException {
        when(instance.createUser(any(User.class))).thenReturn(true);
        boolean actual = instance.createUser(any(User.class));
        assertTrue(actual);
    }

    @Test
    void deleteUserById() throws DaoException {
        when(instance.deleteUserById(anyLong())).thenReturn(true);
        boolean actual = instance.deleteUserById(anyLong());
        assertTrue(actual);
    }

    @Test
    void updateUserById() throws DaoException {
        when(instance.updateUserById(any(User.class))).thenReturn(true);
        boolean actual = instance.updateUserById(any(User.class));
        assertTrue(actual);
    }

    @Test
    void updateCashById() throws DaoException {
        when(instance.updateCashById(anyDouble(), anyLong())).thenReturn(true);
        boolean actual = instance.updateCashById(anyDouble(), anyLong());
        assertTrue(actual);
    }

    @Test
    void reduceCashById() throws DaoException {
        when(instance.reduceCashById(anyDouble(), anyLong(), any(Connection.class))).thenReturn(true);
        boolean actual = instance.reduceCashById(anyDouble(), anyLong(), any(Connection.class));
        assertTrue(actual);
    }

    @Test
    void changeUserStatusOnInRegister() throws DaoException {
        when(instance.changeUserStatusOnInRegister(anyLong())).thenReturn(true);
        boolean actual = instance.changeUserStatusOnInRegister(anyLong());
        assertTrue(actual);
    }

    @Test
    void changeUserStatusOnActive() throws DaoException {
        when(instance.changeUserStatusOnActive(anyLong())).thenReturn(true);
        boolean actual = instance.changeUserStatusOnActive(anyLong());
        assertTrue(actual);
    }

    @Test
    void findAllDeleteUser() throws DaoException {
        when(instance.findAllDeleteUser()).thenReturn(List.of(new User.UserBuilder().createUser()));
        List<User> actual = instance.findAllDeleteUser();
        List<User> expected = List.of(new User.UserBuilder().createUser());
        assertEquals(expected, actual);
    }

    @Test
    void findAllNonDeleteUser() throws DaoException {
        when(instance.findAllNonDeleteUser()).thenReturn(List.of(new User.UserBuilder().createUser()));
        List<User> actual = instance.findAllNonDeleteUser();
        List<User> expected = List.of(new User.UserBuilder().createUser());
        assertEquals(expected, actual);
    }

    @Test
    void searchDeleteUserByName() throws DaoException {
        when(instance.searchDeleteUserByName(anyString())).thenReturn(List.of(new User.UserBuilder().createUser()));
        List<User> actual = instance.searchDeleteUserByName(anyString());
        List<User> expected = List.of(new User.UserBuilder().createUser());
        assertEquals(expected, actual);
    }

    @Test
    void searchNonDeleteUserByName() throws DaoException {
        when(instance.searchNonDeleteUserByName(anyString())).thenReturn(List.of(new User.UserBuilder().createUser()));
        List<User> actual = instance.searchNonDeleteUserByName(anyString());
        List<User> expected = List.of(new User.UserBuilder().createUser());
        assertEquals(expected, actual);
    }

    @Test
    void checkAuthorisation() throws DaoException {
        when(instance.checkAuthorisation(anyString(), anyString())).thenReturn(Optional.ofNullable(new User.UserBuilder().createUser()));
        Optional<User> actual = instance.checkAuthorisation(anyString(), anyString());
        Optional<User> expected = Optional.ofNullable(new User.UserBuilder().createUser());
        assertEquals(expected, actual);
    }

    @Test
    void updatePhotoById() throws DaoException {
        when(instance.updatePhotoById(anyString(), anyLong())).thenReturn(true);
        boolean actual = instance.updatePhotoById(anyString(), anyLong());
        assertTrue(actual);
    }

    @Test
    void checkIsAdmin() throws DaoException {
        when(instance.checkIsAdmin()).thenReturn(true);
        boolean actual = instance.checkIsAdmin();
        assertTrue(actual);
    }

    @Test
    void findUserCashById() throws DaoException {
        when(instance.findUserCashById(anyLong())).thenReturn(12.2);
        double actual = instance.findUserCashById(anyLong());
        double expected = 12.2;
        assertEquals(expected, actual);
    }

    // Incorrect result

    @Test
    void createUserFalse() throws DaoException {
        when(instance.createUser(any(User.class))).thenReturn(false);
        boolean actual = instance.createUser(any(User.class));
        assertFalse(actual);
    }

    @Test
    void deleteUserByIdFalse() throws DaoException {
        when(instance.deleteUserById(anyLong())).thenReturn(false);
        boolean actual = instance.deleteUserById(anyLong());
        assertFalse(actual);
    }

    @Test
    void updateUserByIdFalse() throws DaoException {
        when(instance.updateUserById(any(User.class))).thenReturn(false);
        boolean actual = instance.updateUserById(any(User.class));
        assertFalse(actual);
    }

    @Test
    void updateCashByIdFalse() throws DaoException {
        when(instance.updateCashById(anyDouble(), anyLong())).thenReturn(false);
        boolean actual = instance.updateCashById(anyDouble(), anyLong());
        assertFalse(actual);
    }

    @Test
    void reduceCashByIdFalse() throws DaoException {
        when(instance.reduceCashById(anyDouble(), anyLong(), any(Connection.class))).thenReturn(false);
        boolean actual = instance.reduceCashById(anyDouble(), anyLong(), any(Connection.class));
        assertFalse(actual);
    }

    @Test
    void changeUserStatusOnInRegisterFalse() throws DaoException {
        when(instance.changeUserStatusOnInRegister(anyLong())).thenReturn(false);
        boolean actual = instance.changeUserStatusOnInRegister(anyLong());
        assertFalse(actual);
    }

    @Test
    void changeUserStatusOnActiveFalse() throws DaoException {
        when(instance.changeUserStatusOnActive(anyLong())).thenReturn(false);
        boolean actual = instance.changeUserStatusOnActive(anyLong());
        assertFalse(actual);
    }

    @Test
    void findAllDeleteUserFalse() throws DaoException {
        when(instance.findAllDeleteUser()).thenReturn(List.of());
        List<User> actual = instance.findAllDeleteUser();
        List<User> expected = List.of();
        assertEquals(expected, actual);
    }

    @Test
    void findAllNonDeleteUserFalse() throws DaoException {
        when(instance.findAllNonDeleteUser()).thenReturn(List.of());
        List<User> actual = instance.findAllNonDeleteUser();
        List<User> expected = List.of();
        assertEquals(expected, actual);
    }

    @Test
    void searchDeleteUserByNameFalse() throws DaoException {
        when(instance.searchDeleteUserByName(anyString())).thenReturn(List.of());
        List<User> actual = instance.searchDeleteUserByName(anyString());
        List<User> expected = List.of();
        assertEquals(expected, actual);
    }

    @Test
    void searchNonDeleteUserByNameFalse() throws DaoException {
        when(instance.searchNonDeleteUserByName(anyString())).thenReturn(List.of());
        List<User> actual = instance.searchNonDeleteUserByName(anyString());
        List<User> expected = List.of();
        assertEquals(expected, actual);
    }

    @Test
    void checkAuthorisationFalse() throws DaoException {
        when(instance.checkAuthorisation(anyString(), anyString())).thenReturn(Optional.empty());
        Optional<User> actual = instance.checkAuthorisation(anyString(), anyString());
        Optional<User> expected = Optional.empty();
        assertEquals(expected, actual);
    }

    @Test
    void updatePhotoByIdFalse() throws DaoException {
        when(instance.updatePhotoById(anyString(), anyLong())).thenReturn(false);
        boolean actual = instance.updatePhotoById(anyString(), anyLong());
        assertFalse(actual);
    }

    @Test
    void checkIsAdminFalse() throws DaoException {
        when(instance.checkIsAdmin()).thenReturn(false);
        boolean actual = instance.checkIsAdmin();
        assertFalse(actual);
    }

    @Test
    void findUserCashByIdFalse() throws DaoException {
        when(instance.findUserCashById(anyLong())).thenReturn(0d);
        double actual = instance.findUserCashById(anyLong());
        double expected = 0d;
        assertEquals(expected, actual);
    }

}