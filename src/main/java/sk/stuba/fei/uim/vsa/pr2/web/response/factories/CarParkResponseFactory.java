package sk.stuba.fei.uim.vsa.pr2.web.response.factories;

import sk.stuba.fei.uim.vsa.pr2.domain.CarPark;
import sk.stuba.fei.uim.vsa.pr2.web.response.CarParkDto;

public class CarParkResponseFactory implements ResponseFactory<CarPark, CarParkDto> {
    @Override
    public CarParkDto transformToDto(CarPark entity) {
        return null;
    }

    @Override
    public CarPark transformToEntity(CarPark dot) {
        return null;
    }
}
