package sk.stuba.fei.uim.vsa.pr1b.entities;


import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "USER")
@NamedQuery(name = User.Queries.findAll, query="select user from User user")
@NamedQuery(name = User.Queries.findById, query = "select user from User user where user.id = :id")
@NamedQuery(name = User.Queries.findByEmail, query = "select user from User user where user.email = :email")
@NamedQuery(name = User.Queries.deleteById, query = "delete from User user where user.id = :id")
public class User implements Serializable {
    public static final class Queries{
        public static final String findAll = "findAll";
        public static final String findById = "findById";
        public static final String findByEmail = "findByEmail";
        public static final String deleteById = "deleteById";
    }
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "FIRST_NAME")
    private String name;

    @Column(name = "LAST_NAME")
    private String surname;

    @Column(name = "EMAIL", unique = true)
    private String email;

    @OneToMany
    private List<Car> cars;

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

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }
}
