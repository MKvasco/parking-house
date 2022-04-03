package sk.stuba.fei.uim.vsa.pr1b.entities;


import javax.persistence.*;

@NamedQuery(name = User.Queries.findAll, query="select user from User user")
@NamedQuery(name = User.Queries.findById, query = "select user from User user where user.id = :id")
@Entity
public class User {
    public static final class Queries{
        public static final String findAll = "findAll";
        public static final String findById = "findById";
    }
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String surname;
    private String email;

    @OneToOne
    private Car car;

    public User(String name, String surname, String email) {
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
