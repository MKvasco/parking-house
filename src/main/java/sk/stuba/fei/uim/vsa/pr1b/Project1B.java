package sk.stuba.fei.uim.vsa.pr1b;

import sk.stuba.fei.uim.vsa.pr1b.entities.*;

import java.util.Date;

public class Project1B {

    public static void main(String[] args) {

        //  CREATE carparkservice OKAY
//        CarParkService carParkService = new CarParkService();
//
//        //  CREATE carpark OKAY
//        Object cp = carParkService.createCarPark("Aupark", "Petrzalka", 2);
//        Object cp1 = carParkService.createCarPark("Vivo", "Nove-Mesto", 3);
//
//        //CARPARK
//        //  GET carpark ID OKAY
////        System.out.println(carParkService.getCarPark(1L));
//
//        //  GET carpark with carpark name OKAY (Case INSENSIBLE)
////        System.out.println(carParkService.getCarPark("Aupark"));
//
//        //  GET all carparks OKAY
////        System.out.println(carParkService.getCarParks());
//
//        //  UPDATE carpark
////        Object carPark = carParkService.createCarPark("")
////        CarPark testcp = (CarPark) carParkService.getCarPark(1L);
////        System.out.println(testcp);
//        //      Name change
////        testcp.setName("Change");
////        System.out.println(testcp);
//        //      Address change
////        testcp.setAddress("Riecna 7, Bratislava");
////        System.out.println(carParkService.updateCarPark(testcp));
//        //      Price per hour change
////        testcp.setPricePerHour(3);
////        System.out.println(carParkService.updateCarPark(testcp));
//
//        //  DELETE carpark with carparkId OKAY
////        System.out.println(carParkService.deleteCarPark(2L));
//
//        //CARPARKFLOOR
////        //  CREATE , name must be unique in carpark
//        Object cpf = carParkService.createCarParkFloor(1L,"Prve Poschodnie");
//        Object cpf1 = carParkService.createCarParkFloor(1L,"Druhe Poschodnie");
//        Object cpf2 = carParkService.createCarParkFloor(2L,"Prve Poschodnie");
////        System.out.println(cpf);
////        System.out.println(cpf2);
//
//    //      GET by ID
////        System.out.println(carParkService.getCarParkFloor(3L));
//
//        //  GET all floors in CARPARK
////        System.out.println(carParkService.getCarParkFloors(3L));
//        //  UPDATE
////        CarParkFloor carParkFloor = (CarParkFloor) carParkService.getCarParkFloor(4L);
////        carParkFloor.setFloorIdentifier("Change");
////        carParkService.updateCarParkFloor(carParkFloor);
//        //  DELETE
////        System.out.println(carParkService.deleteCarParkFloor(1L, "Prve Poschodnie"));
////        System.out.println(carParkService.deleteCarParkFloor(4L));
//        //CARTYPE
//        //CREATE
//        Object carTypeBenzin = carParkService.createCarType("Benzin");
//        Object carTypeNafta = carParkService.createCarType("Nafta");
//        Object carTypeElektro = carParkService.createCarType("Elektro");
//        Object carTypeLPG = carParkService.createCarType("LPG");
//
//        //GET ALL
////        System.out.println(carParkService.getCarTypes());
//
//        //GET BY ID
////        System.out.println(carParkService.getCarType(11L));
//
//        //GET BY NAME
////        System.out.println(carParkService.getCarType("LPG"));
//
//        //DELETE CARTYPE
////        System.out.println(carParkService.deleteCarType(9L));
//
//        //PARKING SPOT
//        //CREATE
//        Object ps = carParkService.createParkingSpot(1L, "Prve Poschodnie", "1", 9L);
//        Object ps1 = carParkService.createParkingSpot(1L, "Prve Poschodnie", "2", 9L);
//        Object ps2 = carParkService.createParkingSpot(1L, "Druhe Poschodnie", "3", 9L);
//        Object ps3 = carParkService.createParkingSpot(1L, "Prve Poschodnie", "4", 8L);
//
//        //GET BY ID
////        System.out.println(carParkService.getParkingSpot(10L));
//        //GET ALL ON FLOOR
////        System.out.println(carParkService.getParkingSpots(4L, "Prve Poschodnie"));
//        //GET ALL IN CARPARK
////        System.out.println(carParkService.getParkingSpots(1L));
//        //TODO: GET AVAILABLE
//        //TODO: GET OCCUPIED
//        //UPDATE
////        ((ParkingSpot) ps).setIdentifier("ChangeHere");
////        System.out.println(carParkService.updateParkingSpot(ps));
//        //DELETE
////        System.out.println(carParkService.deleteParkingSpot(13L));
//        //USER
//        //CREATE
//        Object marco = carParkService.createUser("Marco", "Kvasnica", "Ahoj@ahoj.sme");
//        Object klara =carParkService.createUser("Klara", "Vizarova", "Ahoj@ahdoj.sme");
//
//        //GET BY ID AND MAIL
////        System.out.println(carParkService.getUser("ahoj@ahoj.smee"));
//        //GET ALL
////        System.out.println(carParkService.getUsers());
//        //UPDATE
////        ((User) marco).setEmail("marco.kvasnica@gmail.com");
////        System.out.println(carParkService.getUsers());
//        //DELETE
////        System.out.println(carParkService.deleteUser(13L));
//
//        //CAR
//        //CREATE
//        Object mustang = carParkService.createCar(14L, "Ford", "Mustang", "Black","SC365EV" ,8L);
//        Object cls = carParkService.createCar(14L, "Mercedes", "CLS", "Black","SC365CV" ,8L);
//
//
//        //GET BY ID
////        System.out.println(carParkService.getCar(16L));
//        //GET BY ECV
////        System.out.println(carParkService.getCar("SC365EV"));
//        //GET BY USERID
////        System.out.println(carParkService.getCars(23L));
//        //DELETE
////        System.out.println(carParkService.getCars(13L));
////        System.out.println(carParkService.deleteCar(16L));
////        System.out.println(carParkService.getCars(13L));
//
//        //RESERVATION
//        //CREATE
//        Object reservation = carParkService.createReservation(13L, 17L);
//        //END
////        System.out.println(carParkService.endReservation(18L));
////
////        System.out.println(((Reservation) reservation).getParkingSpot().isAvailable());
//        //GET user reservations
////        System.out.println(carParkService.getMyReservations(15L));
////        System.out.println(carParkService.getReservations(13L, new Date()));
////        System.out.println(carParkService.getOccupiedParkingSpots("Aupark"));

    }

}
