package sk.stuba.fei.uim.vsa.pr2.web.response;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class DtoToReservationsDto {
    private Long id;
    private ParkingSpotDto spot;
    private CarDto car;
    private Integer prices;
    @JsonFormat(shape = JsonFormat.Shape.STRING, timezone = "Europe/Bratislava")
    private Date start;
    @JsonFormat(shape = JsonFormat.Shape.STRING, timezone = "Europe/Bratislava")
    private Date end;

    public DtoToReservationsDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ParkingSpotDto getSpot() {
        return spot;
    }

    public void setSpot(ParkingSpotDto spot) {
        this.spot = spot;
    }

    public CarDto getCar() {
        return car;
    }

    public void setCar(CarDto car) {
        this.car = car;
    }

    public Integer getPrices() {
        return prices;
    }

    public void setPrices(Integer prices) {
        this.prices = prices;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }
}
