package sk.stuba.fei.uim.vsa.pr2.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "CAR_PARK")
@NamedQuery(name = CarPark.Queries.findAll,    query = "select carPark from CarPark carPark")
@NamedQuery(name = CarPark.Queries.findById,   query = "select carPark from CarPark carPark where carPark.id = :id")
@NamedQuery(name = CarPark.Queries.findByName, query = "select carPark from CarPark carPark where carPark.name = :name")
@NamedQuery(name = CarPark.Queries.deleteById, query = "delete from CarPark carPark where carPark.id = :id")
public class CarPark implements Serializable {
    public static final class Queries {
        public static final String findAll = "CarPark.findAll";
        public static final String findById = "CarPark.findById";
        public static final String findByName = "CarPark.findByName";
        public static final  String deleteById = "CarPark.deleteById";
    }
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String name;
    private String address;
    private Integer pricePerHour;

    @OneToMany(mappedBy = "carPark", orphanRemoval = true, cascade = CascadeType.REMOVE)
    private List<CarParkFloor> carParkFloors;


    public CarPark(String name, String address, Integer pricePerHour) {
        this.name = name;
        this.address = address;
        this.pricePerHour = pricePerHour;
    }

    public CarPark(){}

    @Override
    public String toString() {
        return "CarPark{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", pricePerHour=" + pricePerHour +
                '}';
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public Integer getPricePerHour() {
        return pricePerHour;
    }

    public List<CarParkFloor> getCarParkFloors() {
        return carParkFloors;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPricePerHour(Integer pricePerHour) {
        this.pricePerHour = pricePerHour;
    }

    public void setCarParkFloors(List<CarParkFloor> carParkFloors) {

        this.carParkFloors = carParkFloors;
    }
    public void addCarParkFloor(CarParkFloor carParkFloor){
        this.carParkFloors.add(carParkFloor);
    }
    public void removeCarParkFloor(CarParkFloor carParkFloor){
        this.carParkFloors.remove(carParkFloor);
    }
}
