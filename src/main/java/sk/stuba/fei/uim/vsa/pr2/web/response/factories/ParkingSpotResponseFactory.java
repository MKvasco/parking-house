package sk.stuba.fei.uim.vsa.pr2.web.response.factories;

import sk.stuba.fei.uim.vsa.pr2.domain.ParkingSpot;
import sk.stuba.fei.uim.vsa.pr2.web.response.ParkingSpotDto;

public class ParkingSpotResponseFactory implements ResponseFactory<ParkingSpot, ParkingSpotDto> {
    @Override
    public ParkingSpotDto transformToDto(ParkingSpot entity) {
        return null;
    }

    @Override
    public ParkingSpot transformToEntity(ParkingSpot dot) {
        return null;
    }
}
