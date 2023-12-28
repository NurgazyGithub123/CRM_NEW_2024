
import dao.AddressDao;
import dao.ManagerDao;
import dao.MentorDao;
import dao.daoUtil.DaoFactory;
import model.Address;
import model.Manager;
import model.Mentor;

import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AddressDao addressDao = (AddressDao) DaoFactory.autowired("AddressDao", "singleton");

        List<Address> addresses = addressDao.findAll();
        System.out.println(addresses);

        addressDao.findByid(3L);

    }
}