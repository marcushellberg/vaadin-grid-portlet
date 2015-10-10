package org.vaadin.marcus.entity;


import javax.persistence.*;

@Entity
public class Customer {

    public enum OrderProgress {
        RECEIVED, PROCESSING, PACKAGING, SHIPPED, DELIVERED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private String address;
    private String company;
    private String city;
    private String zip;
    @Enumerated(EnumType.STRING)
    private OrderProgress orderProgress;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public OrderProgress getOrderProgress() {
        return orderProgress;
    }

    public void setOrderProgress(OrderProgress orderProgress) {
        this.orderProgress = orderProgress;
    }
}
