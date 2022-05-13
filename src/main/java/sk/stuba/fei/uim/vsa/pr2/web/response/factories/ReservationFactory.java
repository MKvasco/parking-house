package sk.stuba.fei.uim.vsa.pr2.web.response.factories;

import sk.stuba.fei.uim.vsa.pr2.domain.Reservation;
import sk.stuba.fei.uim.vsa.pr2.web.response.ParkingSpotDto;
import sk.stuba.fei.uim.vsa.pr2.web.response.ReservationDto;

import java.util.ArrayList;

public class ReservationFactory implements Factory<Reservation, ReservationDto> {
    @Override
    public ReservationDto transformToDto(Reservation entity) {
        ReservationDto reservationDto = new ReservationDto();
        reservationDto.setId(entity.getId());
        reservationDto.setStart(entity.getStartTime());
        reservationDto.setEnd(entity.getEndTime());
        reservationDto.setPrices(entity.getPrice());
        reservationDto.setCar(entity.getCar().getId());
        reservationDto.setSpot(entity.getParkingSpot().getId());
        return reservationDto;
    }

    @Override
    public Reservation transformToEntity(ReservationDto dto) {
        return null;
    }
}
