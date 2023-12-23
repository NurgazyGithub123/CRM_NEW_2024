package dao.impl;

import dao.MentorDao;
import dao.daoUtil.Log;
import model.Mentor;

import java.sql.*;

public class MentorDaoImpl implements MentorDao {

    public MentorDaoImpl(){

        Connection connection = null;
        Statement statement = null;

        try {

            System.out.println("Connection to database...");
            connection = getConnection();
            System.out.println("Connecting succeeted");

            String ddlQuery = "CREATE TABLE tb_mentors(" +
                    "id BIGSERIAL, " +
                    "first_name     VARCHAR(50) NOT NULL, " +
                    "last_name      VARCHAR(50) NOT NULL, " +
                    "email          VARCHAR(50) NOT NULL, " +
                    "phone_number    CHAR(13)    NOT NULL, " +
                    "dob            DATE        NOT NULL, " +
                    "salary         MONEY       NOT NULL, " +
                    "date_created   TIMESTAMP   NOT NULL DEFAULT NOW(), " +
                    "" +
                    "CONSTRAINT pk_mentor_id PRIMARY KEY(id), " +
                    "CONSTRAINT chk_mentor_unique UNIQUE(email)" +
                    ")";

            statement = connection.createStatement();
            statement.execute(ddlQuery);

        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            close(statement);
            close(connection);
        }
    }
    @Override
    public Mentor save(Mentor mentor) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Mentor saveMentor = null;

        try {
            System.out.println("Connection to database...");
            connection = getConnection();
            System.out.println("Connecting succeted");

            String createQuery = "INSERT INTO tb_mentors(first_name, last_name, email, phone_number, dob, salary, date_created) " +
                    "VALUES (?, ?, ?, ?, ?, MONEY(?), ?)";

            String readQuery = "SELECT * FROM tb_mentors ORDER BY id DESC LIMIT 1";

            preparedStatement = connection.prepareStatement(createQuery);
            preparedStatement.setString(1, mentor.getFirstName());
            preparedStatement.setString(2, mentor.getLastName());
            preparedStatement.setString(3, mentor.getEmail());
            preparedStatement.setString(4, mentor.getPhoneNumber());
            preparedStatement.setDate(5, Date.valueOf(mentor.getDob()));
            preparedStatement.setString(6, mentor.getSalary() + "");
            preparedStatement.setTimestamp(7, Timestamp.valueOf(mentor.getDateCreated()));

            preparedStatement.execute();
            close(preparedStatement);

            preparedStatement = connection.prepareStatement(readQuery);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();

            saveMentor = new Mentor();
            saveMentor.setId(resultSet.getLong("id"));
            saveMentor.setFirstName(resultSet.getString("first_name"));
            saveMentor.setLastName(resultSet.getString("last_name"));
            saveMentor.setEmail(resultSet.getString("email"));
            saveMentor.setPhoneNumber(resultSet.getString("phone_number"));
            saveMentor.setDob(resultSet.getDate("dob").toLocalDate());
            saveMentor.setSalary(Double.valueOf(resultSet.getString("salary").replaceAll("[^\\d\\.]+", "")));
            saveMentor.setDateCreated(resultSet.getTimestamp("date_created").toLocalDateTime());


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(resultSet);
            close(preparedStatement);
            close(connection);
        }
        return saveMentor;
    }

    @Override
    public Mentor findByid(Long id) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Mentor savedMentor = null;

        try {
            System.out.println("Connection to database...");
            connection = getConnection();
            System.out.println("Connecting succeted");

            String readQuery = "SELECT * FROM tb_mentors WHERE id = ?";


            preparedStatement = connection.prepareStatement(readQuery);
            preparedStatement.setLong(1,id);

            resultSet = preparedStatement.executeQuery();
            resultSet.next();

            savedMentor = new Mentor();
            savedMentor.setId(resultSet.getLong("id"));
            savedMentor.setFirstName(resultSet.getString("first_name"));
            savedMentor.setLastName(resultSet.getString("last_name"));
            savedMentor.setEmail(resultSet.getString("email"));
            savedMentor.setPhoneNumber(resultSet.getString("phone_number"));
            savedMentor.setDob(resultSet.getDate("dob").toLocalDate());
            savedMentor.setSalary(Double.valueOf(resultSet.getString("salary").replaceAll("[^\\d\\.]+", "")));
            savedMentor.setDateCreated(resultSet.getTimestamp("date_created").toLocalDateTime());


        } catch (SQLException e) {
            Log.error(this.getClass().getSimpleName(),e.getStackTrace()[0].getClass().getSimpleName(),e.getMessage());
            e.printStackTrace();
        }finally {
            close(resultSet);
            close(preparedStatement);
            close(connection);
        }
        return savedMentor;

    }

}
