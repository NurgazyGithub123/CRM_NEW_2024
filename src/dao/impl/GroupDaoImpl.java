package dao.impl;

import dao.GroupDao;
import dao.daoUtil.Log;
import model.Course;
import model.Group;


import java.sql.*;

public class GroupDaoImpl implements GroupDao {

    public GroupDaoImpl(){

        Connection connection = null;
        Statement statement = null;

        try {
            System.out.println("Connection to database...");
            connection = getConnection();
            System.out.println("Connecting succeeted");

            String ddlQuery = "CREATE TABLE IF NOT EXISTS tb_groups(" +
                    "id   BIGSERIAL, " +
                    "group_name    VARCHAR(50)  NOT NULL, " +
                    "group_time    INT NOT NULL, " +
                    "course_id     BIGINT   NOT NULL, " +
                    "date_created   TIMESTAMP   NOT NULL DEFAULT NOW(), " +
                    "" +
                    "CONSTRAINT pk_group_id PRIMARY KEY(id), " +
                    "CONSTRAINT fk_course_id FOREIGN KEY(course_id) " +
                    "   REFERENCES tb_course(id) " +
                    ")";

            statement = connection.createStatement();
            statement.execute(ddlQuery);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(statement);
            close(connection);
        }
    }
    @Override
    public Group save(Group group) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Group saveGroup = null;

        try {
            System.out.println("Connection to database...");
            connection = getConnection();
            System.out.println("Connecting succeeted");

            String createQuery = "INSERT INTO tb_groups(group_name, group_time, course_id, date_created)" +
                    "VALUES (?, ?, ?, ?)";

            String readQuery = "SELECT g.id AS groupID, g.group_name, g.group_time,g.date_created AS GroupDateCreated, s.* " +
                    "FROM tb_groups AS g " +
                    "JOIN tb_course AS s " +
                    "ON g.course_id = s.id " +
                    "ORDER BY g.id DESC LIMIT 1";

            preparedStatement = connection.prepareStatement(createQuery);
            preparedStatement.setString(1, group.getName());
            preparedStatement.setInt(2, group.getGroupTime());
            preparedStatement.setLong(3,group.getCourse().getId());
            preparedStatement.setTimestamp(4,Timestamp.valueOf(group.getDateCreated()));

            preparedStatement.execute();
            close(preparedStatement);

            preparedStatement = connection.prepareStatement(readQuery);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();

            Course course = new Course();
            course = new Course();

            course.setId(resultSet.getLong("id"));
            course.setCourseName(resultSet.getString("course_name"));
            course.setDurationCourse(resultSet.getInt("duration_course"));
            course.setTypeCourse(resultSet.getString("type_course"));
            course.setPriceCourse(Double.valueOf(resultSet.getString("price_course").replaceAll("[^\\d\\.]+", "")));
            course.setDateCreated(resultSet.getTimestamp("date_created").toLocalDateTime());

            saveGroup = new Group();
            saveGroup.setId(resultSet.getLong("GroupID"));
            saveGroup.setName(resultSet.getString("group_name"));
            saveGroup.setGroupTime(resultSet.getInt("group_time"));
            saveGroup.setDateCreated(resultSet.getTimestamp("GroupDateCreated").toLocalDateTime());
            saveGroup.setCourse(course);


            saveGroup.setCourse(course);

        } catch (SQLException e) {
            Log.error(getClass().getSimpleName(), e.getStackTrace()[0].getClass().getSimpleName(),e.getMessage());
            throw new RuntimeException(e);
        }finally {
            close(resultSet);
            close(preparedStatement);
            close(connection);
        }
        return saveGroup;
    }

    @Override
    public Group findByid(Long id) {
        return null;
    }
}
