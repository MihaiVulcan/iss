package Model;

import java.time.LocalDate;
import java.util.*;

/**
 * 
 */
public class Borrow {


    public Borrow(String userId, String bookId, LocalDate startDate, LocalDate endDate, Boolean extended, Boolean returned) {
        this.userId = userId;
        this.bookId = bookId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.extended = extended;
        this.returned = returned;
    }

    public Borrow(int id, String userId, String bookId, LocalDate startDate, LocalDate endDate, Boolean extended, Boolean returned) {
        this.id = id;
        this.userId = userId;
        this.bookId = bookId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.extended = extended;
        this.returned = returned;
    }

    /**
     * Default constructor
     */
    public Borrow() {
    }

    public int id;

    public String userId;

    public String bookId;

    /**
     * 
     */
    public LocalDate startDate;

    /**
     * 
     */
    public LocalDate endDate;

    /**
     * 
     */
    public Boolean extended;

    /**
     * 
     */
    public Boolean returned;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Boolean getExtended() {
        return extended;
    }

    public void setExtended(Boolean extended) {
        this.extended = extended;
    }

    public Boolean getReturned() {
        return returned;
    }

    public void setReturned(Boolean returned) {
        this.returned = returned;
    }
}