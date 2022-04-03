package sk.stuba.fei.uim.vsa.pr1b;

import sk.stuba.fei.uim.vsa.pr1b.entities.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.*;
import java.util.stream.Collectors;

public class CarParkService extends AbstractCarParkService{
    private final EntityManager em;
    private final EntityTransaction et;

    protected CarParkService() {
        super();
        this.em = emf.createEntityManager();
        this.et = em.getTransaction();
    }

    @Override
    protected void close() {
        super.close();
    }

    // CAR PARK
    @Override
    public Object createCarPark(String name, String address, Integer pricePerHour) {
        et.begin();
        CarPark carPark = new CarPark(name, address, pricePerHour);
        em.persist(carPark);
        et.commit();
        return carPark;
    }

    @Override
    public Object getCarPark(Long carParkId) {
        try{
            return em.createNamedQuery(CarPark.Queries.findById, CarPark.class)
                    .setParameter("id", carParkId)
                    .getSingleResult();
        }catch (NoResultException e){
            return null;
        }
    }

    @Override
    public Object getCarPark(String carParkName) {
        try{
            return em.createNamedQuery(CarPark.Queries.findByName, CarPark.class)
                    .setParameter("name", carParkName)
                    .getSingleResult();
        }catch (NoResultException e){
            return null;
        }
    }

    @Override
    public List<Object> getCarParks() {
        return this.em.createNamedQuery(CarPark.Queries.findAll, CarPark.class)
                .getResultStream().collect(Collectors.toList());
    }

    //TODO UPDATE CAR PARK
    @Override
    public Object updateCarPark(Object carPark) {
        return null;
    }

    @Override
    public Object deleteCarPark(Long carParkId) {
        Object deletedCarPark = getCarPark(carParkId);
        if(deletedCarPark == null) return null;
            else{
                et.begin();
                em.createNamedQuery(CarPark.Queries.deleteById, CarPark.class)
                        .setParameter("id", carParkId)
                        .executeUpdate();
                et.commit();
                return deletedCarPark;
            }
    }

    // CAR PARK FLOOR
    @Override
    public Object createCarParkFloor(Long carParkId, String floorIdentifier) {
        try{
            CarPark carPark = em.createNamedQuery(CarPark.Queries.findById, CarPark.class)
                    .setParameter("id",carParkId)
                    .getSingleResult();
            et.begin();
            CarParkFloor carParkFloor = new CarParkFloor(carPark, floorIdentifier);
            em.persist(carParkFloor);
            et.commit();
            return carParkFloor;
        }catch(NoResultException e){
            return null;
        }
    }

//    @Override
//    public Object getCarParkFloor(Long carParkId, String floorIdentifier) {
//        try{
//            CarParkFloor carParkFloor = null;
//            CarPark carPark = em.createNamedQuery(CarPark.Queries.findById, CarPark.class)
//                    .setParameter("id",carParkId)
//                    .getSingleResult();
//            List<CarParkFloor> carParkFloors = carPark.getCarParkFloors();
//            for(CarParkFloor floor : carParkFloors) {
//                if(Objects.equals(floor.getFloorIdentifier(), floorIdentifier)){
//                    carParkFloor = floor;
//                }
//            }
//            return  carParkFloor;
//        }catch(NoResultException e) {
//            return null;
//        }
//    }

    @Override
    public Object getCarParkFloor(Long carParkFloorId) {
        try{
            return em.createNamedQuery(CarParkFloor.Queries.findById, CarParkFloor.class)
                    .setParameter("id", carParkFloorId)
                    .getSingleResult();
        }catch(NoResultException e){
            return null;
        }
    }

    @Override
    public List<Object> getCarParkFloors(Long carParkId) {
        try{
            CarPark carPark = em.createNamedQuery(CarPark.Queries.findById, CarPark.class)
                    .setParameter("id",carParkId)
                    .getSingleResult();
            return Collections.singletonList(carPark.getCarParkFloors());
        }catch(NoResultException e){
            return null;
        }
    }

    //TODO: UPDATE CAR PARK FLOOR
    @Override
    public Object updateCarParkFloor(Object carParkFloor) {
        return null;
    }

