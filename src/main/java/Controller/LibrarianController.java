package Controller;

import Model.Book;
import Model.User;
import Repo.BookRepository;
import Repo.BorrowRepository;
import Repo.UserRepository;

import java.util.List;

public class LibrarianController {
    private UserRepository userRepository;
    private BookRepository bookRepository;
    private BorrowRepository borrowRepository;

    public LibrarianController(UserRepository userRepository, BookRepository bookRepository, BorrowRepository borrowRepository) {
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
        this.borrowRepository = borrowRepository;
    }

    public void returnBook(String bookCode) throws Exception {
        bookRepository.returnBook(bookCode);
        borrowRepository.returnBook(bookCode);
    }

    public List<Book> getAllBooks(){
        return bookRepository.getAllBooks();
    }

    public void updateBook(Book book) {
        bookRepository.updateBook(book);
    }

    public void saveBook(Book book) {
        bookRepository.saveBook(book);
    }

    public void deleteBook(String code) {
        bookRepository.deleteBook(new Book(code));
    }

    public List<User> getUsers() {
        return userRepository.getUsers();
    }

    public void addUser(User user) {
        userRepository.addUser(user);
    }

    public void updateUser(User user) {
        userRepository.update(user);
    }

    public void deleteUser(User user) {
        userRepository.deleteUser(user);
    }
}
