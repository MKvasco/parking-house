package sk.stuba.fei.uim.vsa.pr1b.entities;


import jdk.jfr.Name;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "PARKING_SPOT")
@NamedQuery(name = ParkingSpot.Queries.findAll, query = "select parkingSpot from ParkingSpot parkingSpot")
@NamedQuery(name = ParkingSpot.Queries.findById, query = "select parkingSpot from ParkingSpot parkingSpot where parkingSpot.id = :id")
@NamedQuery(name = ParkingSpot.Queries.deleteById, query = "delete from ParkingSpot parkingSpot where parkingSpot.id = :id")
public class ParkingSpot implements Serializable {

    public static final class Queries{
        public static final String findAll = "findAll";
        public static final String findById = "findById";
        public static final String deleteById = "deleteById";
    }
    @Id
    @GeneratedValue
    private Long id;

    private String identifier;
    @Transient
    private boolean available;

    @ManyToOne
    private CarParkFloor carParkFloor;

    @ManyToOne
    private CarType carType;

    @OneToMany(mappedBy = "parkingSpot")
    private List<Reservation> reservations;

    public ParkingSpot(CarParkFloor carParkFloor, CarType carType, String identifier){
        this.carParkFloor = carParkFloor;
        this.available = true;
        this.carType = carType;
        this.reservations = null;
        this.identifier = identifier;
    }
    public ParkingSpot(){}

    public Long getId() {
        return id;
    }

    public String getIdentifier() {
        return identifier;
    }

    public CarParkFloor getCarParkFloor() {
        return carParkFloor;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void setCarParkFloor(CarParkFloor carParkFloor) {
        this.carParkFloor = carParkFloor;
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

    public void setReservation(List<Reservation> reservations) {
        this.reservations = reservations;
    }
    public void addReservation(Reservation reservation){
        this.reservations.add(reservation);
    }


    @Override
    public String toString() {
        return "ParkingSpot{" +
                "id=" + id +
                ", identifier='" + identifier + '\'' +
                ", carParkFloor=" + carParkFloor +
                ", carType=" + carType +
                '}';
    }
}
