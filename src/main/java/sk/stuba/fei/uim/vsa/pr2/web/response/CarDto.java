package sk.stuba.fei.uim.vsa.pr2.web.response;

import java.util.List;

public class CarDto {

    private Long id;
    private String brand;
    private String model;
    private String vrp;
    private String colour;
    private CarTypeDto type;
    private UserWithCarsDto owner;
    private List<ReservationDto> reservations;

    public CarDto() {}

    public Long getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getVrp() {
        return vrp;
    }

    public String getColour() {
        return colour;
    }

    public CarTypeDto getType() {
        return type;
    }

    public UserWithCarsDto getOwner() {
        return owner;
    }

    public List<ReservationDto> getReservations() {
        return reservations;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setVrp(String vrp) {
        this.vrp = vrp;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public void setType(CarTypeDto type) {
        this.type = type;
    }

    public void setOwner(UserWithCarsDto owner) {
        this.owner = owner;
    }

    public void setReservations(List<ReservationDto> reservations) {
        this.reservations = reservations;
    }

    @Override
    public String toString() {
        return "CarDto{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", vrp='" + vrp + '\'' +
                ", colour='" + colour + '\'' +
                ", carType=" + type +
                ", user=" + owner +
                ", reservations=" + reservations +
                '}';
    }
}
