package sk.stuba.fei.uim.vsa.pr2.web.request.factory;

import sk.stuba.fei.uim.vsa.pr2.domain.Reservation;
import sk.stuba.fei.uim.vsa.pr2.web.response.ReservationDto;

public class ReservationRequestFactory implements RequestFactory<Reservation, ReservationDto> {
    @Override
    public ReservationDto transformToDto(Reservation entity) {
        return null;
    }

    @Override
    public Reservation transformToEntity(ReservationDto dto) {
        return null;
    }
}
