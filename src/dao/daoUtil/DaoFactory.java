package dao.daoUtil;

import dao.*;
import dao.impl.*;
import model.Student;

public abstract class DaoFactory {

    static {
        try {
            System.out.println("Driver loading...");
            Class.forName("org.postgresql.Driver");
            System.out.println("Driver loaded");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static CrudDao<?> autowired(String qualifier, String scope){
        if(!scope.equals("singleton") && !scope.equals("prototype")){
            throw new RuntimeException("Invalid scope jf beans" + scope);
        }
        switch (qualifier){
            case "ManagerDao":
                return getManagerDaoSQL(scope);
            case "MentorDao":
                return getMentorDaoSQL(scope);
            case "CourseDao":
                return getCourseDaoSQL(scope);
            case "AddressDao":
                return getAddressDaoSQL(scope);
            case "StudentDao":
                return getStudentDaoSQL(scope);
            case "GroupDao":
                return getGroupDaoSQL(scope);
            default:
                throw  new RuntimeException("Can't find bean for autowired: " + qualifier);
        }
    }

    private static ManagerDao managerDao;
    private static MentorDao mentorDao;
    private static CourseDao courseDao;
    private static AddressDao addressDao;
    private static StudentDao studentDao;
    private static GroupDao groupDao;
    private static ManagerDao getManagerDaoSQL(String scope){
        if(scope.equals("prototype")) {
            return new ManagerDaoImpl();
        }

        if(managerDao == null){
            managerDao = new ManagerDaoImpl();
        }
        return managerDao;
    }

    private static MentorDao getMentorDaoSQL(String scope){
        if(scope.equals("prototype")) {
            return new MentorDaoImpl();
        }

        if(mentorDao == null){
            mentorDao = new MentorDaoImpl();
        }
        return mentorDao;
    }

    private static CourseDao getCourseDaoSQL(String scope){
        if(scope.equals("prototype")){
            return new CourseDaoImpl();
        }
        if(courseDao == null) {
            return new CourseDaoImpl();
        }
        return courseDao;
    }

    private static AddressDao getAddressDaoSQL(String scope){
        if(scope.equals("prototype")){
            return new AddressdaoImpl();
        }
        if(addressDao == null) {
            return new AddressdaoImpl();
        }
        return addressDao;
    }

    private static GroupDao getGroupDaoSQL(String scope){
        if(scope.equals("prototype")){
            return new GroupDaoImpl();
        }
        if(groupDao == null) {
            return new GroupDaoImpl();
        }
        return groupDao;
    }
    private static StudentDao  getStudentDaoSQL(String scope){
        if(scope.equals("prototype")) {
            return new StudentDaoImpl();
        }

        if(studentDao == null){
            studentDao = new StudentDaoImpl();
        }
        return studentDao;
    }




}
