package sk.stuba.fei.uim.vsa.pr2.web.response;

import java.util.List;

public class CarDto extends Dto{

    private String brand;
    private String model;
    private String colour;
    private String vehicleRegistrationPlate;
    private UserDto user;
    private CarTypeDto carType;
    private List<ReservationDto> reservations;

    public CarDto(String brand, String model, String colour, String vehicleRegistrationPlate, UserDto user, CarTypeDto carType, List<ReservationDto> reservations) {
        this.brand = brand;
        this.model = model;
        this.colour = colour;
        this.vehicleRegistrationPlate = vehicleRegistrationPlate;
        this.user = user;
        this.carType = carType;
        this.reservations = reservations;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getColour() {
        return colour;
    }

    public String getVehicleRegistrationPlate() {
        return vehicleRegistrationPlate;
    }

    public UserDto getUser() {
        return user;
    }

    public CarTypeDto getCarType() {
        return carType;
    }

    public List<ReservationDto> getReservations() {
        return reservations;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public void setVehicleRegistrationPlate(String vehicleRegistrationPlate) {
        this.vehicleRegistrationPlate = vehicleRegistrationPlate;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public void setCarType(CarTypeDto carType) {
        this.carType = carType;
    }

    public void setReservations(List<ReservationDto> reservations) {
        this.reservations = reservations;
    }
}
