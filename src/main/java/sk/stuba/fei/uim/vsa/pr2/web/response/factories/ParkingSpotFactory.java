package sk.stuba.fei.uim.vsa.pr2.web.response.factories;

import sk.stuba.fei.uim.vsa.pr2.domain.CarType;
import sk.stuba.fei.uim.vsa.pr2.domain.ParkingSpot;
import sk.stuba.fei.uim.vsa.pr2.domain.Reservation;
import sk.stuba.fei.uim.vsa.pr2.web.controllers.service.CarTypeService;
import sk.stuba.fei.uim.vsa.pr2.web.controllers.service.ParkingSpotService;
import sk.stuba.fei.uim.vsa.pr2.web.response.ParkingSpotDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ParkingSpotFactory implements Factory<ParkingSpot, ParkingSpotDto> {
    @Override
    public ParkingSpotDto transformToDto(ParkingSpot entity) {
        ParkingSpotDto parkingSpotDto = new ParkingSpotDto();
        if(entity.getCarParkFloor() == null) parkingSpotDto.setCarParkFloor(null);
        if(entity.getCarParkFloor() != null) parkingSpotDto.setCarParkFloor(entity.getCarParkFloor().getFloorIdentifier());
        if(entity.getCarParkFloor() == null || entity.getCarParkFloor().getCarPark() == null) parkingSpotDto.setCarPark(null);
        if(entity.getCarParkFloor() != null && entity.getCarParkFloor().getCarPark() != null) parkingSpotDto.setCarPark(entity.getCarParkFloor().getCarPark().getId());
        parkingSpotDto.setId(entity.getId());
        parkingSpotDto.setIdentifier(entity.getIdentifier());
        parkingSpotDto.setType(new CarTypeFactory().transformToDto(entity.getCarType()));
        parkingSpotDto.setFree(entity.isAvailable());

        List<Reservation> reservations = entity.getReservations();
        if(!reservations.isEmpty()){
            ReservationFactory reservationFactory = new ReservationFactory();
            parkingSpotDto.setReservations(reservations.stream().map(reservationFactory::transformToDto).collect(Collectors.toList()));
        }else{
            parkingSpotDto.setReservations(new ArrayList<>());
        }
        return parkingSpotDto;
    }

    @Override
    public ParkingSpot transformToEntity(ParkingSpotDto dto) {
        CarTypeService carTypeService = new CarTypeService();
        CarType carType = carTypeService.getCarType("WithoutRestriction");

        if(dto.getType() != null) {
            if(Objects.equals(dto.getType().getName(), "")){
                carType = carTypeService.createCarType("WithoutRestriction");
            }else{
                carType = new CarTypeFactory().transformToEntity(dto.getType());
            }
        }
        else if(carType == null){
            carType = carTypeService.createCarType("WithoutRestriction");
        }

        ParkingSpot parkingSpot = new ParkingSpot();
        if(dto.getCarPark() != null) parkingSpot = new ParkingSpotService().createParkingSpot(dto.getCarPark(), dto.getCarParkFloor(), dto.getIdentifier(), carType.getId());
        if(dto.getCarPark() == null) parkingSpot = new ParkingSpotService().createParkingSpot(dto.getCarParkFloor(), dto.getIdentifier(), carType.getId());
        if(parkingSpot == null) return null;
        if(parkingSpot.isAvailable() != null) parkingSpot.setAvailable(dto.getFree());
        if(parkingSpot.isAvailable() == null) parkingSpot.setAvailable(true);
        parkingSpot.setFloorIdentifier(dto.getIdentifier());
        return parkingSpot;
    }
}
