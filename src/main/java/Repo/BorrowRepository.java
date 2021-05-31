package Repo;

import Model.Borrow;

import java.util.List;

public interface BorrowRepository {

    void add(Borrow borrow);

    List<Borrow> getBorrows(String userId);

    void extend(int id);

    void returnBook(String bookCode) throws Exception;
}
