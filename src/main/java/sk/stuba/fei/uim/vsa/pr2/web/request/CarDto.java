package sk.stuba.fei.uim.vsa.pr2.web.request;

import java.util.List;

public class CarDto {

    private Long id;
    private String brand;
    private String model;
    private String vrp;
    private String colour;
    private CarTypeDto carType;
    private Long userId;
    private List<ReservationDto> reservations;

    public CarDto() {}

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
        return carType;
    }

    public void setCarType(CarTypeDto carType) {
        this.carType = carType;
    }

    public Long getOwner() {
        return userId;
    }

    public void setUser(Long userId) {
        this.userId = userId;
    }

    public List<ReservationDto> getReservations() {
        return reservations;
    }

    public void setReservations(List<ReservationDto> reservations) {
        this.reservations = reservations;
    }
}
