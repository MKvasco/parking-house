package sk.stuba.fei.uim.vsa.pr1b.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "CAR_PARK_FLOOR")
@NamedQuery(name = CarParkFloor.Queries.findById, query="select carParkFloor from CarParkFloor carParkFloor where carParkFloor.id = :id")
@NamedQuery(name = CarParkFloor.Queries.deleteById, query="delete from CarParkFloor carParkFloor where carParkFloor.id = :id")
public class CarParkFloor {
    public static final class Queries{
        public static final String findById = "CarParkFloor.findById";
        public static final String deleteById = "CarParkFloor.deleteById";
    }
    @Id
    @GeneratedValue
    private Long id;
    private String floorIdentifier;

    @OneToMany(mappedBy = "carParkFloor")
    private List<ParkingSpot> parkingSpots;

    @ManyToOne
    private CarPark carPark;

    public CarParkFloor(CarPark carPark, String floorIdentifier) {
        this.floorIdentifier = floorIdentifier;
        this.carPark = carPark;

    }
    public CarParkFloor() {}

    public Long getId() {
        return id;
    }

    public String getFloorIdentifier() {
        return floorIdentifier;
    }

    public List<ParkingSpot> getParkingSpots() {
        return parkingSpots;
    }

    public CarPark getCarPark() {
        return carPark;
    }

    public void setFloorIdentifier(String floorIdentifier) {
        this.floorIdentifier = floorIdentifier;
    }

    public void setCarPark(CarPark carPark) {
        this.carPark = carPark;
    }
}