    @Override
    public Object deleteCarParkFloor(Long carParkId, String floorIdentifier) {
        try{
            CarParkFloor carParkFloor = null;
            CarPark carPark = em.createNamedQuery(CarPark.Queries.findById, CarPark.class)
                    .setParameter("id",carParkId)
                    .getSingleResult();
            for(CarParkFloor floor : carPark.getCarParkFloors()){
                if(Objects.equals(floor.getFloorIdentifier(), floorIdentifier)){
                    et.begin();
                    em.createNamedQuery(CarParkFloor.Queries.deleteById, CarParkFloor.class)
                            .setParameter("id", floor.getId())
                            .executeUpdate();
                    et.commit();
                    carParkFloor = floor;
                }
            }
            return carParkFloor;
        }catch(NoResultException e){
            return null;
        }
    }

    @Override
    public Object deleteCarParkFloor(Long carParkFloorId) {
        try{
            return em.createNamedQuery(CarParkFloor.Queries.deleteById, CarParkFloor.class)
                    .setParameter("id", carParkFloorId)
                    .getSingleResult();
        }catch(NoResultException e){
            return null;
        }
    }

    //PARKING SPOT
    @Override
    public Object createParkingSpot(Long carParkId, String floorIdentifier, String spotIdentifier) {
        try{
            ParkingSpot parkingSpot = null;
            CarPark carPark = em.createNamedQuery(CarPark.Queries.findById, CarPark.class)
                    .setParameter("id", carParkId)
                    .getSingleResult();
            List<CarParkFloor> carParkFloors = carPark.getCarParkFloors();
            for(CarParkFloor floor : carParkFloors){
                if(Objects.equals(floor.getFloorIdentifier(), floorIdentifier)){
                    parkingSpot = new ParkingSpot(floor);
                }
            }
            return parkingSpot;
        }catch (NoResultException e){
            return null;
        }
    }
    @Override
    public Object getParkingSpot(Long parkingSpotId) {
        try{
            return em.createNamedQuery(ParkingSpot.Queries.findById, ParkingSpot.class)
                    .setParameter("id", parkingSpotId)
                    .getSingleResult();
        }catch(NoResultException e){
            return null;
        }
    }
    @Override
    public List<Object> getParkingSpots(Long carParkId, String floorIdentifier) {
        try{
            List<ParkingSpot> parkingSpots = null;
            List<CarParkFloor> carParkFloors = em.createNamedQuery(CarPark.Queries.findById, CarPark.class)
                    .setParameter("id", carParkId)
                    .getSingleResult()
                    .getCarParkFloors();
            for(CarParkFloor floor : carParkFloors){
                if(Objects.equals(floor.getFloorIdentifier(), floorIdentifier)){
                    parkingSpots = floor.getParkingSpots();
                }
            }
            return Collections.singletonList(parkingSpots);
        }catch(NoResultException e){
            return null;
        }
    }
    @Override
    public Map<String, List<Object>> getParkingSpots(Long carParkId) {
        try{
            List<CarParkFloor> carParkFloors = em.createNamedQuery(CarPark.Queries.findById, CarPark.class)
                    .setParameter("id", carParkId)
                    .getSingleResult()
                    .getCarParkFloors();
            Map<String, List<Object>> parkingSpots = new HashMap<>();
            for(CarParkFloor floor : carParkFloors){
                parkingSpots.put(floor.getFloorIdentifier(), Collections.singletonList(floor.getParkingSpots()));
            }
            return parkingSpots;

        }catch(NoResultException e){
            return null;
        }
    }
    @Override
    public Map<String, List<Object>> getAvailableParkingSpots(String carParkName) {
        try{
            List<CarParkFloor> carParkFloors = em.createNamedQuery(CarPark.Queries.findByName, CarPark.class)
                    .setParameter("name", carParkName)
                    .getSingleResult()
                    .getCarParkFloors();
            Map<String, List<Object>> parkingSpots = new HashMap<>();
            for(CarParkFloor floor : carParkFloors){
                List<ParkingSpot> parkingSpotsList = floor.getParkingSpots();
                List<ParkingSpot> availableParkingSpots = new ArrayList<>();
                for(ParkingSpot parkingSpot : parkingSpotsList){
                    if(parkingSpot.isAvailable())
                        availableParkingSpots.add(parkingSpot);
                }
                parkingSpots.put(floor.getFloorIdentifier(), Collections.singletonList(availableParkingSpots));
            }
            return parkingSpots;

        }catch(NoResultException e){
            return null;
        }
    }
    @Override
    public Map<String, List<Object>> getOccupiedParkingSpots(String carParkName) {
        try{
            List<CarParkFloor> carParkFloors = em.createNamedQuery(CarPark.Queries.findByName, CarPark.class)
                    .setParameter("name", carParkName)
                    .getSingleResult()
                    .getCarParkFloors();
            Map<String, List<Object>> occupiedParkingSpotsMap = new HashMap<>();
            for(CarParkFloor floor : carParkFloors){
                List<ParkingSpot> parkingSpotList = floor.getParkingSpots();
                List<ParkingSpot> occupiedParkingSpots = new ArrayList<>();
                for(ParkingSpot parkingSpot : parkingSpotList){
                    if(!parkingSpot.isAvailable()){
                        occupiedParkingSpots.add(parkingSpot);
                    }
                }
                occupiedParkingSpotsMap.put(floor.getFloorIdentifier(), Collections.singletonList(occupiedParkingSpots));
            }
            return occupiedParkingSpotsMap;
        }catch(NoResultException e){
            return null;
        }
    }

