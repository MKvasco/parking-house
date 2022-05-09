package sk.stuba.fei.uim.vsa.pr2.web.request.factory;

import sk.stuba.fei.uim.vsa.pr2.domain.CarPark;
import sk.stuba.fei.uim.vsa.pr2.domain.CarParkFloor;
import sk.stuba.fei.uim.vsa.pr2.domain.ParkingSpot;
import sk.stuba.fei.uim.vsa.pr2.service.CarParkService;
import sk.stuba.fei.uim.vsa.pr2.web.response.CarParkFloorDto;
import sk.stuba.fei.uim.vsa.pr2.web.response.ParkingSpotDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CarParkFloorRequestFactory implements RequestFactory<CarParkFloor, CarParkFloorDto> {
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
            ParkingSpotRequestFactory parkingSpotResponseFactory = new ParkingSpotRequestFactory();
            carParkFloorDto.setSpots(entity.getParkingSpots().stream().map(parkingSpotResponseFactory::transformToDto).collect(Collectors.toList()));
        }else{
            carParkFloorDto.setSpots(new ArrayList<>());
        }
        return carParkFloorDto;
    }

    @Override
    public CarParkFloor transformToEntity(CarParkFloorDto dto) {
        CarParkFloor carParkFloor = new CarParkFloor();
        Long id = dto.getId();
        String identifier = dto.getIdentifier();
        List<ParkingSpotDto> parkingSpotDtoList = dto.getSpots();
        carParkFloor.setId(id);
        carParkFloor.setFloorIdentifier(identifier);
        // Find carpark with id
        Long carParkDto = dto.getCarPark();
        if(carParkDto != null){
            CarParkService carParkService = new CarParkService();
            carParkFloor.setCarPark(carParkService.getCarPark(carParkDto));
        }else{
            //TODO: dorobit ked je carpark id null
        }
        if(!parkingSpotDtoList.isEmpty()){
            ParkingSpotRequestFactory parkingSpotResponseFactory = new ParkingSpotRequestFactory();
            carParkFloor.setParkingSpots(parkingSpotDtoList.stream().map(parkingSpotResponseFactory::transformToEntity).collect(Collectors.toList()));
        }else{
            carParkFloor.setParkingSpots(new ArrayList<>());
        }
        return carParkFloor;
    }
}
