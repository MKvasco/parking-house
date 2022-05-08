package sk.stuba.fei.uim.vsa.pr2.web.response.factories;

import sk.stuba.fei.uim.vsa.pr2.domain.CarPark;
import sk.stuba.fei.uim.vsa.pr2.domain.CarParkFloor;
import sk.stuba.fei.uim.vsa.pr2.web.response.CarParkDto;
import sk.stuba.fei.uim.vsa.pr2.web.response.CarParkFloorDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CarParkResponseFactory implements ResponseFactory<CarPark, CarParkDto> {
    @Override
    public CarParkDto transformToDto(CarPark entity) {
        String name = entity.getName();
        String address = entity.getAddress();
        Integer price = entity.getPricePerHour();
        List<CarParkFloor> floors = entity.getCarParkFloors();
        if(floors == null){
            floors = new ArrayList<>();
        }
        CarParkFloorResponseFactory factory = new CarParkFloorResponseFactory();
        List<CarParkFloorDto> floorDtoList = floors.stream().map(factory::transformToDto).collect(Collectors.toList());
        return new CarParkDto(name, address, price, floorDtoList);
    }

    @Override
    public CarPark transformToEntity(CarParkDto dto) {
        String name = dto.getName();
        String address = dto.getAddress();
        Integer price = dto.getPrice();
        CarPark carPark = new CarPark(name, address, price);
        if(dto.getFloors() != null){
            CarParkFloorResponseFactory factory = new CarParkFloorResponseFactory();
            List<CarParkFloor> floors = dto.getFloors().stream().map(factory::transformToEntity).collect(Collectors.toList());
            carPark.setCarParkFloors(floors);
        }
        return carPark;
    }
}
