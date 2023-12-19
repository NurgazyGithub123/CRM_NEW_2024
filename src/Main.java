import dao.DaoFactory;
import dao.ManagerDao;
import dao.MentorDao;
import dao.impl.MentorDaoImpl;
import model.Manager;
import model.Mentor;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
//        Mentor mentor = new Mentor();
//        System.out.print("FirstName: ");
//        mentor.setFirstName(scanner.nextLine());
//
//        System.out.print("LastName: ");
//        mentor.setLastName(scanner.nextLine());
//
//        System.out.print("PhoneNumber");
//        mentor.setPhoneNumber(scanner.nextLine());
//
//        System.out.print("Email: ");
//        mentor.setEmail(scanner.nextLine());
//
//        System.out.print("DOB: ");
//        mentor.setDob(LocalDate.parse(scanner.nextLine()));
//
//        System.out.print("Salary: ");
//        mentor.setSalary(scanner.nextDouble());
//
//        System.out.println("Input: " + mentor);
//
//        MentorDao mentorDao = DaoFactory.getMentorDaoSQL();
//        System.out.println("From database: " + mentorDao.save(mentor));


        MentorDao mentorDao = new MentorDaoImpl();


        System.out.println("ID : " + mentorDao.findByid(4L));
    }
}