package dao.impl;

import dao.AddressDao;
import dao.daoUtil.Log;
import model.Address;
import model.Course;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AddressdaoImpl implements AddressDao {

    public AddressdaoImpl(){

        Connection connection = null;
        Statement statement = null;

        try {

            Log.info(this.getClass().getSimpleName(),Connection.class.getSimpleName(), "Connecting to DATABASE");
            connection = getConnection();
            String ddlQuery = "CREATE TABLE IF NOT EXISTS tb_address(" +
                    "id            BIGSERIAL, " +
                    "country       VARCHAR(50) NOT NULL, " +
                    "city          VARCHAR(50) NOT NULL, " +
                    "region        VARCHAR(50) NOT NULL UNIQUE, " +
                    "district      VARCHAR(50) NOT NULL, " +
                    "apartment     VARCHAR(50) NOT NULL, " +
                    "date_created TIMESTAMP   NOT NULL DEFAULT NOW(), " +
                    "" +
                    "CONSTRAINT pk_address_id PRIMARY KEY(id) " +
                    ")";

            statement = connection.createStatement();
            statement.execute(ddlQuery);

        } catch (SQLException e) {
            Log.error(this.getClass().getSimpleName(),e.getStackTrace()[0].getClass().getSimpleName(),e.getMessage());
            e.printStackTrace();
        }finally {
            close(statement);
            close(connection);
        }
    }
    @Override
    public Address save(Address address) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Address savedAddress = null;

        try {
            Log.info(this.getClass().getSimpleName(),Connection.class.getSimpleName(), "Connecting to DATABASE");
            connection = getConnection();
            Log.info(this.getClass().getSimpleName(),Connection.class.getSimpleName(), "Connecting succeeted");


            String createQuery = "INSERT INTO tb_address(country, city, region, district, apartment, date_created) " +
                    "VALUES(?, ?, ?, ?, ?, ?)";

            String readQuery = "SELECT * FROM tb_address ORDER BY ID DESC LIMIT 1";



            preparedStatement = connection.prepareStatement(createQuery);
            preparedStatement.setString(1, address.getCountry());
            preparedStatement.setString(2, address.getCity());
            preparedStatement.setString(3, address.getRegion());
            preparedStatement.setTimestamp(6,Timestamp.valueOf(address.getDateCreated()));
            preparedStatement.setString(4, address.getDistrict());
            preparedStatement.setString(5, address.getApartment());

            preparedStatement.execute();
            close(preparedStatement);

            preparedStatement = connection.prepareStatement(readQuery);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();

            savedAddress = new Address();
            savedAddress.setId(resultSet.getLong("id"));
            savedAddress.setCountry(resultSet.getString("country"));
            savedAddress.setCity(resultSet.getString("city"));
            savedAddress.setRegion(resultSet.getString("region"));
            savedAddress.setDistrict(resultSet.getString("district"));
            savedAddress.setApartment(resultSet.getString("apartment"));
            savedAddress.setDateCreated(resultSet.getTimestamp("date_created").toLocalDateTime());


        } catch (SQLException e) {
            Log.error(this.getClass().getSimpleName(),e.getStackTrace()[0].getClass().getSimpleName(),e.getMessage());
            throw new RuntimeException(e);
        }finally {
            close(resultSet);
            close(preparedStatement);
            close(connection);
        }

        return savedAddress;
    }

    @Override
    public Address findByid(Long id) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Address savedAddress = null;

        try {
            Log.info(this.getClass().getSimpleName(), Connection.class.getSimpleName(), "Connecting to DATABASE");
            connection = getConnection();
            Log.info(this.getClass().getSimpleName(), Connection.class.getSimpleName(), "Connecting succeeted");


            String readQuery = "SELECT id FROM tb_address WHERE id = ?";

            preparedStatement = connection.prepareStatement(readQuery);
            preparedStatement.setLong(1, id);

            resultSet = preparedStatement.executeQuery();
            resultSet.next();

            savedAddress = new Address();
            savedAddress.setId(resultSet.getLong("id"));
            savedAddress.setCountry(resultSet.getString("country"));
            savedAddress.setCity(resultSet.getString("city"));
            savedAddress.setRegion(resultSet.getString("region"));
            savedAddress.setDistrict(resultSet.getString("district"));
            savedAddress.setApartment(resultSet.getString("apartment"));
            savedAddress.setDateCreated(resultSet.getTimestamp("date_created").toLocalDateTime());

        }catch (SQLException e){
            Log.error(this.getClass().getSimpleName(),e.getStackTrace()[0].getClass().getSimpleName(),e.getMessage());
            throw new RuntimeException(e);
        }finally {
            close(resultSet);
            close(preparedStatement);
            close(connection);
        }
        return savedAddress;
    }

    @Override
    public List<Address> findAll() {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        List<Address> addresses = new ArrayList<>();

        try {
            Log.info(this.getClass().getSimpleName(), Connection.class.getSimpleName(), "Connection to Database...");
            connection = getConnection();

            String readQuery = "SELECT * FROM tb_address";

            preparedStatement = connection.prepareStatement(readQuery);
            resultSet = preparedStatement.executeQuery();

            for (int i = 0; i <= addresses.size() && resultSet.next(); i++) {
                Address address = new Address();
                address.setId(resultSet.getLong("id"));
                address.setCountry(resultSet.getString("country"));
                address.setCity(resultSet.getString("city"));
                address.setRegion(resultSet.getString("region"));
                address.setDistrict(resultSet.getString("district"));
                address.setApartment(resultSet.getString("apartment"));
                address.setDateCreated(resultSet.getTimestamp("date_created").toLocalDateTime());

                addresses.add(address);
            }
           return addresses;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}
