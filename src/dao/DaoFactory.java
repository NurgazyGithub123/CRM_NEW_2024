package dao;

import dao.impl.ManagerDaoImpl;
import dao.impl.MentorDaoImpl;

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

    public static ManagerDao getManagerDaoSQL(){
        return new ManagerDaoImpl();
    }

    public static MentorDao getMentorDaoSQL(){
        return new MentorDaoImpl();
    }

}
