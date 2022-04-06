package sk.stuba.fei.uim.vsa.pr1b.entities;


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
        public static final String findAll = "findAll";
        public static final String findById = "findById";
        public static final String findByName = "findByName";
        public static final String deleteById = "deleteById";

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
}
