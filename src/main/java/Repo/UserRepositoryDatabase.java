package Repo;

import Model.Book;
import Model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class UserRepositoryDatabase implements UserRepository {
    private static SessionFactory sessionFactory;

    public UserRepositoryDatabase(SessionFactory sessionFactory1) {
        sessionFactory = sessionFactory1;
    }


    @Override
    public User logIn(User user) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                String querry = "from User as u where u.username='" + user.getUsername() + "' and u.password='" + user.getPassword() + "'";
                List<User> users =
                        session.createQuery(querry, User.class).
                                setFirstResult(0).setMaxResults(1).
                                list();

                if (users.size() == 1) {
                    tx.commit();
                    return users.get(0);
                } else {
                    tx.commit();
                    throw new Exception("User no found");
                }
            } catch (RuntimeException ex) {
                if (tx != null)
                    tx.rollback();
            }
            return  null;
        }
    }

    @Override
    public List<User> getUsers() {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                String querry = "from User as u ";
                List<User> books =
                        session.createQuery(querry, User.class).
                                list();
                tx.commit();
                return books;

            } catch (RuntimeException ex) {
                if (tx != null)
                    tx.rollback();
            }
            return null;
        }
    }

    @Override
    public void addUser(User user) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                session.save(user);
                tx.commit();
            } catch (RuntimeException ex) {
                if (tx != null)
                    tx.rollback();
            }
        }
    }

    @Override
    public void update(User user) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                session.update(user);
                tx.commit();
            } catch (RuntimeException ex) {
                if (tx != null)
                    tx.rollback();
            }
        }
    }

    @Override
    public void deleteUser(User user) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                session.delete(user);
                tx.commit();
            } catch (RuntimeException ex) {
                if (tx != null)
                    tx.rollback();
            }
        }
    }
}
