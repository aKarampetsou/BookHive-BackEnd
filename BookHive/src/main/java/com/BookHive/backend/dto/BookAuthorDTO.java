package com.BookHive.backend.dto;

/* Το συγκεκριμένο DataTranfserObject χρησιμοποιείται για να μεταφέρει δεδομένα μεταξύ των client και server κατά την διάρκεια
ενός αιτήματος δημιουργίας νέου βιβλίου με νέο συγγραφέα ταυτόχρονα (endpoint:  addBookWithAuthor). Με την βοήθεια των
getters και setters αποφεύγουμε την απευθείας πρόσβαση στις οντόντητες Books και Authors.*/


public class BookAuthorDTO {
    private String title;
    private String isbn;
    private String authorName;
    private String authorSurname;

    // Getters and Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorSurname() {
        return authorSurname;
    }

    public void setAuthorSurname(String authorSurname) {
        this.authorSurname = authorSurname;
    }
}