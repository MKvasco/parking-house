package sk.stuba.fei.uim.vsa.pr1b;

import sk.stuba.fei.uim.vsa.pr1b.entities.Car;
import sk.stuba.fei.uim.vsa.pr1b.entities.CarPark;
import sk.stuba.fei.uim.vsa.pr1b.entities.CarParkFloor;
import sk.stuba.fei.uim.vsa.pr1b.entities.CarType;

public class Project1B {

    public static void main(String[] args) {

        //  CREATE carparkservice OKAY
        CarParkService carParkService = new CarParkService();

        //  CREATE carpark OKAY
        Object cp = carParkService.createCarPark("Aupark", "Petrzalka", 2);
        Object cp1 = carParkService.createCarPark("Vivo", "Nove-Mesto", 3);

        //CARPARK
        //  GET carpark ID OKAY
//        System.out.println(carParkService.getCarPark(1L));

        //  GET carpark with carpark name OKAY (Case INSENSIBLE)
//        System.out.println(carParkService.getCarPark("Aupark"));

        //  GET all carparks OKAY
//        System.out.println(carParkService.getCarParks());

        //  UPDATE carpark
//        Object carPark = carParkService.createCarPark("")
//        CarPark testcp = (CarPark) carParkService.getCarPark(1L);
//        System.out.println(testcp);
        //      Name change
//        testcp.setName("Change");
//        System.out.println(testcp);
        //      Address change
//        testcp.setAddress("Riecna 7, Bratislava");
//        System.out.println(carParkService.updateCarPark(testcp));
        //      Price per hour change
//        testcp.setPricePerHour(3);
//        System.out.println(carParkService.updateCarPark(testcp));

        //  DELETE carpark with carparkId OKAY
//        System.out.println(carParkService.deleteCarPark(2L));

        //CARPARKFLOOR
        //  CREATE , name must be unique in carpark
        Object cpf = carParkService.createCarParkFloor(1L,"Prve Poschodnie");
        Object cpf1 = carParkService.createCarParkFloor(1L,"Druhe Poschodnie");
        Object cpf2 = carParkService.createCarParkFloor(2L,"Prve Poschodnie");
//        System.out.println(cpf);
//        System.out.println(cpf2);

    //      GET by ID
//        System.out.println(carParkService.getCarParkFloor(3L));

        //  GET all floors in CARPARK
//        System.out.println(carParkService.getCarParkFloors(3L));
        //  UPDATE
//        CarParkFloor carParkFloor = (CarParkFloor) carParkService.getCarParkFloor(4L);
//        carParkFloor.setFloorIdentifier("Change");
//        carParkService.updateCarParkFloor(carParkFloor);
        //  DELETE
//        System.out.println(carParkService.deleteCarParkFloor(1L, "Prve Poschodnie"));
//        System.out.println(carParkService.deleteCarParkFloor(4L));
    }

}
