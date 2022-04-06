package sk.stuba.fei.uim.vsa.pr1b;

import sk.stuba.fei.uim.vsa.pr1b.entities.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.RollbackException;
import java.util.*;

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
        try{
            et.begin();
            CarPark carPark = new CarPark(name, address, pricePerHour);
            em.persist(carPark);
            et.commit();
            return carPark;
        }catch (RollbackException e){
            return null;
        }
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
        try{
            List<CarPark> carParkList = em.createNamedQuery(CarPark.Queries.findAll, CarPark.class)
                    .getResultList();
            return new ArrayList<>(carParkList);
        }catch (NoResultException e){
            return new ArrayList<>();
        }
    }

    @Override
    public Object updateCarPark(Object carPark) {
        try{
            et.begin();
            em.merge(carPark);
            et.commit();
            return em.createNamedQuery(CarPark.Queries.findById, CarPark.class)
                    .setParameter("id", ((CarPark) carPark).getId())
                    .getSingleResult();
        }catch (NoResultException | RollbackException e){
            return null;
        }
    }

    @Override
    public Object deleteCarPark(Long carParkId) {
        try{
            CarPark carPark = em.createNamedQuery(CarPark.Queries.findById, CarPark.class)
                    .setParameter("id", carParkId)
                    .getSingleResult();
            et.begin();
            em.createNamedQuery(CarPark.Queries.deleteById, CarPark.class)
                    .setParameter("id", carParkId)
                    .executeUpdate();
            et.commit();
            return carPark;
        }catch (NoResultException | RollbackException e){
            return null;
        }
    }

    // CAR PARK FLOOR
    @Override
    public Object createCarParkFloor(Long carParkId, String floorIdentifier) {
        try{
            CarPark carPark = em.createNamedQuery(CarPark.Queries.findById, CarPark.class)
                    .setParameter("id",carParkId)
                    .getSingleResult();
            List<CarParkFloor> carParkFloors = carPark.getCarParkFloors();
            for(CarParkFloor carParkFloor : carParkFloors){
                if(Objects.equals(carParkFloor.getFloorIdentifier(), floorIdentifier)){
                    return null;
                }
            }
            et.begin();
            CarParkFloor carParkFloor = new CarParkFloor(carPark, floorIdentifier);
            carPark.addCarParkFloor(carParkFloor);
            em.persist(carParkFloor);
            et.commit();
            return carParkFloor;
        }catch(NoResultException | RollbackException e){
            return null;
        }
    }

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
            return new ArrayList<>(carPark.getCarParkFloors());
        }catch(NoResultException e){
            return new ArrayList<>();
        }
    }

    @Override
    public Object updateCarParkFloor(Object carParkFloor) {
        try{
            et.begin();
            em.merge(carParkFloor);
            et.commit();
            return em.createNamedQuery(CarParkFloor.Queries.findById, CarParkFloor.class)
                    .setParameter("id", ((CarParkFloor) carParkFloor).getId())
                    .getSingleResult();
        }catch (RollbackException | NoResultException e){
            return null;
        }
    }

    @Override
    public Object deleteCarParkFloor(Long carParkId, String floorIdentifier) {
        try{
            CarParkFloor carParkFloor = null;
            CarPark carPark = em.createNamedQuery(CarPark.Queries.findById, CarPark.class)
                    .setParameter("id",carParkId)
                    .getSingleResult();
            for(CarParkFloor floor : carPark.getCarParkFloors()){
                System.out.println(floor.getFloorIdentifier());
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
        }catch(RollbackException | NoResultException e){
            return null;
        }
    }

    @Override
    public Object deleteCarParkFloor(Long carParkFloorId) {
        try{
            CarParkFloor carParkFloor = em.createNamedQuery(CarParkFloor.Queries.findById, CarParkFloor.class)
                    .setParameter("id", carParkFloorId)
                    .getSingleResult();
            et.begin();
            em.createNamedQuery(CarParkFloor.Queries.deleteById, CarParkFloor.class)
                    .setParameter("id", carParkFloorId)
                    .executeUpdate();
            et.commit();
            return carParkFloor;
        }catch(RollbackException | NoResultException e){
            return null;
        }
    }

    @Override
    public Object createParkingSpot(Long carParkId, String floorIdentifier, String spotIdentifier) {
        return null;
    }

    @Override
    public Object createParkingSpot(Long carParkId, String floorIdentifier, String spotIdentifier, Long carTypeId) {
        try{
            ParkingSpot parkingSpot = null;
            CarPark carPark = em.createNamedQuery(CarPark.Queries.findById, CarPark.class)
                    .setParameter("id", carParkId)
                    .getSingleResult();
            CarType carType = em.createNamedQuery(CarType.Queries.findById, CarType.class)
                    .setParameter("id", carTypeId)
                    .getSingleResult();
            List<CarParkFloor> carParkFloors = carPark.getCarParkFloors();
            for(CarParkFloor floor : carParkFloors){
                if(Objects.equals(floor.getFloorIdentifier(), floorIdentifier)){
                    et.begin();
                    parkingSpot = new ParkingSpot(floor, carType);
                    em.persist(parkingSpot);
                    et.commit();
                }
            }
            return parkingSpot;
        }catch (RollbackException | NoResultException e){
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

    @Override
    public Object updateParkingSpot(Object parkingSpot) {
        try {
            et.begin();
            em.merge(((ParkingSpot) parkingSpot));
            et.commit();
            return em.createNamedQuery(ParkingSpot.Queries.findById, ParkingSpot.class)
                    .setParameter("id", ((ParkingSpot) parkingSpot).getId())
                    .getSingleResult();
        } catch (RollbackException | NoResultException e) {
            return null;
        }
    }
    @Override
    public Object deleteParkingSpot(Long parkingSpotId) {
        try{
            return em.createNamedQuery(ParkingSpot.Queries.deleteById, ParkingSpot.class)
                    .setParameter("id", parkingSpotId)
                    .getSingleResult();
        }catch(RollbackException | NoResultException e){
            return null;
        }
    }

    @Override
    public Object createCar(Long userId, String brand, String model, String colour, String vehicleRegistrationPlate) {
        return null;
    }

    @Override
    public Object createCar(Long userId, String brand, String model, String colour, String vehicleRegistrationPlate, Long carTypeId) {
        try{
            User user = em.createNamedQuery(User.Queries.findById, User.class)
                    .setParameter("id", userId)
                    .getSingleResult();
            CarType carType = em.createNamedQuery(CarType.Queries.findById, CarType.class)
                    .setParameter("id", carTypeId)
                    .getSingleResult();
            et.begin();
            Car car = new Car(brand, model, colour, vehicleRegistrationPlate, carType);
            car.setUser(user);
            em.persist(car);
            et.commit();
            return car;
        }catch(RollbackException | NoResultException e){
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
    @Override
    public List<Object> getCars(Long userId) {
        try{
            User user = em.createNamedQuery(User.Queries.findById, User.class)
                .setParameter("id", userId)
                .getSingleResult();
            return Collections.singletonList(user.getCars());

        }catch(NoResultException e){
            return null;
        }
    }
    @Override
    public Object updateCar(Object car) {
        try{
            et.begin();
            em.merge(car);
            et.commit();
            return em.createNamedQuery(Car.Queries.findById, Car.class)
                    .setParameter("id", ((Car) car).getId())
                    .getSingleResult();
        }catch (NoResultException | RollbackException e){
            return null;
        }
    }
    @Override
    public Object deleteCar(Long carId) {
        try{
            Car deletedCar = em.createNamedQuery(Car.Queries.findById, Car.class)
                            .setParameter("id", carId)
                            .getSingleResult();
            et.begin();
            em.createNamedQuery(Car.Queries.deleteById, Car.class)
                    .setParameter("id", carId)
                    .executeUpdate();
            et.commit();
            return deletedCar;
        }catch(RollbackException | NoResultException e){
            return null;
        }
    }

    //USER
    @Override
    public Object createUser(String firstname, String lastname, String email) {
        try{
            et.begin();
            User user = new User(firstname, lastname, email);
            em.persist(user);
            et.commit();
            return user;
        }catch(RollbackException | NoResultException e){
            return null;
        }
    }
    @Override
    public Object getUser(Long userId) {
        try{
            return em.createNamedQuery(User.Queries.findById, User.class)
                    .setParameter("id", userId)
                    .getSingleResult();
        }catch (NoResultException e){
            return null;
        }
    }
    @Override
    public Object getUser(String email) {
        try{
            return em.createNamedQuery(User.Queries.findByEmail, User.class)
                    .setParameter("email", email)
                    .getSingleResult();
        }catch (NoResultException e){
            return null;
        }
    }
    @Override
    public List<Object> getUsers() {
        try{
            List<User> userList = em.createNamedQuery(User.Queries.findAll, User.class)
                    .getResultList();
            return new ArrayList<>(userList);
        }catch (NoResultException e){
            return null;
        }
    }
    @Override
    public Object updateUser(Object user) {
        try{
            et.begin();
            em.merge(user);
            et.commit();
            return em.createNamedQuery(User.Queries.findById, User.class)
                    .setParameter("id", ((User) user).getId())
                    .getSingleResult();
        }catch (NoResultException | RollbackException e){
            return null;
        }
    }

    @Override
    public Object deleteUser(Long userId) {
        try{
            User deletedUser = em.createNamedQuery(User.Queries.findById, User.class)
                    .setParameter("id", userId)
                    .getSingleResult();
            et.begin();
            em.createNamedQuery(User.Queries.deleteById, User.class)
                    .setParameter("id", userId)
                    .executeUpdate();
            et.commit();
            return deletedUser;
        }catch (RollbackException | NoResultException e){
            return null;
        }
    }

    @Override
    public Object createReservation(Long parkingSpotId, Long cardId) {
        try{
            Car car = em.createNamedQuery(Car.Queries.findById, Car.class)
                    .setParameter("id", cardId)
                    .getSingleResult();
            ParkingSpot parkingSpot = em.createNamedQuery(ParkingSpot.Queries.findById, ParkingSpot.class)
                    .setParameter("id", parkingSpotId)
                    .getSingleResult();
            if(!parkingSpot.isAvailable()) return null;
            if(parkingSpot.getCarType() != car.getCarType()) return null;
            et.begin();
            Reservation reservation = new Reservation(parkingSpot, cardId, new Date());
            em.persist(reservation);
            et.commit();
            return  reservation;
        }catch (RollbackException | NoResultException e){
            return null;
        }
    }
    @Override
    public Object endReservation(Long reservationId) {
        try{
            Reservation reservation = em.createNamedQuery(Reservation.Queries.findById, Reservation.class)
                    .setParameter("id", reservationId)
                    .getSingleResult();
            ParkingSpot parkingSpot = reservation.getParkingSpot();
            int price = parkingSpot.getCarParkFloor().getCarPark().getPricePerHour();
            long totalTime = new Date().getTime() - reservation.getStartTime().getTime();
            long totalHours = totalTime/1000/60/60;
            Float totalPrice = (float) Math.round(totalHours * price);
            et.begin();
            em.merge(reservation);
            et.commit();
            return reservation;
        }catch (NoResultException e){
            return null;
        }
    }

    @Override
    public List<Object> getReservations(Long parkingSpotId, Date date) {
        try{
            ParkingSpot parkingSpot = em.createNamedQuery(ParkingSpot.Queries.findById, ParkingSpot.class)
                    .setParameter("id", parkingSpotId)
                    .getSingleResult();
            List<Reservation> allReservations = parkingSpot.getReservations();
            List<Object> reservations = new ArrayList<>();
            for(Reservation reservation : allReservations){
                Date reservationDate = reservation.getStartTime();
                if(reservationDate.after(date)){
                    reservations.add(reservation);
                }
            }
            return reservations;

        }catch (NoResultException e) {
            return null;
        }
    }
    @Override
    public List<Object> getMyReservations(Long userId) {
        try{
            List<Object> userReservations = new ArrayList<>();
            List <Car> userCars = em.createNamedQuery(User.Queries.findById, User.class)
                    .setParameter("id", userId)
                    .getSingleResult().getCars();
            for(Car car : userCars){
                List<Reservation> userCarReservations = car.getReservations();
                userReservations.addAll(userCarReservations);
            }
            return userReservations;
        }catch (NoResultException e){
            return null;
        }
    }
    @Override
    public Object updateReservation(Object reservation) {
        try {
            et.begin();
            em.merge(reservation);
            et.commit();
            return em.createNamedQuery(Reservation.Queries.findById, Reservation.class)
                    .setParameter("id", ((Reservation) reservation).getId());
        }catch (RollbackException e){
            return null;
        }
    }

    //CARTYPES
    @Override
    public Object createCarType(String name) {
        return new CarType(name);
    }
    @Override
    public List<Object> getCarTypes() {
        try{
            return Collections.singletonList(em.createNamedQuery(CarType.Queries.findAll, CarType.class)
                    .getResultList());
        }catch (NoResultException e){
            return null;
        }
    }
    @Override
    public Object getCarType(Long carTypeId) {
        try{
           return em.createNamedQuery(CarType.Queries.findById, CarType.class)
                   .setParameter("id", carTypeId)
                   .getSingleResult();
        }catch (NoResultException e){
            return null;
        }
    }
    @Override
    public Object getCarType(String name) {
        try{
            return em.createNamedQuery(CarType.Queries.findByName, CarType.class)
                    .setParameter("name", name)
                    .getSingleResult();
        }catch (NoResultException e){
            return null;
        }
    }
    @Override
    public Object deleteCarType(Long carTypeId) {
        try{
            et.begin();
            em.createNamedQuery(CarType.Queries.deleteById, CarType.class)
                    .executeUpdate();
            et.commit();
            return em.createNamedQuery(CarType.Queries.findById, CarType.class)
                    .setParameter("id", carTypeId)
                    .getSingleResult();
        }catch (NoResultException e){
            return null;
        }
    }
}
