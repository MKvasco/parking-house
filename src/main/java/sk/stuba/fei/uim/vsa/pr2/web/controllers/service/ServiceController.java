package sk.stuba.fei.uim.vsa.pr2.web.controllers.service;

import sk.stuba.fei.uim.vsa.pr2.domain.*;

import java.util.List;

public class ServiceController {

    private final CarParkService carParkService;
    private final CarParkFloorService carParkFloorService;
    private final ParkingSpotService parkingSpotService;
    private final CarTypeService carTypeService;
    private final UserService userService;
    private final CarService carService;
    private final ReservationService reservationService;

    public ServiceController(){
        this.carParkService = new CarParkService();
        this.carParkFloorService = new CarParkFloorService();
        this.parkingSpotService = new ParkingSpotService();
        this.carTypeService = new CarTypeService();
        this.userService = new UserService();
        this.carService = new CarService();
        this.reservationService = new ReservationService();
    }
    public CarPark createCarPark(String name, String address, Integer price) {
        return carParkService.createCarPark(name, address, price);
    }

    public CarParkFloor createCarParkFloor(String identifier, CarPark carPark, List<ParkingSpot> spots){
        CarParkFloor carParkFloor = carParkFloorService.createCarParkFloor(carPark.getId(), identifier);
        carParkFloor.setParkingSpots(spots);
        return carParkFloor;
    }

    public ParkingSpot createParkingSpot(Long carParkId, String floorIdentifier, String spotIdentifier, Long carTypeId){
        return parkingSpotService.createParkingSpot(carParkId, floorIdentifier, spotIdentifier, carTypeId);
    }

    public CarType createCarType(String name){
        return carTypeService.createCarType(name);
    }

    public User createUser(String firstname, String lastname, String email){
        return userService.createUser(firstname, lastname, email);
    }

    public Car createCar(Long userId, String brand, String model, String colour, String vrp, Long carTypeId ){
        return carService.createCar(userId, brand, model, colour, vrp, carTypeId);
    }
    public Reservation createReservation(Long parkingSpotId, Long carId){
        return reservationService.createReservation(parkingSpotId, carId);
    }
}
