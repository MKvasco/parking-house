package sk.stuba.fei.uim.vsa.pr2.web.request;

import java.util.List;

public class CarParkFloorDto {

    private Long id;
    private String identifier;
    private Long carPark;
    private List<ParkingSpotDto> spots;


    public Long getId() {
        return id;
    }

    public String getIdentifier() {
        return identifier;
    }

    public Long getCarPark() {
        return carPark;
    }

    public List<ParkingSpotDto> getSpots() {
        return spots;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public void setCarPark(Long carPark) {
        this.carPark = carPark;
    }

    public void setSpots(List<ParkingSpotDto> spots) {
        this.spots = spots;
    }

    @Override
    public String toString() {
        return "CarParkFloorDto{" +
                "identifier='" + identifier + '\'' +
                ", carPark=" + carPark +
                ", spots=" + spots +
                '}';
    }
}
