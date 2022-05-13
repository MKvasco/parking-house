package sk.stuba.fei.uim.vsa.pr2.web.controllers.service;

import sk.stuba.fei.uim.vsa.pr2.domain.Car;
import sk.stuba.fei.uim.vsa.pr2.domain.ParkingSpot;
import sk.stuba.fei.uim.vsa.pr2.domain.Reservation;
import sk.stuba.fei.uim.vsa.pr2.domain.User;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReservationService {

    private final EntityManager em;
    private final EntityTransaction et;

    protected ReservationService() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        this.em = emf.createEntityManager();
        this.et = em.getTransaction();
    }
    public Reservation createReservation(Long parkingSpotId, Long cardId) {
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
            Reservation reservation = new Reservation(parkingSpot, car, new Date());
            parkingSpot.setAvailable(false);
            parkingSpot.addReservation(reservation);
            car.addReservation(reservation);
            em.persist(reservation);
            et.commit();
            return  reservation;
        }catch (RollbackException | NoResultException e){
            return null;
        }
    }
    public Reservation endReservation(Long reservationId) {
        try{
            Reservation reservation = em.createNamedQuery(Reservation.Queries.findById, Reservation.class)
                    .setParameter("id", reservationId)
                    .getSingleResult();
            Car car = reservation.getCar();
            ParkingSpot parkingSpot = reservation.getParkingSpot();
            int price = parkingSpot.getCarParkFloor().getCarPark().getPricePerHour();
            long totalTime = new Date().getTime() - reservation.getStartTime().getTime();
            long totalSeconds = totalTime/1000;
            long totalHours;
            if(totalSeconds % 60 == 0){
                long totalMinutes = totalSeconds / 60;
                if(totalMinutes % 60 == 0)
                    totalHours = totalMinutes / 60;
                else
                    totalHours = totalMinutes / 60 + 1;
            }else{
                totalHours = (totalSeconds/60/60) + 1;
            }
            totalHours = (totalHours == 0 ? 1 : totalHours);
            int totalPrice = (int) (totalHours * price);
            reservation.setEndTime(new Date());
            reservation.setPrice(totalPrice);
            et.begin();
            car.removeReservation(reservation);
            parkingSpot.setAvailable(true);
            em.merge(reservation);
            et.commit();
            return reservation;
        }catch (NoResultException e){
            return null;
        }
    }

    public List<Reservation> getReservations(Long parkingSpotId, Date date) {
        try {
            ParkingSpot parkingSpot = em.createNamedQuery(ParkingSpot.Queries.findById, ParkingSpot.class)
                    .setParameter("id", parkingSpotId)
                    .getSingleResult();
            List<Reservation> allReservations = parkingSpot.getReservations();
            List<Reservation> reservations = new ArrayList<>();
            SimpleDateFormat smf = new SimpleDateFormat("dd-MM-yyy");
            for (Reservation reservation : allReservations) {
                if (smf.format(date).equals(smf.format(reservation.getStartTime()))) {
                    reservations.add(reservation);
                }
            }
            return reservations;
        } catch (NoResultException e) {
            return new ArrayList<>();
        }
    }
    public List<Reservation> getMyReservations(Long userId) {
        try{
            List<Reservation> userReservations = new ArrayList<>();
            List <Car> userCars = em.createNamedQuery(User.Queries.findById, User.class)
                    .setParameter("id", userId)
                    .getSingleResult().getCars();
            for(Car car : userCars){
                List<Reservation> userCarReservations = car.getReservations();
                for(Reservation reservation : userCarReservations){
                    if(reservation.getEndTime() == null){
                        userReservations.add(reservation);
                    }
                }
            }
            return userReservations;
        }catch (NoResultException e){
            return new ArrayList<>();
        }
    }
    public Reservation updateReservation(Reservation reservation) {
        try {
            et.begin();
            em.merge(reservation);
            et.commit();
            return em.createNamedQuery(Reservation.Queries.findById, Reservation.class)
                    .setParameter("id", reservation.getId())
                    .getSingleResult();
        }catch (RollbackException e){
            return null;
        }
    }
}
