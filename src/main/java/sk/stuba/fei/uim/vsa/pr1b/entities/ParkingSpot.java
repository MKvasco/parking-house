package sk.stuba.fei.uim.vsa.pr1b.entities;


import jdk.jfr.Name;

import javax.persistence.*;

@Entity
@Table(name = "PARKING_SPOT")
@NamedQuery(name = ParkingSpot.Queries.findAll, query = "select parkingSpot from ParkingSpot parkingSpot")
@NamedQuery(name = ParkingSpot.Queries.findById, query = "select parkingSpot from ParkingSpot parkingSpot where parkingSpot.id = :id")
@NamedQuery(name = ParkingSpot.Queries.deleteById, query = "delete from ParkingSpot parkingSpot where parkingSpot.id = :id")
public class ParkingSpot {

    public static final class Queries{
        public static final String findAll = "findAll";
        public static final String findById = "findById";
        public static final String deleteById = "deleteById";
    }
    @Id
    @GeneratedValue
    private Long id;

    private String identifier;
    private boolean available;

    @ManyToOne
    private CarParkFloor carParkFloor;

    @ManyToOne
    private CarType carType;

    public ParkingSpot(CarParkFloor carParkFloor){
        this.carParkFloor = carParkFloor;
        this.available = true;
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
}
