package sk.stuba.fei.uim.vsa.pr2.web.response.factories;

import sk.stuba.fei.uim.vsa.pr2.domain.CarType;
import sk.stuba.fei.uim.vsa.pr2.service.CarTypeService;
import sk.stuba.fei.uim.vsa.pr2.web.response.CarTypeDto;

public class CarTypeFactory implements Factory<CarType, CarTypeDto> {

    @Override
    public CarTypeDto transformToDto(CarType entity) {
        CarTypeDto carType = new CarTypeDto();
        carType.setId(entity.getId());
        carType.setName(entity.getName());
        return carType;
    }

    @Override
    public CarType transformToEntity(CarTypeDto dto) {
        if(new CarTypeService().getCarType(dto.getName()) == null){
            return new CarTypeService().createCarType(dto.getName());
        }else{
            return new CarTypeService().getCarType(dto.getName());
        }

    }
}
