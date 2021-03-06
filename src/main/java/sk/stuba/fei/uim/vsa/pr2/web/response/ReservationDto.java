package sk.stuba.fei.uim.vsa.pr2.web.response;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class ReservationDto {
    private Long id;
    private Long spot;
    private Long car;
    private Integer prices;
    @JsonFormat(shape = JsonFormat.Shape.STRING, timezone = "Europe/Bratislava")
    private Date start;
    @JsonFormat(shape = JsonFormat.Shape.STRING, timezone = "Europe/Bratislava")
    private Date end;

    public ReservationDto() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSpot() {
        return spot;
    }

    public void setSpot(Long spot) {
        this.spot = spot;
    }

    public Long getCar() {
        return car;
    }

    public void setCar(Long car) {
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

    @Override
    public String toString() {
        return "ReservationDto{" +
                "id=" + id +
                ", spot=" + spot +
                ", car=" + car +
                ", prices=" + prices +
                ", start=" + start +
                ", end=" + end +
                '}';
    }
}
