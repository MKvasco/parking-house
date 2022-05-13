package sk.stuba.fei.uim.vsa.pr2.web.response;

import java.util.List;

public class ParkingSpotDto{

    private Long id;
    private String identifier;
    private String carParkFloor;
    private Long carPark;
    private CarTypeDto type;
    private Boolean free;
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

    public CarTypeDto getType() {
        return type;
    }

    public Boolean getFree() {
        return free;
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

    public void setType(CarTypeDto type) {
        this.type = type;
    }

    public void setFree(Boolean available) {
        this.free = available;
    }

    public void setReservations(List<ReservationDto> reservations) {
        this.reservations = reservations;
    }

    @Override
    public String toString() {
        return "ParkingSpotDto{" +
                "id=" + id +
                ", identifier='" + identifier + '\'' +
                ", carParkFloor='" + carParkFloor + '\'' +
                ", carPark=" + carPark +
                ", carType=" + type +
                ", available=" + free +
                ", reservations=" + reservations +
                '}';
    }
}
