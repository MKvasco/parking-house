package sk.stuba.fei.uim.vsa.pr2.web.response;

import java.util.List;

public class UserWithCarsDto {

    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private List<Long> cars;

    public UserWithCarsDto() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Long> getCars() {
        return cars;
    }

    public void setCars(List<Long> cars) {
        this.cars = cars;
    }

    @Override
    public String toString() {
        return "UserWithCarsDto{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", cars=" + cars +
                '}';
    }
}
