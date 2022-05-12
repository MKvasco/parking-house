package sk.stuba.fei.uim.vsa.pr2.web.response.factories;

import sk.stuba.fei.uim.vsa.pr2.domain.Reservation;
import sk.stuba.fei.uim.vsa.pr2.web.response.ReservationDto;

public class ReservationFactory implements Factory<Reservation, ReservationDto> {
    @Override
    public ReservationDto transformToDto(Reservation entity) {
        return null;
    }

    @Override
    public Reservation transformToEntity(ReservationDto dto) {
        return null;
    }
}
