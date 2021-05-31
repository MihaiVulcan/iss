package Model.DTOs;

import java.time.LocalDate;

public class BorrowDTO {
    private int id;
    private String bookCode;
    private String Author;
    private String Title;
    private boolean extended;
    private LocalDate returnDate;

    public BorrowDTO(int id, String bookCode, String title, String author, boolean extended, LocalDate returnDate) {
        this.id = id;
        this.bookCode = bookCode;
        Author = author;
        Title = title;
        this.extended = extended;
        this.returnDate = returnDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookCode() {
        return bookCode;
    }

    public void setBookCode(String bookCode) {
        this.bookCode = bookCode;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public boolean isExtended() {
        return extended;
    }

    public void setExtended(boolean extended) {
        this.extended = extended;
    }
}
