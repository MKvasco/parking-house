package sk.stuba.fei.uim.vsa.pr2.web.response.factories;

import sk.stuba.fei.uim.vsa.pr2.domain.CarParkFloor;
import sk.stuba.fei.uim.vsa.pr2.domain.ParkingSpot;
import sk.stuba.fei.uim.vsa.pr2.web.controllers.service.CarParkFloorService;
import sk.stuba.fei.uim.vsa.pr2.web.response.CarParkFloorDto;
import sk.stuba.fei.uim.vsa.pr2.web.response.ParkingSpotDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CarParkFloorFactory implements Factory<CarParkFloor, CarParkFloorDto> {
    @Override
    public CarParkFloorDto transformToDto(CarParkFloor entity) {
        CarParkFloorDto carParkFloorDto = new CarParkFloorDto();

        carParkFloorDto.setId(entity.getId());
        carParkFloorDto.setIdentifier(entity.getFloorIdentifier());
        if(entity.getCarPark() == null) carParkFloorDto.setCarPark(null);
        if(entity.getCarPark() != null) carParkFloorDto.setCarPark(entity.getCarPark().getId());

        List<ParkingSpot> spots = entity.getParkingSpots();
        if(spots == null) spots = new ArrayList<>();
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
        CarParkFloor carParkFloor = new CarParkFloor();
        if(dto.getCarPark() == null ) carParkFloor = new CarParkFloorService().createCarParkFloor(dto.getIdentifier());
        if(dto.getCarPark() != null) carParkFloor = new CarParkFloorService().createCarParkFloor(dto.getCarPark(), dto.getIdentifier());
        if(carParkFloor == null) return null;
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
