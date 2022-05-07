package sk.stuba.fei.uim.vsa.pr2.service;

import sk.stuba.fei.uim.vsa.pr2.domain.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class UserService {

    private final EntityManager em;
    private final EntityTransaction et;

    protected UserService() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        this.em = emf.createEntityManager();
        this.et = em.getTransaction();
    }
    public Object createUser(String firstname, String lastname, String email) {
        try{
            et.begin();
            User user = new User(firstname, lastname, email);
            em.persist(user);
            et.commit();
            return user;
        }catch(RollbackException | NoResultException e){
            return null;
        }
    }
    public Object getUser(Long userId) {
        try{
            return em.createNamedQuery(User.Queries.findById, User.class)
                    .setParameter("id", userId)
                    .getSingleResult();
        }catch (NoResultException e){
            return null;
        }
    }
    public Object getUser(String email) {
        try{
            return em.createNamedQuery(User.Queries.findByEmail, User.class)
                    .setParameter("email", email)
                    .getSingleResult();
        }catch (NoResultException e){
            return null;
        }
    }
    public List<Object> getUsers() {
        try{
            List<User> userList = em.createNamedQuery(User.Queries.findAll, User.class)
                    .getResultList();
            return new ArrayList<>(userList);
        }catch (NoResultException e){
            return null;
        }
    }
    public Object updateUser(Object user) {
        try{
            et.begin();
            em.merge(user);
            et.commit();
            return em.createNamedQuery(User.Queries.findById, User.class)
                    .setParameter("id", ((User) user).getId())
                    .getSingleResult();
        }catch (NoResultException | RollbackException e){
            return null;
        }
    }

    public Object deleteUser(Long userId) {
        try{
            User deletedUser = em.createNamedQuery(User.Queries.findById, User.class)
                    .setParameter("id", userId)
                    .getSingleResult();
            et.begin();
            em.createNamedQuery(User.Queries.deleteById, User.class)
                    .setParameter("id", userId)
                    .executeUpdate();
            et.commit();
            return deletedUser;
        }catch (RollbackException | NoResultException e){
            return null;
        }
    }
}
