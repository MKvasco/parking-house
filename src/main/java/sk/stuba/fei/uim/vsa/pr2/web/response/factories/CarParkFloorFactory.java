package sk.stuba.fei.uim.vsa.pr2.web.response.factories;

import sk.stuba.fei.uim.vsa.pr2.domain.CarPark;
import sk.stuba.fei.uim.vsa.pr2.domain.CarParkFloor;
import sk.stuba.fei.uim.vsa.pr2.domain.ParkingSpot;
import sk.stuba.fei.uim.vsa.pr2.service.CarParkFloorService;
import sk.stuba.fei.uim.vsa.pr2.web.response.CarParkFloorDto;
import sk.stuba.fei.uim.vsa.pr2.web.response.ParkingSpotDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CarParkFloorFactory implements Factory<CarParkFloor, CarParkFloorDto> {
    @Override
    public CarParkFloorDto transformToDto(CarParkFloor entity) {
        CarParkFloorDto carParkFloorDto = new CarParkFloorDto();
        Long id = entity.getId();
        String identifier = entity.getFloorIdentifier();
        CarPark carPark = entity.getCarPark();
        carParkFloorDto.setId(id);
        carParkFloorDto.setIdentifier(identifier);
        carParkFloorDto.setCarPark(carPark.getId());
        List<ParkingSpot> spots = entity.getParkingSpots();
        if(!spots.isEmpty()){
            ParkingSpotFactory parkingSpotFactory = new ParkingSpotFactory();
            carParkFloorDto.setSpots(entity.getParkingSpots().stream().map(parkingSpotFactory::transformToDto).collect(Collectors.toList()));
        }else{
            carParkFloorDto.setSpots(new ArrayList<>());
        }
        return carParkFloorDto;
    }

    @Override
    public CarParkFloor transformToEntity(CarParkFloorDto dto) {
        CarParkFloor carParkFloor = new CarParkFloorService().createCarParkFloor(dto.getCarPark(), dto.getIdentifier());
        if(carParkFloor == null){
            return null;
        }
        List<ParkingSpotDto> parkingSpotDtoList = dto.getSpots();

        if(parkingSpotDtoList == null) parkingSpotDtoList = new ArrayList<>();
        if(!parkingSpotDtoList.isEmpty()){
            ParkingSpotFactory parkingSpotFactory = new ParkingSpotFactory();
            for(ParkingSpotDto parkingSpotDto : parkingSpotDtoList){
                parkingSpotDto.setCarPark(dto.getCarPark());
            }
            carParkFloor.setParkingSpots(parkingSpotDtoList.stream().map(parkingSpotFactory::transformToEntity).collect(Collectors.toList()));
        }
        return carParkFloor;
    }
}
