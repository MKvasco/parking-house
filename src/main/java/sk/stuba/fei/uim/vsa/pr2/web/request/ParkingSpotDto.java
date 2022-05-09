package sk.stuba.fei.uim.vsa.pr2.web.request;

import java.util.List;

public class ParkingSpotDto{

    private Long id;
    private String identifier;
    private String carParkFloor;
    private Long carPark;
    private CarTypeDto carType;
    private Boolean available;
    private List<ReservationDto> reservations;

    public ParkingSpotDto(){
    }

    public Long getId() {
        return id;
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getCarParkFloor() {
        return carParkFloor;
    }

    public Long getCarPark() {
        return carPark;
    }

    public CarTypeDto getCarType() {
        return carType;
    }

    public Boolean getFree() {
        return available;
    }

    public List<ReservationDto> getReservations() {
        return reservations;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public void setCarParkFloor(String carParkFloor) {
        this.carParkFloor = carParkFloor;
    }

    public void setCarPark(Long carPark) {
        this.carPark = carPark;
    }

    public void setCarType(CarTypeDto carType) {
        this.carType = carType;
    }

    public void setFree(Boolean available) {
        this.available = available;
    }

    public void setReservations(List<ReservationDto> reservations) {
        this.reservations = reservations;
    }
}
