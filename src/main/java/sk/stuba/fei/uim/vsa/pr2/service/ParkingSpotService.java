package sk.stuba.fei.uim.vsa.pr2.service;

import sk.stuba.fei.uim.vsa.pr2.domain.*;

import javax.persistence.*;
import java.util.*;

public class ParkingSpotService {

    private final EntityManager em;
    private final EntityTransaction et;

    public ParkingSpotService() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        this.em = emf.createEntityManager();
        this.et = em.getTransaction();
    }


    public ParkingSpot createParkingSpot(Long carParkId, String floorIdentifier, String spotIdentifier, Long carTypeId) {
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
                List<ParkingSpot> parkingSpots = floor.getParkingSpots();
                for(ParkingSpot ps : parkingSpots){
                    if(Objects.equals(ps.getIdentifier(), spotIdentifier))
                        return null;
                }
                if(Objects.equals(floor.getFloorIdentifier(), floorIdentifier)){
                    et.begin();
                    parkingSpot = new ParkingSpot(floor, carType, spotIdentifier);
                    floor.addParkingSpot(parkingSpot);
                    em.persist(parkingSpot);
                    et.commit();
                }
            }
            return parkingSpot;
        }catch (RollbackException | NoResultException e){
            return null;
        }
    }

    public ParkingSpot getParkingSpot(Long parkingSpotId) {
        try{
            return em.createNamedQuery(ParkingSpot.Queries.findById, ParkingSpot.class)
                    .setParameter("id", parkingSpotId)
                    .getSingleResult();
        }catch(NoResultException e){
            return null;
        }
    }
    public List<ParkingSpot> getParkingSpots(Long carParkId){
        try{
            List<ParkingSpot> parkingSpots = new ArrayList<>();
            List<CarParkFloor> carParkFloors = em.createNamedQuery(CarPark.Queries.findById, CarPark.class)
                    .setParameter("id", carParkId)
                    .getSingleResult()
                    .getCarParkFloors();
            for(CarParkFloor floor : carParkFloors){
                parkingSpots.addAll(floor.getParkingSpots());
            }
            return parkingSpots;
        }catch(NoResultException e){
            return new ArrayList<>();
        }
    }
    public List<ParkingSpot> getParkingSpots(Long carParkId, String floorIdentifier) {
        try{
            List<ParkingSpot> parkingSpots = new ArrayList<>();
            List<CarParkFloor> carParkFloors = em.createNamedQuery(CarPark.Queries.findById, CarPark.class)
                    .setParameter("id", carParkId)
                    .getSingleResult()
                    .getCarParkFloors();
            for(CarParkFloor floor : carParkFloors){
                if(Objects.equals(floor.getFloorIdentifier(), floorIdentifier)){
                    parkingSpots = floor.getParkingSpots();
                }
            }
            return new ArrayList<>(parkingSpots);
        }catch(NoResultException e){
            return new ArrayList<>();
        }
    }
    public Map<String, List<Object>> getParkingSpotsHash(Long carParkId) {
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
            return new HashMap<>();
        }
    }
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
                parkingSpots.put(floor.getFloorIdentifier(), new ArrayList<>(availableParkingSpots));
            }
            return parkingSpots;

        }catch(NoResultException e){
            return new HashMap<>();
        }
    }
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
                occupiedParkingSpotsMap.put(floor.getFloorIdentifier(), new ArrayList<>(occupiedParkingSpots));
            }
            return occupiedParkingSpotsMap;
        }catch(NoResultException e){
            return new HashMap<>();
        }
    }

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
    public ParkingSpot deleteParkingSpot(Long parkingSpotId) {
        return null;
    }
}
