package dao.impl;

import dao.ManagerDao;
import dao.daoUtil.Log;
import model.Manager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ManagerDaoImpl implements ManagerDao {

    public ManagerDaoImpl() {

        Connection connection = null;
        Statement statement = null;

        try {
            System.out.println("Connection to database...");
            connection = getConnection();
            System.out.println("Connecting succeeted");

            String ddlQuery = "CREATE TABLE IF NOT EXISTS tb_managers(" +
                    "id           BIGSERIAL, " +
                    "first_name   VARCHAR(50) NOT NULL, " +
                    "last_name    VARCHAR(50) NOT NULL, " +
                    "email        VARCHAR(50) NOT NULL UNIQUE, " +
                    "phone_number CHAR(13)    NOT NULL, " +
                    "dob          DATE   CHECK ( dob < NOW()), " +
                    "salary       MONEY       NOT NULL, " +
                    "date_created TIMESTAMP   NOT NULL DEFAULT NOW(), " +
                    "" +
                    "CONSTRAINT pk_managers_id PRIMARY KEY(id) " +
                    ")";

            statement = connection.createStatement();
            statement.execute(ddlQuery);

        } catch (SQLException e) {
            Log.error(this.getClass().getSimpleName(),e.getStackTrace()[0].getClass().getSimpleName(),e.getMessage());
            e.printStackTrace();
        }finally {
            close(statement);
            close(connection);
        }
    }

    public Manager save(Manager manager){

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Manager savedManager = null;

        try {
            System.out.println("Connection to database...");
            connection = getConnection();
            System.out.println("Connecting succeeted");


            String createQuery = "INSERT INTO tb_managers(first_name, last_name, email, phone_number, dob, salary, date_created) " +
                    "VALUES(?, ?, ?, ?, ?,MONEY(?), ?)";

            String readQuery = "SELECT * FROM tb_managers ORDER BY ID DESC LIMIT 1";

            preparedStatement = connection.prepareStatement(createQuery);
            preparedStatement.setString(1, manager.getFirstName());
            preparedStatement.setString(2, manager.getLastName());
            preparedStatement.setString(3, manager.getEmail());
            preparedStatement.setString(4, manager.getPhoneNumber());
            preparedStatement.setTimestamp(7,Timestamp.valueOf(manager.getDateCreated()));
            preparedStatement.setDate(5,Date.valueOf(manager.getDob()));
            preparedStatement.setString(6, manager.getSalary() + "");

            preparedStatement.execute();
            close(preparedStatement);

            preparedStatement = connection.prepareStatement(readQuery);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();

            savedManager = new Manager();
            savedManager.setId(resultSet.getLong("id"));
            savedManager.setFirstName(resultSet.getString("first_name"));
            savedManager.setLastName(resultSet.getString("last_name"));
            savedManager.setEmail(resultSet.getString("email"));
            savedManager.setPhoneNumber(resultSet.getString("phone_number"));
            savedManager.setDob(resultSet.getDate("dob").toLocalDate());
            savedManager.setSalary(Double.valueOf(resultSet.getString("salary").replaceAll("[^\\d\\.]+", "")));
            savedManager.setDateCreated(resultSet.getTimestamp("date_created").toLocalDateTime());


        } catch (SQLException e) {
            Log.error(this.getClass().getSimpleName(),e.getStackTrace()[0].getClass().getSimpleName(),e.getMessage());
            throw new RuntimeException(e);
        }finally {
            close(resultSet);
            close(preparedStatement);
            close(connection);
        }
        return savedManager;
    }

    @Override
    public Manager findByid(Long id) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Manager savedManager = null;

        try {
            System.out.println("Connection to database...");
            connection = getConnection();
            System.out.println("Connecting succeted");

            String readQuery = "SELECT * FROM tb_managers WHERE id = ?";


            preparedStatement = connection.prepareStatement(readQuery);
            preparedStatement.setLong(1,id);

            resultSet = preparedStatement.executeQuery();
            resultSet.next();

            savedManager = new Manager();
            savedManager.setId(resultSet.getLong("id"));
            savedManager.setFirstName(resultSet.getString("first_name"));
            savedManager.setLastName(resultSet.getString("last_name"));
            savedManager.setEmail(resultSet.getString("email"));
            savedManager.setPhoneNumber(resultSet.getString("phone_number"));
            savedManager.setDob(resultSet.getDate("dob").toLocalDate());
            savedManager.setSalary(Double.valueOf(resultSet.getString("salary").replaceAll("[^\\d\\.]+", "")));
            savedManager.setDateCreated(resultSet.getTimestamp("date_created").toLocalDateTime());


        } catch (SQLException e) {
            Log.error(this.getClass().getSimpleName(),e.getStackTrace()[0].getClass().getSimpleName(),e.getMessage());
            e.printStackTrace();
        }finally {
            close(resultSet);
            close(preparedStatement);
            close(connection);
        }
        return savedManager;
    }

    @Override
    public List<Manager> findAll() {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Manager> managers = new ArrayList<>();

        try {
            Log.error(this.getClass().getSimpleName() + "findAll", Connection.class.getSimpleName(), "Connecting to DataBase");
            connection = getConnection();

            String readQuery = "SELECT * FROM tb_managers";

            preparedStatement = connection.prepareStatement(readQuery);
            resultSet = preparedStatement.executeQuery();



            for (int i = 0; i <= managers.size() && resultSet.next() ; i++) {
            Manager manager = new Manager();
            manager.setId(resultSet.getLong("id"));
            manager.setFirstName(resultSet.getString("first_name"));
            manager.setLastName(resultSet.getString("last_name"));
            manager.setEmail(resultSet.getString("email"));
            manager.setPhoneNumber(resultSet.getString("phone_number"));
            manager.setDob(resultSet.getDate("dob").toLocalDate());
            manager.setSalary(Double.valueOf(resultSet.getString("salary").replaceAll("[^\\d\\.]+", "")));
            manager.setDateCreated(resultSet.getTimestamp("date_created").toLocalDateTime());

            managers.add(manager);
            }
            return managers;

        } catch (SQLException e) {
            Log.error(this.getClass().getSimpleName(),e.getStackTrace()[0].getClass().getSimpleName(),e.getMessage());
            e.printStackTrace();
        }finally {
            close(resultSet);
            close(preparedStatement);
            close(connection);
        }
        return null;
    }


}
