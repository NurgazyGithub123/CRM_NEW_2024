package dao;

import model.Manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface CrudDao <Model>{

    Model save(Model model);
    Optional<Model> findByid(Long id);

    List<Model> findAll();

    List<Model> saveAll(List<Model> models);


    default Connection getConnection() throws SQLException {
        final String URL = "jdbc:postgresql://localhost:5432/crm";
        final String USER = "postgres";
        final String PASSWORD = "1234";

        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    default void close(AutoCloseable autoCloseable){
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
