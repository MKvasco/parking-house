package sk.stuba.fei.uim.vsa.pr2.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "CAR_PARK_FLOOR")
@NamedQuery(name = CarParkFloor.Queries.findById, query="select carParkFloor from CarParkFloor carParkFloor where carParkFloor.id = :id")
@NamedQuery(name = CarParkFloor.Queries.deleteById, query="delete from CarParkFloor carParkFloor where carParkFloor.id = :id")
public class CarParkFloor implements Serializable {
    public static final class Queries{
        public static final String findById = "CarParkFloor.findById";
        public static final String deleteById = "CarParkFloor.deleteById";
    }
    @Id
    @GeneratedValue
    private Long id;

    private String floorIdentifier;

    @OneToMany(mappedBy = "carParkFloor", orphanRemoval = true, cascade = CascadeType.REMOVE)
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

    public void setId(Long id) {
        this.id = id;
    }

    public void setFloorIdentifier(String floorIdentifier) {
        this.floorIdentifier = floorIdentifier;
    }

    public void setParkingSpots(List<ParkingSpot> parkingSpots) {
        this.parkingSpots = parkingSpots;
    }

    public void setCarPark(CarPark carPark) {

        this.carPark = carPark;
    }
    public void addParkingSpot(ParkingSpot parkingSpot){
        this.parkingSpots.add(parkingSpot);
    }
    public void removeParkingSpot(ParkingSpot parkingSpot){
        this.parkingSpots.remove(parkingSpot);
    }

    @Override
    public String toString() {
        return "CarParkFloor{" +
                "id=" + id +
                ", floorIdentifier='" + floorIdentifier + '\'' +
                ", carPark=" + carPark +
                '}';
    }
}
