package sk.stuba.fei.uim.vsa.pr2.web.response;

import java.util.List;

public class CarWithUsersDto {

    private Long id;
    private String brand;
    private String model;
    private String vrp;
    private String colour;
    private CarTypeDto type;
    private UserWithCarsDto owner;
    private List<ReservationDto> reservations;

    public CarWithUsersDto() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getVrp() {
        return vrp;
    }

    public void setVrp(String vrp) {
        this.vrp = vrp;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public CarTypeDto getType() {
        return type;
    }

    public void setCarType(CarTypeDto carType) {
        this.type = carType;
    }

    public UserWithCarsDto getOwner() {
        return owner;
    }

    public void setUser(UserWithCarsDto user) {
        this.owner = user;
    }

    public List<ReservationDto> getReservations() {
        return reservations;
    }

    public void setReservations(List<ReservationDto> reservations) {
        this.reservations = reservations;
    }

    @Override
    public String toString() {
        return "CarWithUsersDto{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", vrp='" + vrp + '\'' +
                ", colour='" + colour + '\'' +
                ", type=" + type +
                ", owner=" + owner +
                ", reservations=" + reservations +
                '}';
    }
}
