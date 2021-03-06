package sk.stuba.fei.uim.vsa.pr2.web.response;

public class CarTypeDto {

    private Long id;
    private String name;

    public CarTypeDto() {}

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "CarTypeDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
