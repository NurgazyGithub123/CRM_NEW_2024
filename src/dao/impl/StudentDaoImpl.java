package dao.impl;

import dao.StudentDao;
import dao.daoUtil.Log;
import model.Student;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

public class StudentDaoImpl implements StudentDao {

    public StudentDaoImpl(){
        Connection connection = null;
        Statement statement = null;

        try {
            Log.info(this.getClass().getSimpleName(),Connection.class.getSimpleName(), "Connecting to DATABASE");
            connection = getConnection();
            Log.info(this.getClass().getSimpleName(),Connection.class.getSimpleName(), "Connecting succeeted");

            String ddlQuery = "CREATE TABLE tb_students(" +
                    "id BIGSERIAL, " +
                    "first_name     VARCHAR(50) NOT NULL, " +
                    "last_name      VARCHAR(50) NOT NULL, " +
                    "email          VARCHAR(50) NOT NULL, " +
                    "phone_number    CHAR(13)    NOT NULL, " +
                    "dob            DATE        NOT NULL, " +
                    "group_id       BIGINT       NOT NULL, " +
                    "date_created   TIMESTAMP   NOT NULL DEFAULT NOW(), " +
                    "" +
                    "CONSTRAINT pk_student_id PRIMARY KEY(id), " +
                    "CONSTRAINT chk_student_unique UNIQUE(email), " +
                    "CONSTRAINT fk_group_id FOREIGN KEY(group_id) " +
                    "  REFERENCES tb_groups(id) " +
                    ")";

            statement = connection.createStatement();
            statement.execute(ddlQuery);

        } catch (SQLException e) {
            Log.error(this.getClass().getSimpleName(),e.getStackTrace()[0].getClass().getSimpleName(),e.getMessage());
            throw new RuntimeException(e);
        }finally {
            close(statement);
            close(connection);
        }
    }

    @Override
    public Student save(Student student) {
        return null;
    }

    @Override
    public Optional<Student> findByid(Long id) {
        return null;
    }

    @Override
    public List<Student> findAll() {
        return null;
    }

    @Override
    public List<Student> saveAll(List<Student> students) {
        return null;
    }
}
