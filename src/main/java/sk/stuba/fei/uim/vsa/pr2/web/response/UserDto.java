package sk.stuba.fei.uim.vsa.pr2.web.response;

import java.util.List;

public class UserDto extends Dto{

    private String name;
    private String surname;
    private String email;
    private List<CarDto> cars;

    public UserDto(String name, String surname, String email, List<CarDto> cars) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.cars = cars;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public List<CarDto> getCars() {
        return cars;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCars(List<CarDto> cars) {
        this.cars = cars;
    }
}
