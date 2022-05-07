package sk.stuba.fei.uim.vsa.pr2.web.response.factories;

import sk.stuba.fei.uim.vsa.pr2.domain.CarParkFloor;
import sk.stuba.fei.uim.vsa.pr2.web.response.CarParkFloorDto;

public class CarParkFloorResponseFactory implements ResponseFactory<CarParkFloor, CarParkFloorDto>{
    @Override
    public CarParkFloorDto transformToDto(CarParkFloor entity) {
        return null;
    }

    @Override
    public CarParkFloor transformToEntity(CarParkFloor dot) {
        return null;
    }
}
