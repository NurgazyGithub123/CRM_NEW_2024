package dao;

import model.Manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public interface CrudDao <Model>{

    Model save(Model model);
    Model findByid(Long id);
    default Connection getConnection() throws SQLException {
        final String URL = "jdbc:postgresql://localhost:5432/crm";
        final String USER = "postgres";
        final String PASSWORD = "1234";

        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

}
