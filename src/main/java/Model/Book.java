package Model;

import java.util.*;

/**
 * 
 */
public class Book {

    public Book(String code) {
        this.code = code;
    }

    public Book(String code, String title, String author, String description, String pagesFromBook, BookCategory category, Boolean available) {
        this.code = code;
        this.title = title;
        this.author = author;
        this.description = description;
        this.pagesFromBook = pagesFromBook;
        this.category = category;
        this.available = available;
    }

    /**
     * Default constructor
     */
    public Book() {
    }

    /**
     * 
     */
    public String code;

    /**
     * 
     */
    public String title;

    /**
     * 
     */
    public String author;

    /**
     * 
     */
    public String description;

    /**
     * 
     */
    public String pagesFromBook;

    /**
     * 
     */
    public BookCategory category;

    public Boolean available;

    public Boolean isAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPagesFromBook() {
        return pagesFromBook;
    }

    public void setPagesFromBook(String pagesFromBook) {
        this.pagesFromBook = pagesFromBook;
    }

    public BookCategory getCategory() {
        return category;
    }

    public void setCategory(BookCategory category) {
        this.category = category;
    }

    public Boolean getAvailable() {
        return available;
    }
}