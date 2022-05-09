package sk.stuba.fei.uim.vsa.pr2.web.request.factory;

import sk.stuba.fei.uim.vsa.pr2.domain.CarType;
import sk.stuba.fei.uim.vsa.pr2.domain.ParkingSpot;
import sk.stuba.fei.uim.vsa.pr2.domain.Reservation;
import sk.stuba.fei.uim.vsa.pr2.web.response.ParkingSpotDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ParkingSpotRequestFactory implements RequestFactory<ParkingSpot, ParkingSpotDto> {
    @Override
    public ParkingSpotDto transformToDto(ParkingSpot entity) {
        ParkingSpotDto parkingSpotDto = new ParkingSpotDto();
        Long id = entity.getId();
        String identifier = entity.getIdentifier();
        String carParkFloor = entity.getCarParkFloor().getFloorIdentifier();
        Long carPark = entity.getCarParkFloor().getCarPark().getId();
        CarType carType = entity.getCarType();
        Boolean free = entity.isAvailable();
        System.out.println(free);
        parkingSpotDto.setId(id);
        parkingSpotDto.setIdentifier(identifier);
        parkingSpotDto.setCarParkFloor(carParkFloor);
        parkingSpotDto.setCarPark(carPark);
        parkingSpotDto.setCarType(new CarTypeRequestFactory().transformToDto(carType));
        parkingSpotDto.setFree(free);

        List<Reservation> reservations = entity.getReservations();
        if(!reservations.isEmpty()){
            ReservationRequestFactory reservationResponseFactory = new ReservationRequestFactory();
            parkingSpotDto.setReservations(reservations.stream().map(reservationResponseFactory::transformToDto).collect(Collectors.toList()));
        }else{
            parkingSpotDto.setReservations(new ArrayList<>());
        }
        return parkingSpotDto;
    }

    @Override
    public ParkingSpot transformToEntity(ParkingSpotDto dto) {
        return null;
    }
}
