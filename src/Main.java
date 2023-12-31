
import dao.AddressDao;
import dao.daoUtil.DaoFactory;
import model.Address;
import model.builder.AddressBuilder;

import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) {

        Address address = AddressBuilder.builder()
                .country("kgz")
                .city("Bishkek")
                .region("uuuuu")
                .district("qqq")
                .apartment("cdcdcd")
                .build();

        Address address1 = AddressBuilder.builder()
                .country("KZ")
                .city("Almata")
                .region("jjjjj")
                .district("qqq")
                .apartment("cdcdcd")
                .build();

        Address address2 = AddressBuilder.builder()
                .country("UZ")
                .city("Tashkent")
                .region("kkkkk")
                .district("qqq")
                .apartment("cdcdcd")
                .build();

        List<Address> addressList = new ArrayList<>();
        addressList.add(address);
        addressList.add(address1);
        addressList.add(address2);

        AddressDao addressDao = (AddressDao) DaoFactory.autowired("AddressDao", "singleton");
        List<Address> savedAddress = addressDao.saveAll(addressList);

        System.out.println(savedAddress);


    }
}