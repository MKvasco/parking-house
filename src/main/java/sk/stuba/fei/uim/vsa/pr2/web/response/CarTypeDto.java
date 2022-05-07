package sk.stuba.fei.uim.vsa.pr2.web.response;

import java.util.List;

public class CarTypeDto extends Dto{

    private String name;
    private List<CarDto> cars;
    private List<ParkingSpotDto> parkingSpots;

    public CarTypeDto(String name, List<CarDto> cars, List<ParkingSpotDto> parkingSpots) {
        this.name = name;
        this.cars = cars;
        this.parkingSpots = parkingSpots;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public List<CarDto> getCars() {
        return cars;
    }

    public List<ParkingSpotDto> getParkingSpots() {
        return parkingSpots;
    }

    public void setCars(List<CarDto> cars) {
        this.cars = cars;
    }

    public void setParkingSpots(List<ParkingSpotDto> parkingSpots) {
        this.parkingSpots = parkingSpots;
    }
}
