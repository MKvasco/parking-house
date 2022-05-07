package sk.stuba.fei.uim.vsa.pr2.service;

import sk.stuba.fei.uim.vsa.pr2.domain.CarType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class CarTypeService {

    private final EntityManager em;
    private final EntityTransaction et;

    protected CarTypeService() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        this.em = emf.createEntityManager();
        this.et = em.getTransaction();
    }

    public Object createCarType(String name) {
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

    public List<Object> getCarTypes() {
        try{
            return new ArrayList<>(em.createNamedQuery(CarType.Queries.findAll, CarType.class)
                    .getResultList());
        }catch (NoResultException e){
            return new ArrayList<>();
        }
    }
    public Object getCarType(Long carTypeId) {
        try{
            return em.createNamedQuery(CarType.Queries.findById, CarType.class)
                    .setParameter("id", carTypeId)
                    .getSingleResult();
        }catch (NoResultException e){
            return null;
        }
    }
    public Object getCarType(String name) {
        try{
            return em.createNamedQuery(CarType.Queries.findByName, CarType.class)
                    .setParameter("name", name)
                    .getSingleResult();
        }catch (NoResultException e){
            return null;
        }
    }
    public Object deleteCarType(Long carTypeId) {
        try{
            Object carType = em.createNamedQuery(CarType.Queries.findById, CarType.class)
                    .setParameter("id", carTypeId)
                    .getSingleResult();
            et.begin();
            em.createNamedQuery(CarType.Queries.deleteById, CarType.class)
                    .setParameter("id", carTypeId)
                    .executeUpdate();
            et.commit();
            return carType;
        }catch (NoResultException e){
            return null;
        }
    }
}
