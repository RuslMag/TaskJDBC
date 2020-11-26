package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl extends Util implements UserDao {
    Connection connection = getConnection();

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        String SQL_CREATE_TABLE = "CREATE TABLE IF NOT EXISTS USER"
                + "  (id           INT NOT NULL PRIMARY KEY AUTO_INCREMENT,"
                + "   name         VARCHAR(45) NOT NULL,"
                + "   lastName     VARCHAR(45) NOT NULL,"
                + "   age          INT NOT NULL)";

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(SQL_CREATE_TABLE);
            System.out.println("Таблица успешно создана!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        String SQL_DROP_TABLE = "DROP TABLE IF EXISTS USER";

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(SQL_DROP_TABLE);
            System.out.println("Таблица успешно удалена!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String SQL_SAVE_USER = "INSERT INTO USER (NAME, LASTNAME, AGE) VALUES(?, ?, ?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SAVE_USER);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.println("User с именем - " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        String SQL_DELETE_USER = "DELETE FROM USER WHERE ID=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_USER)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        String SQL_GET_USERS = "SELECT NAME, LASTNAME, AGE FROM USER";

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SQL_GET_USERS);

            while (resultSet.next()) {
                User user = new User();
                user.setName(resultSet.getString("NAME"));
                user.setLastName(resultSet.getString("LASTNAME"));
                user.setAge(resultSet.getByte("AGE"));

                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public void cleanUsersTable() {
        String SQL_TRUNCATE_USER = "TRUNCATE TABLE USER";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(SQL_TRUNCATE_USER);
            System.out.println("Таблица успешно очищена!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
