package sk.stuba.fei.uim.vsa.pr2.web.controllers.service;

import sk.stuba.fei.uim.vsa.pr2.domain.*;

import javax.persistence.*;
import java.util.*;

public class CarParkService{
    private final EntityManager em;
    private final EntityTransaction et;

    public CarParkService() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        this.em = emf.createEntityManager();
        this.et = em.getTransaction();
    }

    public CarPark createCarPark(String name, String address, Integer pricePerHour) {
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

    public CarPark getCarPark(Long carParkId) {
        try{
            return em.createNamedQuery(CarPark.Queries.findById, CarPark.class)
                    .setParameter("id", carParkId)
                    .getSingleResult();
        }catch (NoResultException e){
            return null;
        }
    }

    public CarPark getCarPark(String carParkName) {
        try{
            return em.createNamedQuery(CarPark.Queries.findByName, CarPark.class)
                    .setParameter("name", carParkName)
                    .getSingleResult();
        }catch (NoResultException e){
            return null;
        }
    }

    public List<CarPark> getCarParks() {
        try{
            List<CarPark> carParkList = em.createNamedQuery(CarPark.Queries.findAll, CarPark.class)
                    .getResultList();
            return new ArrayList<>(carParkList);
        }catch (NoResultException e){
            return new ArrayList<>();
        }
    }

    public CarPark updateCarPark(Object carPark) {
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

    public CarPark deleteCarPark(Long carParkId) {
        CarPark carPark = em.createNamedQuery(CarPark.Queries.findById, CarPark.class)
                .setParameter("id", carParkId)
                .getSingleResult();

        // Check childrens
        List<CarParkFloor> carParkFloors = new CarParkFloorService().getCarParkFloors(carParkId);
        if(!carParkFloors.isEmpty()){
            CarParkFloorService carParkFloorService = new CarParkFloorService();
            for(CarParkFloor floor : carParkFloors){
                carParkFloorService.deleteCarParkFloor(carParkId, floor.getFloorIdentifier());
            }
        }

        // Delete carpark
        try{
            et.begin();
            em.createNamedQuery(CarPark.Queries.deleteById, CarPark.class)
                    .setParameter("id", carParkId)
                    .executeUpdate();
            et.commit();
        }catch(NoResultException | RollbackException r){
            return null;
        }
        return carPark;
    }
}

