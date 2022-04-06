package sk.stuba.fei.uim.vsa.pr1b.entities;

import jdk.jfr.Name;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "CAR")
@NamedQuery(name = Car.Queries.findAll, query = "select car from Car car")
@NamedQuery(name = Car.Queries.findById, query = "select car from Car car where car.id = :id")
@NamedQuery(name = Car.Queries.deleteById, query = "delete from Car car where car.id = :id")
public class Car implements Serializable {
    public static final class Queries{
        public static final String findAll = "Car.findAll";
        public static final String findById = "Car.findById";
        public static final String deleteById = "Car.deleteById";
    }

    @Id
    @GeneratedValue
    private Long id;

    private String brand;
    private String model;
    private String colour;

    @Column(name = "VEHICLE_REGISTRATION_PLATE", unique = true)
    private String vehicleRegistrationPlate;

    @ManyToOne
    private User user;

    @ManyToOne
    private CarType carType;

    @OneToMany(mappedBy = "car")
    private List<Reservation> reservations;


    public Car(String brand, String model, String colour, String vehicleRegistrationPlate, CarType carType) {
        this.brand = brand;
        this.model = model;
        this.colour = colour;
        this.vehicleRegistrationPlate = vehicleRegistrationPlate;
        this.carType = carType;
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

    public CarType getCarType() {
        return carType;
    }

    public void setCarType(CarType carType) {
        this.carType = carType;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }
}
