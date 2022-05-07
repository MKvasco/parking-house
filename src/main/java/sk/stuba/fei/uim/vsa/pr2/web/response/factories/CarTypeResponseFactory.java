package sk.stuba.fei.uim.vsa.pr2.web.response.factories;

import sk.stuba.fei.uim.vsa.pr2.domain.CarType;
import sk.stuba.fei.uim.vsa.pr2.web.response.CarTypeDto;
import sk.stuba.fei.uim.vsa.pr2.web.response.Dto;

public class CarTypeResponseFactory implements ResponseFactory<CarType, CarTypeDto> {

    @Override
    public CarTypeDto transformToDto(CarType entity) {
        return null;
    }

    @Override
    public CarType transformToEntity(CarType dot) {
        return null;
    }
}
