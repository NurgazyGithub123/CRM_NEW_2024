package dao.impl;

import dao.ManagerDao;
import model.Manager;

import java.sql.*;

public class ManagerDaoImpl implements ManagerDao {

    public ManagerDaoImpl() {

        Connection connection = null;
        Statement statement = null;

        try {
            System.out.println("Connection to database...");
            connection = getConnection();
            System.out.println("Connecting succeeted");

            String ddlQuery = "CREATE TABLE tb_managers(" +
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
        return null;
    }

    private void close(AutoCloseable autoCloseable){
        try {
            System.out.println(autoCloseable.getClass().getSimpleName() + "closing...");
            autoCloseable.close();
            System.out.println(autoCloseable.getClass().getSimpleName() + "closed");
        } catch (Exception e) {
            System.out.println("Could not close " + autoCloseable.getClass().getSimpleName());
            e.printStackTrace();
        }
    }
}
