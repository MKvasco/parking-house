package sk.stuba.fei.uim.vsa.pr2.web.response;

import java.util.List;

public class CarParkDto extends Dto {

    private String name;
    private String address;
    private Integer pricePerHour;
    private List<CarParkFloorDto> carParkFloors;

    public CarParkDto(String name, String address, Integer pricePerHour, List<CarParkFloorDto> carParkFloors) {
        this.name = name;
        this.address = address;
        this.pricePerHour = pricePerHour;
        this.carParkFloors = carParkFloors;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public Integer getPricePerHour() {
        return pricePerHour;
    }

    public List<CarParkFloorDto> getCarParkFloors() {
        return carParkFloors;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPricePerHour(Integer pricePerHour) {
        this.pricePerHour = pricePerHour;
    }

    public void setCarParkFloors(List<CarParkFloorDto> carParkFloors) {
        this.carParkFloors = carParkFloors;
    }
}
