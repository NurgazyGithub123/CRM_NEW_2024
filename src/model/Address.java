package model;

import java.time.LocalDateTime;

public final class Address{

    private Long id;
    private LocalDateTime dateCreated;
    private String country;
    private String city;
    private String region;
    private String district;
    private String apartment;

    public Address() {
        this.dateCreated= LocalDateTime.now();
    }

    public Address(Long id, String country, String city, String region, String district, String apartment) {
        this.id = id;
        this.dateCreated = LocalDateTime.now();
        this.country = country;
        this.city = city;
        this.region = region;
        this.district = district;
        this.apartment = apartment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", dateCreated=" + dateCreated +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", region='" + region + '\'' +
                ", district='" + district + '\'' +
                ", apartment='" + apartment + '\'' +
                '}';
    }
}
