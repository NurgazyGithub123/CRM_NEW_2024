
import dao.CourseDao;
import dao.GroupDao;
import dao.ManagerDao;
import dao.daoUtil.DaoFactory;
import dao.impl.CourseDaoImpl;
import model.Course;
import model.Group;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ManagerDao managerDao = (ManagerDao) DaoFactory.autowired("ManagerDao","singleton");
    }
}