package com.pharmacy.traning.model.dao.impl;

import com.pharmacy.traning.model.entity.User;
import com.pharmacy.traning.exception.DaoException;
import com.pharmacy.traning.model.dao.UserDao;
import com.pharmacy.traning.model.pool.ConnectionPool;
import com.pharmacy.traning.model.dao.ColumnName;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.pharmacy.traning.model.dao.ColumnName.*;

/**
 * @author Besarab Victor
 * The type User dao.
 */
public class UserDaoImpl implements UserDao {

    private static final Logger logger = LogManager.getLogger();
    private static UserDaoImpl instance;

    private static final String SQL_CREATE_USER = """
            insert into users (user_position, user_name, user_cash, user_login, user_password, user_status)
            values (?, ?, ?, ?, ?, ?);""";

    private static final String SQL_DELETE_USER = """
            update users
            set user_status = 'delete'
            where user_id = ?;""";

    private static final String SQL_USER_CASH_BY_USER_ID = """
            select user_cash
            from users
            where user_id = ?;""";

    private static final String SQL_SET_ACTIVE_USER_BY_USER_ID = """
            update users
            set user_status = 'active'
            where user_id = ?;""";

    private static final String SQL_SET_IN_REGISTER_USER_BY_USER_ID = """
            update users
            set user_status = 'in_register'
            where user_id = ?;""";

    private static final String SQL_FIND_ALL_DELETE_USER = """
            select user_id, user_position, user_name, user_cash, user_login, user_status, user_photo
            from users
            where user_status = 'delete' and user_position = 'user'
            order by user_name;""";

    private static final String SQL_FIND_ALL_NON_DELETE_USER = """
            select user_id, user_position, user_name, user_cash, user_login, user_password, user_status, user_photo
            from users
            where user_status != 'delete' and user_position = 'user'
            order by(case user_status
            when 'in_register' then 1
            when 'active' then 2
            end), user_name;""";

    private static final String SQL_SEARCH_DELETE_USER_BY_NAME = """
           select user_id, user_position, user_name, user_cash, user_login, user_password, user_status, user_photo
            from users
            where user_status = 'delete' and user_position = 'user' and user_name like ?
            order by user_name;""";

    private static final String SQL_SEARCH_NON_DELETE_USER_BY_NAME = """
           select user_id, user_position, user_name, user_cash, user_login, user_password, user_status, user_photo
            from users
            where user_status != 'delete' and user_position = 'user' and user_name like ?
            order by user_name;""";

    private static final String SQL_CHECK_AUTHORISATION = """
            select user_id, user_position, user_name, user_cash, user_login, user_password, user_status, user_photo
            from users
            where user_login = ? and user_password = ? and user_status != 'delete';""";

    private static final String SQL_UPDATE_PHOTO_BY_ID = """
            update users
            set user_photo = ?
            where user_id = ?;""";

    private static final String SQL_UPDATE_CASH_BY_ID = """
            update users
            set user_cash = user_cash + ? 
            where user_id = ?;""";

    private static final String SQL_REDUCE_CASH_BY_ID = """
            update users
            set user_cash = user_cash - ? 
            where user_id = ?;""";

    private static final String SQL_UPDATE_PASSWORD_NAME_BY_ID = """
             update users
             set user_password = ? , user_name = ?
             where user_id = ?;""";


    private static final String SQL_CHECK_IS_ADMIN = """
            select user_position
            from users
            where user_position = 'admin' and user_status != 'delete';""";

    /**
     * Get instance user dao.
     *
     * @return the user dao
     */
    public static UserDaoImpl getInstance(){
        if (instance == null){
            instance = new UserDaoImpl();
        }
        return instance;
    }

    private UserDaoImpl(){

    }

