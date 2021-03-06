package sk.stuba.fei.uim.vsa.pr2.domain;


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
    private String floorIdentifier;
    @Transient
    private Boolean available = true;

    @ManyToOne
    private CarParkFloor carParkFloor;

    @ManyToOne
    private CarType carType;

    @OneToMany(mappedBy = "parkingSpot")
    private List<Reservation> reservations;

    public ParkingSpot(CarParkFloor carParkFloor, CarType carType, String identifier){
        this.carParkFloor = carParkFloor;
        this.carType = carType;
        this.identifier = identifier;
    }
    public ParkingSpot(CarParkFloor carParkFloor, String identifier){
        this.carParkFloor = carParkFloor;
        this.identifier = identifier;
    }
    public ParkingSpot(){}

    public String getFloorIdentifier() {
        return floorIdentifier;
    }

    public void setFloorIdentifier(String floorIdentifier) {
        this.floorIdentifier = floorIdentifier;
    }

    public Long getId() {
        return id;
    }

    public String getIdentifier() {
        return identifier;
    }

    public CarParkFloor getCarParkFloor() {
        return carParkFloor;
    }

    public Boolean isAvailable() {
        return available;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public void setAvailable(Boolean available) {
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
