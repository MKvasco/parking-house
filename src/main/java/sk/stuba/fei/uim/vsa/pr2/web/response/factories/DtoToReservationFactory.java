package sk.stuba.fei.uim.vsa.pr2.web.response.factories;

import sk.stuba.fei.uim.vsa.pr2.domain.Car;
import sk.stuba.fei.uim.vsa.pr2.domain.ParkingSpot;
import sk.stuba.fei.uim.vsa.pr2.domain.Reservation;
import sk.stuba.fei.uim.vsa.pr2.web.controllers.service.CarService;
import sk.stuba.fei.uim.vsa.pr2.web.controllers.service.ParkingSpotService;
import sk.stuba.fei.uim.vsa.pr2.web.controllers.service.ReservationService;
import sk.stuba.fei.uim.vsa.pr2.web.response.DtoToReservationsDto;
import sk.stuba.fei.uim.vsa.pr2.web.response.ParkingSpotDto;
import sk.stuba.fei.uim.vsa.pr2.web.response.ReservationDto;

import java.util.ArrayList;

public class DtoToReservationFactory implements Factory<Reservation, DtoToReservationsDto> {
    @Override
    public DtoToReservationsDto transformToDto(Reservation entity) {
        DtoToReservationsDto reservationDto = new DtoToReservationsDto();
        reservationDto.setId(entity.getId());
        reservationDto.setStart(entity.getStartTime());
        reservationDto.setEnd(entity.getEndTime());
        reservationDto.setPrices(entity.getPrice());
        reservationDto.setSpot(new ParkingSpotFactory().transformToDto(entity.getParkingSpot()));
        reservationDto.setCar(new CarFactory().transformToDto(entity.getCar()));
        return reservationDto;
    }

    @Override
    public Reservation transformToEntity(DtoToReservationsDto dto) {
        Car car = new CarService().getCar(dto.getCar().getId());
        ParkingSpot spot = new ParkingSpotService().getParkingSpot(dto.getSpot().getId());
        if(car == null || spot == null) return null;
        return new ReservationService().createReservation(spot.getId(), car.getId());
    }
}
