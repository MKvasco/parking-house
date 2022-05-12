package sk.stuba.fei.uim.vsa.pr2.web.response.factories;

import sk.stuba.fei.uim.vsa.pr2.domain.Car;
import sk.stuba.fei.uim.vsa.pr2.domain.Reservation;
import sk.stuba.fei.uim.vsa.pr2.web.response.CarDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CarFactory implements Factory<Car, CarDto> {
    @Override
    public CarDto transformToDto(Car entity) {
        CarDto carDto = new CarDto();
        carDto.setId(entity.getId());
        carDto.setBrand(entity.getBrand());
        carDto.setModel(entity.getModel());
        carDto.setVrp(entity.getVehicleRegistrationPlate());
        carDto.setColour(entity.getColour());
        carDto.setUser(null);
        carDto.setUser(entity.getUser().getId());

        carDto.setCarType(new CarTypeFactory().transformToDto(entity.getCarType()));

        List<Reservation> reservations = entity.getReservations();
        if(!reservations.isEmpty()){
            ReservationFactory reservationFactory = new ReservationFactory();
            carDto.setReservations(reservations.stream().map(reservationFactory::transformToDto).collect(Collectors.toList()));
        }else{
            carDto.setReservations(new ArrayList<>());
        }
        return carDto;
    }

    @Override
    public Car transformToEntity(CarDto dto) {
        return null;
    }
}
