package sk.stuba.fei.uim.vsa.pr1b.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "RESERVATION")
@NamedQuery(name = Reservation.Queries.findAll,query = "select reservation from Reservation reservation")
@NamedQuery(name = Reservation.Queries.findById, query = "select reservation from Reservation reservation where reservation.id = :id ")
public class Reservation implements Serializable {
    public static final class Queries{
        public static final String findAll = "findAll";
        public static final String findById = "findById";
    }

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private ParkingSpot parkingSpot;

    @Column(name = "CAR")
    @ManyToOne
    private Car car;

    private Float price;

    @Column(name = "RESERVATION_START")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startTime;

    @Column(name = "RESERVATION_END")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endTime;

    public Reservation(ParkingSpot parkingSpot, Long carId, Date startTime) {
        this.parkingSpot = parkingSpot;
        this.car = car;
        this.price = null;
        this.startTime = startTime;
        this.endTime = null;
    }

    public Reservation() {

    }

    public Long getId() {
        return id;
    }

    public Car getCar() {
        return car;
    }

    public Float getPrice() {
        return price;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setCarId(Car car) {
        this.car = car;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }

    public void setParkingSpot(ParkingSpot parkingSpot) {
        this.parkingSpot = parkingSpot;
    }
}
