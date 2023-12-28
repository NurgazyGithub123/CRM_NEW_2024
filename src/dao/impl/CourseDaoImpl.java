package dao.impl;

import dao.CourseDao;
import dao.daoUtil.Log;
import model.Address;
import model.Course;
import model.Course;

import java.sql.*;
import java.util.List;

public class CourseDaoImpl implements CourseDao {

    public CourseDaoImpl(){
        Connection connection = null;
        Statement statement = null;


        try {
            Log.info(this.getClass().getSimpleName(),Connection.class.getSimpleName(), "Connecting to DATABASE");
            connection = getConnection();
            Log.info(this.getClass().getSimpleName(),Connection.class.getSimpleName(), "Connecting succeeted");

            String createQuery = "CREATE TABLE tb_course(" +
                    "id BIGSERIAL," +
                    "course_name      VARCHAR(50)  NOT NULL, " +
                    "duration_course  INTEGER      NOT NULL, " +
                    "type_course      VARCHAR(50)  NOT NULL, " +
                    "price_course     MONEY        NOT NULL, " +
                    "date_created   TIMESTAMP   NOT NULL DEFAULT NOW(), " +
                    "" +
                    "CONSTRAINT pk_course_id PRIMARY KEY(id)" +
                    ")";

            statement = connection.createStatement();
            statement.execute(createQuery);

        } catch (SQLException e) {
            Log.error(this.getClass().getSimpleName(),e.getStackTrace()[0].getClass().getSimpleName(),e.getMessage());
            e.printStackTrace();
        }finally {
            close(connection);
            close(statement);
        }
    }

    @Override
    public Course save(Course course){

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Course savedCourse = null;

        try {
            System.out.println("Connection to database...");
            connection = getConnection();
            System.out.println("Connecting succeeted");


            String createQuery = "INSERT INTO tb_course(course_name, duration_course, type_course, price_course, date_created) " +
                    "VALUES(?, ?, ?,MONEY(?), ?)";

            String readQuery = "SELECT * FROM tb_course ORDER BY ID DESC LIMIT 1";

            preparedStatement = connection.prepareStatement(createQuery);
            preparedStatement.setString(1, course.getCourseName());
            preparedStatement.setInt(2, course.getDurationCourse());
            preparedStatement.setString(3, course.getTypeCourse());
            preparedStatement.setTimestamp(5,Timestamp.valueOf(course.getDateCreated()));
            preparedStatement.setString(4, course.getPriceCourse() + "");

            preparedStatement.execute();
            close(preparedStatement);

            preparedStatement = connection.prepareStatement(readQuery);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();

            savedCourse = new Course();
            savedCourse.setId(resultSet.getLong("id"));
            savedCourse.setCourseName(resultSet.getString("course_name"));
            savedCourse.setDurationCourse(resultSet.getInt("duration_course"));
            savedCourse.setTypeCourse(resultSet.getString("type_course"));
            savedCourse.setPriceCourse(Double.valueOf(resultSet.getString("price_course").replaceAll("[^\\d\\.]+", "")));
            savedCourse.setDateCreated(resultSet.getTimestamp("date_created").toLocalDateTime());


        } catch (SQLException e) {
            Log.error(this.getClass().getSimpleName(),e.getStackTrace()[0].getClass().getSimpleName(),e.getMessage());
            throw new RuntimeException(e);
        }finally {
            close(resultSet);
            close(preparedStatement);
            close(connection);
        }
        return savedCourse;
    }

    @Override
    public Course findByid(Long id) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Course savedCourse = null;

        try {
            Log.info(this.getClass().getSimpleName(),Connection.class.getSimpleName(), "Connecting to DATABASE");
            connection = getConnection();
            Log.info(this.getClass().getSimpleName(),Connection.class.getSimpleName(), "Connecting succeeted");

            String readQuery = "SELECT * FROM tb_course WHERE id = ?";


            preparedStatement = connection.prepareStatement(readQuery);
            preparedStatement.setLong(1,id);

            resultSet = preparedStatement.executeQuery();
            close(preparedStatement);
            resultSet.next();

            savedCourse = new Course();
            savedCourse.setId(resultSet.getLong("id"));
            savedCourse.setCourseName(resultSet.getString("course_name"));
            savedCourse.setDurationCourse(resultSet.getInt("duration_course"));
            savedCourse.setTypeCourse(resultSet.getString("type_course"));
            savedCourse.setPriceCourse(Double.valueOf(resultSet.getString("price_course").replaceAll("[^\\d\\.]+", "")));
            savedCourse.setDateCreated(resultSet.getTimestamp("date_created").toLocalDateTime());


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(resultSet);
            close(preparedStatement);
            close(connection);

        }
        return savedCourse;
    }

    @Override
    public List<Course> findAll() {
        return null;
    }

}
