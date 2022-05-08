package sk.stuba.fei.uim.vsa.pr2.web.response;

import java.util.List;

public class CarParkDto extends Dto {

    private String name;
    private String address;
    private Integer price;
    private List<CarParkFloorDto> floors;

    public CarParkDto(String name, String address, Integer price, List<CarParkFloorDto> floors) {
        this.name = name;
        this.address = address;
        this.price = price;
        this.floors = floors;
    }

    public CarParkDto(){}

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

    public Integer getPrice() {
        return price;
    }

    public List<CarParkFloorDto> getFloors() {
        return floors;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public void setFloors(List<CarParkFloorDto> floors) {
        this.floors = floors;
    }

    @Override
    public String toString() {
        return "CarParkDto{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", price=" + price +
                ", floors=" + floors +
                '}';
    }
}
