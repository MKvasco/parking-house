package sk.stuba.fei.uim.vsa.pr2.web.response.factories;

import sk.stuba.fei.uim.vsa.pr2.domain.Car;
import sk.stuba.fei.uim.vsa.pr2.web.response.CarDto;

public class CarResponseFactory implements ResponseFactory<Car, CarDto> {
    @Override
    public CarDto transformToDto(Car entity) {
        return null;
    }

    @Override
    public Car transformToEntity(CarDto dto) {
        return null;
    }
}
