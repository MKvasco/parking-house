package sk.stuba.fei.uim.vsa.pr2.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "CAR_TYPE")
@NamedQuery(name=CarType.Queries.findAll, query = "select carType from CarType carType")
@NamedQuery(name=CarType.Queries.findById, query = "select carType from CarType carType where carType.id = :id")
@NamedQuery(name=CarType.Queries.findByName, query = "select  carType from CarType carType where carType.name = :name")
@NamedQuery(name=CarType.Queries.deleteById, query = "delete from CarType carType where carType.id = :id")
public class CarType implements Serializable {

    public static final class Queries{
        public static final String findAll = "CarType.findAll";
        public static final String findById = "CarType.findById";
        public static final String findByName = "CarType.findByName";
        public static final String deleteById = "CarType.deleteById";

    }
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @OneToMany(mappedBy = "carType")
    private List<Car> cars;

    @OneToMany(mappedBy = "carType")
    private List<ParkingSpot>  parkingSpots;

    public CarType(String name) {
        this.name = name;
    }

    public CarType() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Car> getCars() {
        return cars;
    }

    public List<ParkingSpot> getParkingSpots() {
        return parkingSpots;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public void setParkingSpots(List<ParkingSpot> parkingSpots) {
        this.parkingSpots = parkingSpots;
    }
    public void addParkingSpot(ParkingSpot parkingSpot){
        this.parkingSpots.add(parkingSpot);
    }
    public void addCars(Car car){
        this.cars.add(car);
    }
    public void removeCar(Car car){
        this.cars.remove(car);
    }
    public void  removeParkingSpot(ParkingSpot parkingSpot){
        this.parkingSpots.remove(parkingSpot);
    }

    @Override
    public String toString() {
        return "CarType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
