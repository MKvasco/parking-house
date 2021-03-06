package sk.stuba.fei.uim.vsa.pr2.web.controllers.service;


import sk.stuba.fei.uim.vsa.pr2.domain.CarPark;
import sk.stuba.fei.uim.vsa.pr2.domain.CarParkFloor;
import sk.stuba.fei.uim.vsa.pr2.domain.ParkingSpot;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CarParkFloorService {

    private final EntityManager em;
    private final EntityTransaction et;

    public CarParkFloorService() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        this.em = emf.createEntityManager();
        this.et = em.getTransaction();
    }

    public CarParkFloor createCarParkFloor(Long carParkId, String floorIdentifier) {
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

    public CarParkFloor createCarParkFloor(String floorIdentifier){
        try{
            et.begin();
            CarParkFloor carParkFloor = new CarParkFloor();
            carParkFloor.setFloorIdentifier(floorIdentifier);
            em.persist(carParkFloor);
            et.commit();
            return carParkFloor;
        }catch(NoResultException | RollbackException e){
            return null;
        }
    }

    public CarParkFloor getCarParkFloor(Long carParkFloorId) {
        try{
            return em.createNamedQuery(CarParkFloor.Queries.findById, CarParkFloor.class)
                    .setParameter("id", carParkFloorId)
                    .getSingleResult();
        }catch(NoResultException e){
            return null;
        }
    }

    public List<CarParkFloor> getCarParkFloors(Long carParkId) {
        try{
            CarPark carPark = em.createNamedQuery(CarPark.Queries.findById, CarPark.class)
                    .setParameter("id",carParkId)
                    .getSingleResult();
            return new ArrayList<>(carPark.getCarParkFloors());
        }catch(NoResultException e){
            return new ArrayList<>();
        }
    }

    public CarParkFloor updateCarParkFloor(Object carParkFloor) {
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

    public CarParkFloor deleteCarParkFloor(Long carParkId, String floorIdentifier) {
        CarPark carPark = em.createNamedQuery(CarPark.Queries.findById, CarPark.class)
                .setParameter("id", carParkId)
                .getSingleResult();

        // Get Floor
        CarParkFloor carParkFloor = null;
        List<CarParkFloor> carParkFloors = carPark.getCarParkFloors();
        for(CarParkFloor floor : carParkFloors){
            if(Objects.equals(floor.getFloorIdentifier(), floorIdentifier)){
                carParkFloor = floor;
            }
        }

        //Get Parking spots to delete
        if(carParkFloor != null){
            List<ParkingSpot> parkingSpots = new ParkingSpotService().getParkingSpots(carParkId, floorIdentifier);
            if(!parkingSpots.isEmpty()){
                ParkingSpotService parkingSpotService = new ParkingSpotService();
                for(ParkingSpot parkingSpot : parkingSpots){
                    parkingSpotService.deleteParkingSpot(parkingSpot.getId());
                }
            }
        // Delete CarParkFloor
            try{
                et.begin();
                em.createNamedQuery(CarParkFloor.Queries.deleteById, CarParkFloor.class)
                        .setParameter("id", carParkFloor.getId())
                        .executeUpdate();
                et.commit();
            }catch (NoResultException | RollbackException e){
                return null;
            }
        }
        return carParkFloor;
    }

    public CarParkFloor deleteCarParkFloor(Long carParkFloorId) {
        try{
            CarParkFloor carParkFloor = em.createNamedQuery(CarParkFloor.Queries.findById, CarParkFloor.class)
                    .setParameter("id", carParkFloorId)
                    .getSingleResult();
            CarPark carPark = carParkFloor.getCarPark();
            et.begin();
            carPark.removeCarParkFloor(carParkFloor);
            em.createNamedQuery(CarParkFloor.Queries.deleteById, CarParkFloor.class)
                    .setParameter("id", carParkFloorId)
                    .executeUpdate();
            et.commit();
            return carParkFloor;
        }catch(RollbackException | NoResultException e){
            return null;
        }
    }
}
