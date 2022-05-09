package sk.stuba.fei.uim.vsa.pr2.web.request;

import java.util.Date;

public class ReservationDto {

    private ParkingSpotDto parkingSpot;
    private CarDto car;
    private Integer price;
    private Date startTime;
    private Date endTime;

    public ReservationDto(ParkingSpotDto parkingSpot, CarDto car, Integer price, Date startTime, Date endTime) {
        this.parkingSpot = parkingSpot;
        this.car = car;
        this.price = price;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public ParkingSpotDto getParkingSpot() {
        return parkingSpot;
    }

    public CarDto getCar() {
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
