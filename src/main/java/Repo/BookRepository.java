package Repo;

import Model.Book;

import java.util.List;

public interface BookRepository {
    List<Book> getAvailableBooks();

    List<Book> getAllBooks();

    void borrowBook(String bookCode);

    Book findOne(String bookCode) throws Exception;

    void returnBook(String bookCode);

    void updateBook(Book book);

    void saveBook(Book book);

    void deleteBook(Book book);
}
