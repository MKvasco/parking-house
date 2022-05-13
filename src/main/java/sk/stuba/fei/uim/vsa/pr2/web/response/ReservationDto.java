package sk.stuba.fei.uim.vsa.pr2.web.response;

import java.util.Date;

public class ReservationDto {
    private Long id;
    private ParkingSpotDto parkingSpot;
    private CarDto car;
    private Integer price;
    private Date startTime;
    private Date endTime;

    public ReservationDto() {}


    public Long getId() {
        return id;
    }

    public ParkingSpotDto getSpot() {
        return parkingSpot;
    }

    public CarDto getCar() {
        return car;
    }

    public Integer getPrices() {
        return price;
    }

    public Date getStart() {
        return startTime;
    }

    public Date getEnd() {
        return endTime;
    }

    public void setParkingSpot(ParkingSpotDto parkingSpot) {
        this.parkingSpot = parkingSpot;
    }

    public void setCar(CarDto car) {
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

}
