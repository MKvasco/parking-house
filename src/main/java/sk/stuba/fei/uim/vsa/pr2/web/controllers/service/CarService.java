package sk.stuba.fei.uim.vsa.pr2.web.controllers.service;

import sk.stuba.fei.uim.vsa.pr2.domain.Car;
import sk.stuba.fei.uim.vsa.pr2.domain.CarType;
import sk.stuba.fei.uim.vsa.pr2.domain.Reservation;
import sk.stuba.fei.uim.vsa.pr2.domain.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CarService {

    private final EntityManager em;
    private final EntityTransaction et;

    public CarService() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        this.em = emf.createEntityManager();
        this.et = em.getTransaction();
    }
    public Car createCar(Long userId, String brand, String model, String colour, String vehicleRegistrationPlate, Long carTypeId) {
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
            user.addCar(car);
            carType.addCars(car);
            em.persist(car);
            et.commit();
            return car;
        }catch(RollbackException | NoResultException e){
            return null;
        }
    }
    public List<Car> getCars(){
        try{
            return em.createNamedQuery(Car.Queries.findAll, Car.class)
                    .getResultList();
        }catch(NoResultException e){
            return new ArrayList<>();
        }
    }

    public Car getCar(Long carId) {
        try{
            return em.createNamedQuery(Car.Queries.findById, Car.class)
                    .setParameter("id", carId)
                    .getSingleResult();
        }catch(NoResultException e){
            return null;
        }
    }
    public Car getCar(String vehicleRegistrationPlate) {
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
    public List<Car> getCars(Long userId) {
        try{
            User user = em.createNamedQuery(User.Queries.findById, User.class)
                    .setParameter("id", userId)
                    .getSingleResult();
            return new ArrayList<>(user.getCars());

        }catch(NoResultException e){
            return new ArrayList<>();
        }
    }
    public Car updateCar(Object car) {
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
    public Car deleteCar(Long carId) {
        try{
            Car car = em.createNamedQuery(Car.Queries.findById, Car.class)
                    .setParameter("id", carId)
                    .getSingleResult();
            et.begin();
            em.createNamedQuery(Car.Queries.deleteById, Car.class)
                    .setParameter("id", carId)
                    .executeUpdate();
            et.commit();
            return car;
        }catch(RollbackException | NoResultException e){
            return null;
        }
    }
}
