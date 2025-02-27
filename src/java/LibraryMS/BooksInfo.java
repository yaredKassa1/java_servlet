/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LibraryMS;

public class BooksInfo {
     private String bookId;
    private String title;
    private String author;
    private String edition;
    private String publisher;
    private String pbDate; 

    public BooksInfo(String bookId, String title, String author, String edition, String publisher, String pbDate) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.edition = edition;
        this.publisher = publisher;
        this.pbDate = pbDate;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
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

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPbDate() {
        return pbDate;
    }

    public void setPbDate(String pbDate) {
        this.pbDate = pbDate;
    }
    
    
}

