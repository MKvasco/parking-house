package sk.stuba.fei.uim.vsa.pr2.web.request.factory;

import sk.stuba.fei.uim.vsa.pr2.domain.CarPark;
import sk.stuba.fei.uim.vsa.pr2.domain.CarParkFloor;
import sk.stuba.fei.uim.vsa.pr2.web.response.CarParkDto;
import sk.stuba.fei.uim.vsa.pr2.web.response.CarParkFloorDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CarParkRequestFactory implements RequestFactory<CarPark, CarParkDto> {
    @Override
    public CarParkDto transformToDto(CarPark entity) {
        CarParkDto carParkDto = new CarParkDto();
        Long id = entity.getId();
        String name = entity.getName();
        String address = entity.getAddress();
        Integer price = entity.getPricePerHour();
        carParkDto.setId(id);
        carParkDto.setName(name);
        carParkDto.setAddress(address);
        carParkDto.setPrice(price);
        List<CarParkFloor> carParkFloors = entity.getCarParkFloors();
        if(!carParkFloors.isEmpty()){
            CarParkFloorRequestFactory carParkFloorResponseFactory = new CarParkFloorRequestFactory();
            carParkDto.setFloors(carParkFloors.stream().map(carParkFloorResponseFactory::transformToDto).collect(Collectors.toList()));
        }else{
            carParkDto.setFloors(new ArrayList<>());
        }
        return carParkDto;
    }

    @Override
    public CarPark transformToEntity(CarParkDto dto) {
        CarPark carPark = new CarPark();
        String name = dto.getName();
        String address = dto.getAddress();
        Integer price = dto.getPrice();
        List<CarParkFloorDto> carParkFloorDtoList = dto.getFloors();
        carPark.setName(name);
        carPark.setAddress(address);
        carPark.setPricePerHour(price);
        if(!carParkFloorDtoList.isEmpty()){
            CarParkFloorRequestFactory carParkFloorResponseFactory = new CarParkFloorRequestFactory();
            carPark.setCarParkFloors(carParkFloorDtoList.stream().map(carParkFloorResponseFactory::transformToEntity).collect(Collectors.toList()));
        }
        return carPark;
    }
}
