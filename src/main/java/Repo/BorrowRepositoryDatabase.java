package Repo;

import Model.Borrow;
import Model.Librarian;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class BorrowRepositoryDatabase implements BorrowRepository {

    SessionFactory sessionFactory;

    public BorrowRepositoryDatabase(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(Borrow borrow) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                session.save(borrow);
                tx.commit();
            } catch (RuntimeException ex) {
                if (tx != null)
                    tx.rollback();
            }
        }
    }

    @Override
    public List<Borrow> getBorrows(String userId) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                String querry = "from Borrow as b where returned=false and b.userId=" + userId;
                List<Borrow> users =
                        session.createQuery(querry, Borrow.class).
                                list();
                tx.commit();
                return users;
            } catch (RuntimeException ex) {
                if (tx != null)
                    tx.rollback();
            }
            return  null;
        }
    }

    @Override
    public void extend(int id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                String querry = "update Borrow b set b.extended=true where b.id="+id;
                session.createQuery(querry).executeUpdate();
                tx.commit();
            } catch (RuntimeException ex) {
                if (tx != null)
                    tx.rollback();
            }
        }
    }

    @Override
    public void returnBook(String bookCode) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                String querry = "update Borrow b set b.returned=true where b.bookId="+bookCode;
                int ret = session.createQuery(querry).executeUpdate();
                if (ret==0)
                    throw new Exception( "This book was not borrowed or does not exsit");
                tx.commit();
            } catch (RuntimeException ex) {
                if (tx != null)
                    tx.rollback();
            }
        }
    }
}
