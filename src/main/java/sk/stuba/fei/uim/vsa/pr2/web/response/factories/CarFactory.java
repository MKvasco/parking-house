package sk.stuba.fei.uim.vsa.pr2.web.response.factories;

import sk.stuba.fei.uim.vsa.pr2.domain.Car;
import sk.stuba.fei.uim.vsa.pr2.domain.CarType;
import sk.stuba.fei.uim.vsa.pr2.domain.Reservation;
import sk.stuba.fei.uim.vsa.pr2.domain.User;
import sk.stuba.fei.uim.vsa.pr2.web.controllers.service.CarService;
import sk.stuba.fei.uim.vsa.pr2.web.response.CarDto;
import sk.stuba.fei.uim.vsa.pr2.web.response.ReservationDto;

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

        User user = entity.getUser();
        carDto.setOwner(new UserWithCarsFactory().transformToDto(user));
        carDto.setType(new CarTypeFactory().transformToDto(entity.getCarType()));

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
        String model = dto.getModel();
        String brand = dto.getBrand();
        String vrp = dto.getVrp();
        String colour = dto.getColour();
        CarType carType = new CarTypeFactory().transformToEntity(dto.getType());
        User user = new UserWithCarsFactory().transformToEntity(dto.getOwner());
        Car car = new CarService().createCar(user.getId(), brand, model, colour, vrp, carType.getId());
        if(car == null) return null;

        List <ReservationDto> reservationDtoList = dto.getReservations();
        if(reservationDtoList == null) reservationDtoList = new ArrayList<>();
        if(!reservationDtoList.isEmpty()){
            ReservationFactory reservationFactory = new ReservationFactory();
            car.setReservations(dto.getReservations().stream().map(reservationFactory::transformToEntity).collect(Collectors.toList()));

        }
        return car;
    }
}
