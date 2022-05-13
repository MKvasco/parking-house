package sk.stuba.fei.uim.vsa.pr2.web.response.factories;

import sk.stuba.fei.uim.vsa.pr2.domain.CarPark;
import sk.stuba.fei.uim.vsa.pr2.domain.CarParkFloor;
import sk.stuba.fei.uim.vsa.pr2.web.controllers.service.CarParkService;
import sk.stuba.fei.uim.vsa.pr2.web.response.CarParkDto;
import sk.stuba.fei.uim.vsa.pr2.web.response.CarParkFloorDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CarParkFactory implements Factory<CarPark, CarParkDto> {
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
        carParkDto.setPrices(price);
        List<CarParkFloor> carParkFloors = entity.getCarParkFloors();
        if(!carParkFloors.isEmpty()){
            CarParkFloorFactory carParkFloorFactory = new CarParkFloorFactory();
            carParkDto.setFloors(carParkFloors.stream().map(carParkFloorFactory::transformToDto).collect(Collectors.toList()));
        }else{
            carParkDto.setFloors(new ArrayList<>());
        }
        return carParkDto;
    }

    @Override
    public CarPark transformToEntity(CarParkDto dto) {
        String name = dto.getName();
        String address = dto.getAddress();
        Integer price = dto.getPrices();

        CarPark carPark = new CarParkService().createCarPark(name, address, price);
        if(carPark == null) return null;

        List<CarParkFloorDto> carParkFloorDtoList = dto.getFloors();
        if(!carParkFloorDtoList.isEmpty()){
            for(CarParkFloorDto floor : carParkFloorDtoList){
                floor.setCarPark(carPark.getId());
            }
            CarParkFloorFactory carParkFloorFactory = new CarParkFloorFactory();
            carPark.setCarParkFloors(carParkFloorDtoList.stream().map(carParkFloorFactory::transformToEntity).collect(Collectors.toList()));
        }
        return carPark;
    }
}
