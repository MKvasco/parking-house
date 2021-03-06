package sk.stuba.fei.uim.vsa.pr2.web.controllers.service;

import sk.stuba.fei.uim.vsa.pr2.domain.Car;
import sk.stuba.fei.uim.vsa.pr2.domain.CarType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class CarTypeService {

    private final EntityManager em;
    private final EntityTransaction et;

    public CarTypeService() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        this.em = emf.createEntityManager();
        this.et = em.getTransaction();
    }

    public CarType createCarType(String name) {
        try{
            et.begin();
            CarType carType = new CarType(name);
            em.persist(carType);
            et.commit();
            return carType;
        }catch (RollbackException e){
            return null;
        }
    }

    public List<CarType> getCarTypes() {
        try{
            return new ArrayList<>(em.createNamedQuery(CarType.Queries.findAll, CarType.class)
                    .getResultList());
        }catch (NoResultException e){
            return new ArrayList<>();
        }
    }
    public CarType getCarType(Long carTypeId) {
        try{
            return em.createNamedQuery(CarType.Queries.findById, CarType.class)
                    .setParameter("id", carTypeId)
                    .getSingleResult();
        }catch (NoResultException e){
            return null;
        }
    }
    public CarType getCarType(String name) {
        try{
            return em.createNamedQuery(CarType.Queries.findByName, CarType.class)
                    .setParameter("name", name)
                    .getSingleResult();
        }catch (NoResultException e){
            return null;
        }
    }
    public CarType deleteCarType(Long carTypeId) {
        try{
            CarType carType = em.createNamedQuery(CarType.Queries.findById, CarType.class)
                    .setParameter("id", carTypeId)
                    .getSingleResult();

           et.begin();
           em.createNamedQuery(CarType.Queries.deleteById, CarType.class)
                   .setParameter("id", carTypeId)
                   .executeUpdate();
           et.commit();
           return carType;
        }catch (NoResultException | RollbackException e){
            return null;
        }
    }
}
