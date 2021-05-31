package Repo;

import Model.Book;
import Model.Librarian;
import Model.User;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class BookRepositoryDatabase implements BookRepository {
    SessionFactory sessionFactory;

    public BookRepositoryDatabase(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Book> getAvailableBooks() {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                String querry = "from Book as b where b.available=true";
                List<Book> books =
                        session.createQuery(querry, Book.class).
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
    public void borrowBook(String bookCode) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                String querry = "update Book b set b.available=false where b.code="+bookCode;
                session.createQuery(querry).executeUpdate();
                tx.commit();
            } catch (RuntimeException ex) {
                if (tx != null)
                    tx.rollback();
            }
        }
    }

    @Override
    public Book findOne(String bookCode) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                String querry = "from Book as b where b.code="+bookCode;
                List<Book> users =
                        session.createQuery(querry, Book.class).
                                setFirstResult(0).setMaxResults(1).
                                list();

                if (users.size() == 1) {
                    tx.commit();
                    return users.get(0);
                } else {
                    tx.commit();
                    throw new Exception("Book no found");
                }
            } catch (RuntimeException ex) {
                if (tx != null)
                    tx.rollback();
            }
            return  null;
        }
    }

    @Override
    public void returnBook(String bookCode) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                String querry = "update Book b set b.available=true where b.code="+bookCode;
                session.createQuery(querry).executeUpdate();
                tx.commit();
            } catch (RuntimeException ex) {
                if (tx != null)
                    tx.rollback();
            }
        }
    }

    @Override
    public List<Book> getAllBooks() {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                String querry = "from Book as b ";
                List<Book> books =
                        session.createQuery(querry, Book.class).
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
    public void updateBook(Book book) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                session.update(book);
                tx.commit();
            } catch (RuntimeException ex) {
                if (tx != null)
                    tx.rollback();
            }
        }
    }

    @Override
    public void saveBook(Book book) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                session.save(book);
                tx.commit();
            } catch (RuntimeException ex) {
                if (tx != null)
                    tx.rollback();
            }
        }
    }

    @Override
    public void deleteBook(Book book) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                session.delete(book);
                tx.commit();
            } catch (RuntimeException ex) {
                if (tx != null)
                    tx.rollback();
            }
        }
    }
}
