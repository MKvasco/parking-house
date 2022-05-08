package sk.stuba.fei.uim.vsa.pr2.web.response;

import java.util.Date;
import java.util.List;

public class ParkingSpotDto extends Dto{

    private String identifier;
    private boolean available;
    private CarParkFloorDto carParkFloor;
    private CarTypeDto carType;
    private List<ReservationDto> reservations;

    public ParkingSpotDto(String identifier, boolean available, CarParkFloorDto carParkFloor, CarTypeDto carType, List<ReservationDto> reservations) {
        this.identifier = identifier;
        this.available = available;
        this.carParkFloor = carParkFloor;
        this.carType = carType;
        this.reservations = reservations;
    }
    public ParkingSpotDto(){}

    public String getIdentifier() {
        return identifier;
    }

    public boolean isAvailable() {
        return available;
    }

    public CarParkFloorDto getCarParkFloor() {
        return carParkFloor;
    }

    public CarTypeDto getCarType() {
        return carType;
    }

    public List<ReservationDto> getReservations() {
        return reservations;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void setCarParkFloor(CarParkFloorDto carParkFloor) {
        this.carParkFloor = carParkFloor;
    }

    public void setCarType(CarTypeDto carType) {
        this.carType = carType;
    }

    public void setReservations(List<ReservationDto> reservations) {
        this.reservations = reservations;
    }
}
