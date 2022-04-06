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
        public static final String findAll = "Reservation.findAll";
        public static final String findById = "Reservation.findById";
    }

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private ParkingSpot parkingSpot;

    @JoinColumn(name = "CAR")
    @ManyToOne
    private Car car;

    @Column(name = "TOTAL_COST")
    private Integer price;

    @Column(name = "RESERVATION_START")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startTime;

    @Column(name = "RESERVATION_END")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endTime;

    public Reservation(ParkingSpot parkingSpot, Car car, Date startTime) {
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

    public Integer getPrice() {
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

    public void setPrice(Integer price) {
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

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", parkingSpot=" + parkingSpot +
                ", car=" + car +
                ", price=" + price +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
