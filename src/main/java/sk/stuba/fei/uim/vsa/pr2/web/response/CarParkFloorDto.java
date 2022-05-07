package sk.stuba.fei.uim.vsa.pr2.web.response;

import java.util.List;

public class CarParkFloorDto extends Dto{

    private String floorIdentifier;
    private List<ParkingSpotDto> parkingSpots;
    private CarParkDto carPark;

    public CarParkFloorDto(String floorIdentifier, List<ParkingSpotDto> parkingSpots, CarParkDto carPark) {
        this.floorIdentifier = floorIdentifier;
        this.parkingSpots = parkingSpots;
        this.carPark = carPark;
    }

    public String getFloorIdentifier() {
        return floorIdentifier;
    }

    public List<ParkingSpotDto> getParkingSpots() {
        return parkingSpots;
    }

    public CarParkDto getCarPark() {
        return carPark;
    }

    public void setFloorIdentifier(String floorIdentifier) {
        this.floorIdentifier = floorIdentifier;
    }

    public void setParkingSpots(List<ParkingSpotDto> parkingSpots) {
        this.parkingSpots = parkingSpots;
    }

    public void setCarPark(CarParkDto carPark) {
        this.carPark = carPark;
    }
}
