package model.builder;

import model.Address;

import java.time.LocalDateTime;

public class AddressBuilder {

    private Long id;
    private LocalDateTime dateCreated;
    private String country;
    private String city;
    private String region;
    private String district;
    private String apartment;

    public static AddressBuilder builder(){
        return new AddressBuilder();
    }

    public Address build(){
        return new Address(id, country, city, region, district, apartment);
    }

    public AddressBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public AddressBuilder dateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
        return this;
    }

    public AddressBuilder country(String country) {
        this.country = country;
        return this;
    }

    public AddressBuilder city(String city) {
        this.city = city;
        return this;
    }

    public AddressBuilder region(String region) {
        this.region = region;
        return this;
    }

    public AddressBuilder district(String district) {
        this.district = district;
        return this;
    }

    public AddressBuilder apartment(String apartment) {
        this.apartment = apartment;
        return this;
    }
}
