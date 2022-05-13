package sk.stuba.fei.uim.vsa.pr2.web.response.factories;

import sk.stuba.fei.uim.vsa.pr2.domain.Car;
import sk.stuba.fei.uim.vsa.pr2.domain.CarType;
import sk.stuba.fei.uim.vsa.pr2.domain.Reservation;
import sk.stuba.fei.uim.vsa.pr2.domain.User;
import sk.stuba.fei.uim.vsa.pr2.web.controllers.service.CarService;
import sk.stuba.fei.uim.vsa.pr2.web.controllers.service.UserService;
import sk.stuba.fei.uim.vsa.pr2.web.response.CarWithUsersDto;
import sk.stuba.fei.uim.vsa.pr2.web.response.ReservationDto;
import sk.stuba.fei.uim.vsa.pr2.web.response.UserWithCarsDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CarWithUsersFactory implements Factory<Car, CarWithUsersDto> {
    @Override
    public CarWithUsersDto transformToDto(Car entity) {
        CarWithUsersDto carWithUsersDto = new CarWithUsersDto();
        carWithUsersDto.setId(entity.getId());
        carWithUsersDto.setModel(entity.getModel());
        carWithUsersDto.setBrand(entity.getBrand());
        carWithUsersDto.setColour(entity.getColour());
        carWithUsersDto.setVrp(entity.getVehicleRegistrationPlate());
        carWithUsersDto.setCarType(new CarTypeFactory().transformToDto(entity.getCarType()));
        carWithUsersDto.setUser(new UserWithCarsFactory().transformToDto(entity.getUser()));

        ReservationFactory reservationFactory = new ReservationFactory();
        carWithUsersDto.setReservations(entity.getReservations().stream().map(reservationFactory::transformToDto).collect(Collectors.toList()));

        return carWithUsersDto;
    }

    @Override
    public Car transformToEntity(CarWithUsersDto dto) {
        String brand = dto.getBrand();
        String colour = dto.getColour();
        String model = dto.getModel();
        String vrp = dto.getVrp();

        User user = new UserService().getUser(dto.getOwner().getEmail());
        if(user == null) return null;

        CarType carType = new CarTypeFactory().transformToEntity(dto.getType());

        Car car = new CarService().createCar(user.getId(), brand, model, colour, vrp, carType.getId());
        if(car == null) return null;
        car.setUser(new UserWithCarsFactory().transformToEntity(dto.getOwner()));

        List<ReservationDto> reservationDtoList = dto.getReservations();
        if(reservationDtoList == null) reservationDtoList = new ArrayList<>();
        if(!reservationDtoList.isEmpty()){
            ReservationFactory reservationFactory = new ReservationFactory();
            car.setReservations(reservationDtoList.stream().map(reservationFactory::transformToEntity).collect(Collectors.toList()));
        }
        return car;
    }
}
