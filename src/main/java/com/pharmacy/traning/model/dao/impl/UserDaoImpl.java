package com.pharmacy.traning.model.dao.impl;

import com.pharmacy.traning.model.entity.Position;
import com.pharmacy.traning.model.entity.User;
import com.pharmacy.traning.model.entity.UserStatus;
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

public class UserDaoImpl implements UserDao {

    private static final Logger logger = LogManager.getLogger();
    private static final UserDaoImpl instance = new UserDaoImpl();

    public static UserDaoImpl getInstance(){
        return instance;
    }

    private UserDaoImpl(){

    }

    private static final String SQL_CREATE_USER = """
            insert into users (user_position, user_name, user_cash, user_login, user_password, user_status)
            values (?, ?, ?, ?, ?, ?);""";

    private static final String SQL_DELETE_USER = """
            update users
            set user_position = 'delete'
            where user_id = ?;""";

    private static final String SQL_FIND_USER_BY_STATUS = """
            select user_id, user_position, user_name, user_cash, user_login, user_password, user_status, user_photo
            from users
            where user_status = ?;""";

    private static final String SQL_CHECK_AUTHORISATION = """
            select user_id, user_position, user_name, user_cash, user_login, user_password, user_status, user_photo
            from users
            where user_login = ? and user_password = ?;""";

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
            if (statement.executeUpdate() != 0)
                return true;
        } catch (SQLException throwables) {
            logger.error("PrepareStatement didn't connection or this function is not available." + throwables);
            new DaoException("PrepareStatement didn't connection or this function is not available.", throwables);
        }
        return false;
    }

    @Override
    public boolean deleteUserById(int id) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(SQL_DELETE_USER)) {
            statement.setInt(1, id);
            if (statement.executeUpdate() != 0)
                return true;
        } catch (SQLException throwables) {
            logger.error("PrepareStatement didn't connection or this function is not available." + throwables);
            new DaoException("PrepareStatement didn't connection or this function is not available.", throwables);
        }
        return false;
    }

    @Override
    public List<User> findUserByStatus(String status) throws DaoException {
        List<User> userList = new ArrayList<>();
        try(Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(SQL_FIND_USER_BY_STATUS)) {
            statement.setString(1, status);
            try(ResultSet result = statement.executeQuery()){
                while (result.next()){
                    userList.add(new User.UserBuilder()
                            .setId(result.getInt(ColumnName.USER_ID))
                            .setPosition(Position.valueOf(result.getString(ColumnName.USER_POSITION)))
                            .setName(result.getString(ColumnName.USER_NAME))
                            .setCash(result.getDouble(ColumnName.USER_CASH))
                            .setLogin(result.getString(ColumnName.USER_LOGIN))
                            .setPassword(result.getString(ColumnName.USER_PASSWORD))
                            .setUserStatus(UserStatus.valueOf(result.getString(ColumnName.USER_STATUS)))
                            .setPhoto(result.getBlob(ColumnName.USER_PHOTO))
                            .createUser());
                }
            }
        } catch (SQLException throwables) {
            logger.error("PrepareStatement didn't connection or this function is not available." + throwables);
            new DaoException("PrepareStatement didn't connection or this function is not available.", throwables);
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
                    return Optional.of(new User.UserBuilder()
                            .setId(result.getInt(ColumnName.USER_ID))
                            .setName(result.getString(ColumnName.USER_NAME))
                            .setCash(result.getDouble(ColumnName.USER_CASH))
                            .setLogin(result.getString(ColumnName.USER_LOGIN))
                            .setPassword(result.getString(ColumnName.USER_PASSWORD))
                            .setPosition(Position.valueOf(result.getString(ColumnName.USER_POSITION)))
                            .setUserStatus(UserStatus.valueOf(result.getString(ColumnName.USER_STATUS)))
                            .setPhoto(result.getBlob(ColumnName.USER_PHOTO))
                            .createUser());
                }
            }
        } catch (SQLException throwables) {
            logger.error("PrepareStatement didn't connection or this function is not available." + throwables);
            new DaoException("PrepareStatement didn't connection or this function is not available.", throwables);
        }
        return Optional.empty();
    }

}
