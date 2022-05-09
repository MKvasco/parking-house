package sk.stuba.fei.uim.vsa.pr2.web.request;

import java.util.List;

public class CarParkDto {

    private Long id;
    private String name;
    private String address;
    private Integer price;
    private List<CarParkFloorDto> floors;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
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

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
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
