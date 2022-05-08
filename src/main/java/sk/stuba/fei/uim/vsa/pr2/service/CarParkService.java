package sk.stuba.fei.uim.vsa.pr2.service;

import sk.stuba.fei.uim.vsa.pr2.domain.*;

import javax.persistence.*;
import java.text.SimpleDateFormat;
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
}