    @Override
    public boolean createUser(User user) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_CREATE_USER)){
            statement.setString(1, String.valueOf(user.getPosition()));
            statement.setString(2, user.getName());
            statement.setDouble(3, user.getCash());
            statement.setString(4, user.getLogin());
            statement.setString(5, user.getPassword());
            statement.setString(6, user.getUserStatus().toString());
           return statement.executeUpdate() != 0;
        } catch (SQLException e) {
            logger.error("PrepareStatement didn't connection or createUser method is not available. " + e);
            throw new DaoException("PrepareStatement didn't connection or createUser method is not available. " + e);
        }
    }

    @Override
    public boolean deleteUserById(long id) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(SQL_DELETE_USER)) {
            statement.setLong(1, id);
           return statement.executeUpdate() != 0;
        } catch (SQLException e) {
            logger.error("PrepareStatement didn't connection or deleteUserById method is not available. " + e);
            throw new DaoException("PrepareStatement didn't connection or deleteUserById method is not available. " + e);
        }
    }

    @Override
    public boolean updateUserById(User user) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_PASSWORD_NAME_BY_ID)) {
            statement.setString(1, user.getPassword());
            statement.setString(2, user.getName());
            statement.setLong(3, user.getId());
           return statement.executeUpdate() != 0;
        } catch (SQLException e) {
            logger.error("PrepareStatement didn't connection or updateUserById method is not available. " + e);
            throw new DaoException("PrepareStatement didn't connection or updateUserById method is not available. " + e);
        }
    }

    @Override
    public boolean updateCashById(Double cash, long id) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_CASH_BY_ID)) {
            statement.setDouble(1, cash);
            statement.setLong(2, id);
           return statement.executeUpdate() != 0;
        } catch (SQLException e) {
            logger.error("PrepareStatement didn't connection or updateCashById method is not available. " + e);
            throw new DaoException("PrepareStatement didn't connection or updateCashById method is not available. " + e);
        }
    }

    @Override
    public boolean reduceCashById(Double cash, long id, Connection connection) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_REDUCE_CASH_BY_ID)) {
            statement.setDouble(1, cash);
            statement.setLong(2, id);
            return statement.executeUpdate() != 0;
        } catch (SQLException e) {
            logger.error("PrepareStatement didn't connection or reduceCashById method is not available. " + e);
            throw new DaoException("PrepareStatement didn't connection or reduceCashById method is not available. " + e);
        }
    }

    @Override
    public boolean changeUserStatusOnInRegister(long userId) throws DaoException {
        return setUserStatus(SQL_SET_IN_REGISTER_USER_BY_USER_ID, userId);
    }

    @Override
    public boolean changeUserStatusOnActive(long userId) throws DaoException {
        return setUserStatus(SQL_SET_ACTIVE_USER_BY_USER_ID, userId);
    }

    private boolean setUserStatus(String scrypt, long userId) throws DaoException{
       try(Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(scrypt)) {
            statement.setLong(1, userId);
           return statement.executeUpdate() != 0;
       } catch (SQLException e) {
           logger.error("PrepareStatement didn't connection or setUserStatus method is not available. " + e);
           throw new DaoException("PrepareStatement didn't connection or setUserStatus method is not available. " + e);
       }
    }

    @Override
    public List<User> findAllDeleteUser() throws DaoException {
        return findAllUserByScript(SQL_FIND_ALL_DELETE_USER);
    }

    @Override
    public List<User> findAllNonDeleteUser() throws DaoException {
        return findAllUserByScript(SQL_FIND_ALL_NON_DELETE_USER);
    }

    private List<User> findAllUserByScript(String scrypt) throws DaoException {
        List<User> userList = new ArrayList<>();
        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(scrypt);
            ResultSet result = statement.executeQuery()){
                while (result.next()) {
                    userList.add(new User.UserBuilder()
                            .setId(result.getLong(USER_ID))
                            .setPosition((result.getString(USER_POSITION)))
                            .setName(result.getString(USER_NAME))
                            .setCash(result.getDouble(USER_CASH))
                            .setLogin(result.getString(USER_LOGIN))
                            .setUserStatus(result.getString(USER_STATUS))
                            .setPhoto(result.getString(USER_PHOTO))
                            .createUser());
                }
        }catch (SQLException e) {
            logger.error("PrepareStatement didn't connection or findAllUserByScript method is not available. " + e);
            throw new DaoException("PrepareStatement didn't connection or findAllUserByScript method is not available. " + e);
        }
        return userList;
    }

    @Override
    public List<User> searchDeleteUserByName(String name) throws DaoException {
        return searchUserByScript(SQL_SEARCH_DELETE_USER_BY_NAME, name);
    }

    @Override
    public List<User> searchNonDeleteUserByName(String name) throws DaoException {
        return searchUserByScript(SQL_SEARCH_NON_DELETE_USER_BY_NAME, name);
    }

    private List<User> searchUserByScript(String scrypt, String name) throws DaoException {
        List<User> userList = new ArrayList<>();
        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(scrypt)){
            statement.setString(1, "%" + name + "%");
            try(ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    userList.add(new User.UserBuilder()
                            .setId(result.getLong(USER_ID))
                            .setPosition((result.getString(USER_POSITION)))
                            .setName(result.getString(USER_NAME))
                            .setCash(result.getDouble(USER_CASH))
                            .setLogin(result.getString(USER_LOGIN))
                            .setUserStatus(result.getString(USER_STATUS))
                            .setPhoto(result.getString(USER_PHOTO))
                            .createUser());
                }
            }
        }catch (SQLException e) {
            logger.error("PrepareStatement didn't connection or searchUserByScript method is not available. " + e);
            throw new DaoException("PrepareStatement didn't connection or searchUserByScript method is not available. " + e);
        }
        return userList;
    }

    @Override
    public Optional<User> checkAuthorisation(String login, String password) throws DaoException {
        try(Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(SQL_CHECK_AUTHORISATION)) {
            statement.setString(1, login);
            statement.setString(2, password);
            try (ResultSet result = statement.executeQuery()){
                if (result.next()){
                    return Optional.ofNullable(new User.UserBuilder()
                            .setId(result.getInt(USER_ID))
                            .setName(result.getString(ColumnName.USER_NAME))
                            .setCash(result.getDouble(ColumnName.USER_CASH))
                            .setLogin(result.getString(ColumnName.USER_LOGIN))
                            .setPassword(result.getString(ColumnName.USER_PASSWORD))
                            .setPosition((result.getString(ColumnName.USER_POSITION)))
                            .setUserStatus(result.getString(USER_STATUS))
                            .setPhoto(result.getString(USER_PHOTO))
                            .createUser());
                }
            }
        } catch (SQLException e) {
            logger.error("PrepareStatement didn't connection or checkAuthorisation method is not available. " + e);
            throw new DaoException("PrepareStatement didn't connection or checkAuthorisation method is not available. " + e);
        }
        return Optional.empty();
    }

    @Override
    public boolean updatePhotoById(String path, long id) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_PHOTO_BY_ID)) {
            statement.setString(1, path);
            statement.setLong(2, id);
          return statement.executeUpdate() != 0;
        } catch (SQLException e) {
            logger.error("PrepareStatement didn't connection or updatePhotoById method is not available. " + e);
            throw new DaoException("PrepareStatement didn't connection or updatePhotoById method is not available. " + e);
        }
    }

    @Override
    public boolean checkIsAdmin() throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_CHECK_IS_ADMIN);
             ResultSet result = statement.executeQuery()) {
           return result.next();
        } catch (SQLException e) {
            logger.error("PrepareStatement didn't connection or checkIsAdmin method is not available. " + e);
            throw new DaoException("PrepareStatement didn't connection or checkIsAdmin method is not available. " + e);
        }
    }

    @Override
    public double findUserCashById(long userId) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_USER_CASH_BY_USER_ID)) {
            statement.setLong(1, userId);
            try (ResultSet result = statement.executeQuery()){
               if (result.next()){
                   return result.getDouble(USER_CASH);
               }
            }
        } catch (SQLException e) {
            logger.error("PrepareStatement didn't connection or findUserCashById method is not available. " + e);
            throw new DaoException("PrepareStatement didn't connection or findUserCashById method is not available. " + e);
        }
        return 0;
    }

}
