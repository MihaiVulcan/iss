package Repo;

import Model.Librarian;
import Model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class LibrarianRepositoryDatabase implements LibrarianRepository {
    private static SessionFactory sessionFactory;

    public LibrarianRepositoryDatabase(SessionFactory sessionFactory1) {
        sessionFactory = sessionFactory1;
    }


    @Override
    public Librarian logIn(Librarian librarian) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                String querry = "from Librarian as u where u.username='" + librarian.getUsername() + "' and u.password='" + librarian.getPassword() + "'";
                List<Librarian> users =
                        session.createQuery(querry, Librarian.class).
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
}
