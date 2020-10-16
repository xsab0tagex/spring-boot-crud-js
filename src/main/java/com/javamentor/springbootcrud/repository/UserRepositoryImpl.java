package com.javamentor.springbootcrud.repository;

import com.javamentor.springbootcrud.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {


    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> findAll() {
        return entityManager
                .createQuery("SELECT e FROM User e", User.class)
                .getResultList();
    }

    @Override
    public User findById(Long id) {
        return entityManager
                .find(User.class, id);
    }

    @Override
    public void save(User user) {
        entityManager
                .persist(user);
    }

    @Override
    public void deleteById(Long id) {
        entityManager
                .remove(findById(id));
    }

    @Override
    public void updateUser(User user) {
        entityManager
                .merge(user);
    }

    @Override
    public User getUserByName(String username) {
        return (User) entityManager
                .createQuery("SELECT u from User u where u.userName = :username")
                .setParameter("username", username)
                .getSingleResult();
    }
}
