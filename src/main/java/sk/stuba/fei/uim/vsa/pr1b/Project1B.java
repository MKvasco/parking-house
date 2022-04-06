package sk.stuba.fei.uim.vsa.pr1b;

import sk.stuba.fei.uim.vsa.pr1b.entities.CarPark;

public class Project1B {

    public static void main(String[] args) {

        //  CREATE carparkservice OKAY
        CarParkService carParkService = new CarParkService();

        //  CREATE carpark OKAY
        Object cp = carParkService.createCarPark("Aupark", "Petrzalka", 2);
        Object cp1 = carParkService.createCarPark("Vivo", "Nove-Mesto", 3);

        //  GET carpark with carpark ID OKAY
//        System.out.println(carParkService.getCarPark(1L));

        //  GET carpark with carpark name OKAY (Case INSENSIBLE)
//        System.out.println(carParkService.getCarPark("Aupark"));

        //  GET all carparks OKAY
//        System.out.println(carParkService.getCarParks());

        //TODO: UPDATE carpark
//        Object carPark = carParkService.createCarPark("")
//        System.out.println(carParkService.updateCarPark());

        //  DELETE carpark with carparkId OKAY
//        System.out.println(carParkService.deleteCarPark(2L));
    }

}
