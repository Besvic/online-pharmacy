package com.pharmacy.traning.model.service.impl;

import com.pharmacy.traning.exception.ServiceException;
import com.pharmacy.traning.model.entity.CreditCard;
import com.pharmacy.traning.model.entity.User;
import com.pharmacy.traning.model.service.ServiceUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.when;

class ServiceUserImplTest {

    @Mock
    private static ServiceUserImpl instanceMock;
    private static final ServiceUser instanceUser = ServiceUserImpl.getInstance();

    @BeforeEach
    private void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    //Correct result

    @Test
    void registration() throws ServiceException {
        when(instanceMock.registration(new User.UserBuilder().createUser())).thenReturn(true);
        boolean actual = instanceMock.registration(new User.UserBuilder().createUser());
        assertTrue(actual);
    }

    @Test
    void updatePhotoById() throws ServiceException {
        when(instanceMock.updatePhotoById(anyString(), anyLong())).thenReturn(true);
        boolean actual = instanceMock.updatePhotoById(anyString(), anyLong());
        assertTrue(actual);
    }

    @Test
    void updateUserById() throws ServiceException {
        when(instanceMock.updateUserById(any(User.class), anyString(), anyString())).thenReturn(true);
        boolean actual = instanceMock.updateUserById(any(User.class), anyString(), anyString());
        assertTrue(actual);
    }

    @Test
    void changeUserStatusByUserId() throws ServiceException {
        when(instanceMock.changeUserStatusByUserId(anyString(), anyString())).thenReturn(true);
        boolean actual = instanceMock.changeUserStatusByUserId(anyString(), anyString());
        assertTrue(actual);
    }

    @Test
    void deleteUserByUserId() throws ServiceException {
        when(instanceMock.deleteUserByUserId(anyString())).thenReturn(true);
        boolean actual = instanceMock.deleteUserByUserId(anyString());
        assertTrue(actual);
    }

    @Test
    void findAllDeleteUser() throws ServiceException {
        when(instanceMock.findAllDeleteUser()).thenReturn(List.of(new User.UserBuilder().createUser()));
        List<User> actual = instanceMock.findAllDeleteUser();
        List<User> expected = List.of(new User.UserBuilder().createUser());
        assertEquals(expected, actual);
    }

    @Test
    void findAllNonDeleteUser() throws ServiceException {
        when(instanceMock.findAllNonDeleteUser()).thenReturn(List.of(new User.UserBuilder().createUser()));
        List<User> actual = instanceMock.findAllNonDeleteUser();
        List<User> expected = List.of(new User.UserBuilder().createUser());
        assertEquals(expected, actual);
    }

    @Test
    void searchDeleteUserByName() throws ServiceException {
        when(instanceMock.searchDeleteUserByName(anyString())).thenReturn(List.of(new User.UserBuilder().createUser()));
        List<User> actual = instanceMock.searchDeleteUserByName(anyString());
        List<User> expected = List.of(new User.UserBuilder().createUser());
        assertEquals(expected, actual);
    }

    @Test
    void searchNonDeleteUserByName() throws ServiceException {
        when(instanceMock.searchNonDeleteUserByName(anyString())).thenReturn(List.of(new User.UserBuilder().createUser()));
        List<User> actual = instanceMock.searchNonDeleteUserByName(anyString());
        List<User> expected = List.of(new User.UserBuilder().createUser());
        assertEquals(expected, actual);
    }

    @Test
    void findUserCashById() throws ServiceException {
        when(instanceMock.findUserCashById(anyLong())).thenReturn(1d);
        double actual = instanceMock.findUserCashById(anyLong());
        double expected = 1d;
        assertEquals(expected, actual);
    }

    @Test
    void updateCashById() throws ServiceException {
        when(instanceMock.updateCashById(any(CreditCard.class), anyLong())).thenReturn(true);
        boolean actual = instanceMock.updateCashById(any(CreditCard.class), anyLong());
        assertTrue(actual);
    }

    @Test
    void signIn() throws ServiceException {
        when(instanceMock.signIn(anyString(), anyString())).thenReturn(Optional.of(new User.UserBuilder().createUser()));
        Optional<User> actual = instanceMock.signIn(anyString(), anyString());
        Optional<User> expected = Optional.of(new User.UserBuilder().createUser());
        assertEquals(expected, actual);
    }

    //Incorrect result

    @Test
    void registrationThrowException() {
        assertThrows(ServiceException.class, ()->{
           instanceUser.registration(new User.UserBuilder()
                   .setPassword("123")
                   .setLogin("login")
                   .createUser());
        });
    }

    @Test
    void updatePhotoByIdThrowException() throws ServiceException {
        when(instanceMock.updatePhotoById(anyString(), anyLong())).thenReturn(false);
        boolean actual = instanceMock.updatePhotoById(anyString(), anyLong());
        assertFalse(actual);
    }

    @Test
    void updateUserByIdThrowException() throws ServiceException {
        when(instanceMock.updateUserById(any(User.class), anyString(), anyString())).thenReturn(false);
            boolean actual = instanceMock.updateUserById(any(User.class), anyString(), anyString());
            assertFalse(actual);
    }

    @Test
    void changeUserStatusByUserIdThrowException() {
        assertThrows(ServiceException.class, ()->{
            instanceUser.changeUserStatusByUserId("UserId", "ADMIN");
        });
    }

    @Test
    void deleteUserByUserIdThrowException() {
        assertThrows(ServiceException.class, ()->{
            instanceUser.deleteUserByUserId("UserId");
        });
    }

    @Test
    void findAllDeleteUserThrowException() throws ServiceException {
        when(instanceMock.findAllDeleteUser()).thenReturn(List.of());
        List<User> actual = instanceMock.findAllDeleteUser();
        List<User> expected = List.of();
        assertEquals(expected, actual);
    }

    @Test
    void findAllNonDeleteUserThrowException() throws ServiceException {
        when(instanceMock.findAllNonDeleteUser()).thenReturn(List.of());
        List<User> actual = instanceMock.findAllNonDeleteUser();
        List<User> expected = List.of();
        assertEquals(expected, actual);
    }

    @Test
    void searchDeleteUserByNameThrowException() {
        assertThrows(ServiceException.class, ()->{
           instanceUser.searchDeleteUserByName("name23");
        });
    }

    @Test
    void searchNonDeleteUserByNameThrowException() {
        assertThrows(ServiceException.class, ()->{
            instanceUser.searchNonDeleteUserByName("name23");
        });
    }

    @Test
    void findUserCashByIdThrowException() throws ServiceException {
        when(instanceMock.findUserCashById(anyLong())).thenReturn(0d);
        double actual = instanceMock.findUserCashById(anyLong());
        double expected = 0d;
        assertEquals(expected, actual);
    }

    @Test
    void updateCashByIdThrowException() throws ServiceException {
        when(instanceMock.updateCashById(any(CreditCard.class), anyLong())).thenReturn(false);
        boolean actual = instanceMock.updateCashById(any(CreditCard.class), anyLong());
        assertFalse(actual);
    }

    @Test
    void signInThrowException() {
        assertThrows(ServiceException.class, ()->{
            instanceUser.signIn("email", "pass");
        });
    }
}