package sk.stuba.fei.uim.vsa.pr2.service;

import sk.stuba.fei.uim.vsa.pr2.domain.CarPark;

public class ServiceController {

    private final CarParkService carParkService;

    public ServiceController(){
        this.carParkService = new CarParkService();
    }
    public CarPark createCarPark(String name, String address, Integer price) {
        return carParkService.createCarPark(name, address, price);
    }
}