    //TODO: UPDATE PARKING SPOT
    @Override
    public Object updateParkingSpot(Object parkingSpot) {
        try {
            return null;
        } catch (NoResultException e) {
            return null;
        }
    }
    @Override
    public Object deleteParkingSpot(Long parkingSpotId) {
        try{
            return em.createNamedQuery(ParkingSpot.Queries.deleteById, ParkingSpot.class)
                    .setParameter("id", parkingSpotId)
                    .getSingleResult();
        }catch(NoResultException e){
            return null;
        }
    }

    //CAR
    @Override
    public Object createCar(Long userId, String brand, String model, String colour, String vehicleRegistrationPlate) {
        try{
            User user = em.createNamedQuery(User.Queries.findById, User.class)
                    .setParameter("id", userId)
                    .getSingleResult();
            Car car = new Car(brand, model, colour, vehicleRegistrationPlate);
            car.setUser(user);
            return car;
        }catch(NoResultException e){
            return null;
        }
    }
    @Override
    public Object getCar(Long carId) {
        try{
            return em.createNamedQuery(Car.Queries.findById, Car.class)
                    .setParameter("id", carId)
                    .getSingleResult();
        }catch(NoResultException e){
            return null;
        }
    }
    @Override
    public Object getCar(String vehicleRegistrationPlate) {
        try{

        List<Car> carList = em.createNamedQuery(Car.Queries.findAll, Car.class)
                .getResultList();
        for(Car car : carList){
            if(Objects.equals(car.getVehicleRegistrationPlate(), vehicleRegistrationPlate)){
                return car;
            }
        }
        }catch(NoResultException e){
            return null;
        }
        return null;
    }
    //TODO
    @Override
    public List<Object> getCars(Long userId) {
        return null;
    }
    //TODO
    @Override
    public Object updateCar(Object car) {
        return null;
    }
    //TODO
    @Override
    public Object deleteCar(Long carId) {
        return null;
    }

    //TODO USER
    @Override
    public Object createUser(String firstname, String lastname, String email) {
        return null;
    }
    //TODO
    @Override
    public Object getUser(Long userId) {
        return null;
    }
    //TODO
    @Override
    public Object getUser(String email) {
        return null;
    }
    //TODO
    @Override
    public List<Object> getUsers() {
        return null;
    }
    //TODO
    @Override
    public Object updateUser(Object user) {
        return null;
    }
    //TODO
    @Override
    public Object deleteUser(Long userId) {
        return null;
    }

    //TODO RESERVATION
    @Override
    public Object createReservation(Long parkingSpotId, Long cardId) {
        return null;
    }
    //TODO
    @Override
    public Object endReservation(Long reservationId) {
        return null;
    }
    //TODO
    @Override
    public List<Object> getReservations(Long parkingSpotId, Date date) {
        return null;
    }
    //TODO
    @Override
    public List<Object> getMyReservations(Long userId) {
        return null;
    }
    //TODO
    @Override
    public Object updateReservation(Object reservation) {
        return null;
    }

    //TODO CARTYPES
    @Override
    public Object createCarType(String name) {
        return null;
    }
    //TODO
    @Override
    public List<Object> getCarTypes() {
        return null;
    }
    //TODO
    @Override
    public Object getCarType(Long carTypeId) {
        return null;
    }
    //TODO
    @Override
    public Object getCarType(String name) {
        return null;
    }
    //TODO
    @Override
    public Object deleteCarType(Long carTypeId) {
        return null;
    }
    //TODO
    @Override
    public Object createCar(Long userId, String brand, String model, String colour, String vehicleRegistrationPlate, Long carTypeId) {
        return null;
    }
    //TODO
    @Override
    public Object createParkingSpot(Long carParkId, String floorIdentifier, String spotIdentifier, Long carTypeId) {
        return null;
    }
}
