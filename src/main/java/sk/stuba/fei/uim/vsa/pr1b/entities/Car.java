package sk.stuba.fei.uim.vsa.pr1b.entities;

import jdk.jfr.Name;

import javax.persistence.*;

@Entity
@NamedQuery(name = Car.Queries.findAll, query = "select car from Car car")
@NamedQuery(name = Car.Queries.findById, query = "select car from Car car where car.id = :id")
@NamedQuery(name = Car.Queries.deleteById, query = "delete from Car car where car.id = :id")
public class Car {
    public static final class Queries{
        public static final String findAll = "findAll";
        public static final String findById = "findById";
        public static final String deleteById = "deleteById";
    }

    @Id
    @GeneratedValue
    private Long id;
    private String brand;
    private String model;
    private String colour;
    private String vehicleRegistrationPlate;

    @ManyToOne
    private User user;


    public Car(String brand, String model, String colour, String vehicleRegistrationPlate) {
        this.brand = brand;
        this.model = model;
        this.colour = colour;
        this.vehicleRegistrationPlate = vehicleRegistrationPlate;
    }

    public Car() {
    }

    public Long getId() {
        return id;
    }


    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getColour() {
        return colour;
    }

    public String getVehicleRegistrationPlate() {
        return vehicleRegistrationPlate;
    }


    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public void setVehicleRegistrationPlate(String vehicleRegistrationPlate) {
        this.vehicleRegistrationPlate = vehicleRegistrationPlate;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
