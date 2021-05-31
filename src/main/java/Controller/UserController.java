package Controller;

import Model.Borrow;
import Model.DTOs.BorrowDTO;
import Repo.BookRepository;
import Repo.BorrowRepository;

import Model.Book;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserController {
    private BookRepository bookRepository;
    private BorrowRepository borrowRepository;

    public UserController(BookRepository bookRepository, BorrowRepository borrowRepository) {
        this.bookRepository = bookRepository;
        this.borrowRepository = borrowRepository;
    }

    public void borrowBook(String userId, String bookCode){
        bookRepository.borrowBook(bookCode);
        borrowRepository.add(new Borrow(userId, bookCode, LocalDate.now(), LocalDate.now().plus(14, ChronoUnit.DAYS), false, false ));
    }

    public List<Book> getAvailableBooks(){
        return bookRepository.getAvailableBooks();
    }

    public List<BorrowDTO> getBorrowedBooks(String userId) throws Exception {
        List<Borrow> borrows = borrowRepository.getBorrows(userId);
        List<BorrowDTO> ret = new ArrayList<>();
        for(Borrow borrow:borrows){
            Book book = bookRepository.findOne(borrow.getBookId());
            ret.add(new BorrowDTO(borrow.getId(), book.getCode(), book.getTitle(), book.getAuthor(), borrow.extended, borrow.endDate));
        }
        return ret;
    }

    public void extendbook(int id){
        borrowRepository.extend(id);
    }
}
