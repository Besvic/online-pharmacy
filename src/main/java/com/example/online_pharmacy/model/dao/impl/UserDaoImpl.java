package com.example.online_pharmacy.model.dao.impl;

import com.example.online_pharmacy.entity.User;
import com.example.online_pharmacy.exception.DaoException;
import com.example.online_pharmacy.exception.DatabaseException;
import com.example.online_pharmacy.model.dao.UserDao;
import com.example.online_pharmacy.model.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class UserDaoImpl implements UserDao {

    private static final Logger logger = LogManager.getLogger();

    private static final String SQL_CREATE_USER = """
            insert into users (user_position, user_name, user_cash, user_login, user_password, user_status) +
            values (?, ?, ?, ?, ?, ?);""";

    private static final String SQL_DELETE_USER = """
            update users
            set user_position = 'delete'
            where user_id = ?;""";

    @Override
    public int createUser(User user) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_CREATE_USER)){
            statement.setString(1, String.valueOf(user.getPosition()));
            statement.setString(2, user.getName());
            statement.setDouble(3, user.getCash());
            statement.setString(4, user.getLogin());
            statement.setString(5, user.getPassword());
            statement.setString(6, user.getStatus().toString());
            return statement.executeUpdate();
        } catch (SQLException throwables) {
            logger.error("PrepareStatement didn't connection or unknown field found." + throwables);
            new DaoException("PrepareStatement didn't connection or unknown field found.", throwables);
        }
        return 0;
    }

    @Override
    public int deleteUserById(int id) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(SQL_DELETE_USER)) {
            statement.setInt(1, id);
            return statement.executeUpdate();
        } catch (SQLException throwables) {
            logger.error("PrepareStatement didn't connection or unknown field found." + throwables);
            new DaoException("PrepareStatement didn't connection or unknown field found.", throwables);
        }
        return 0;
    }

    @Override
    public List<User> findUserByPosition(String position) throws DaoException {
        return null;
    }

    @Override
    public boolean checkAuthorisation(String login, String password) throws DaoException {
        return false;
    }
}
