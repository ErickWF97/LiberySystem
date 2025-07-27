package org.ewflorencio.dao;

import org.ewflorencio.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class UserDao {

    private SessionFactory sessionFactory;

    public UserDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public User findByUsername(String username) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(
                            "from User where username = :username", User.class)
                    .setParameter("username", username)
                    .uniqueResult();
        }
    }

}
