package sk.stuba.fei.uim.vsa.pr1b.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "RESERVATION")
public class Reservation {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "PARKING_SPOT_ID")
    private Long parkingSpotId;

    @Column(name = "CAR_ID")
    private Long carId;

    private Float price;

    @Temporal(TemporalType.TIMESTAMP)
    private Date startTime;

    @Temporal(TemporalType.TIMESTAMP)
    private Date endTime;

    public Reservation(Long parkingSpotId, Long carId, Date startTime) {
        this.parkingSpotId = parkingSpotId;
        this.carId = carId;
        this.price = null;
        this.startTime = startTime;
        this.endTime = null;
    }

    public Reservation() {

    }
}
