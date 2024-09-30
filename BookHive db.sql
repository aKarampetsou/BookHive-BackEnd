
CREATE TABLE Authors (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    surname VARCHAR(100) NOT NULL,
    CONSTRAINT unique_author UNIQUE (name, surname) --Δεν μπορούν να υπάρχουν ταυτόχρονα 
	                                                --δύο συγγραφείς με το ίδιο ονομα και επώνυμο
);

CREATE TABLE Books (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    isbn VARCHAR(13) UNIQUE NOT NULL,
    author_id INTEGER NOT NULL,
    FOREIGN KEY (author_id) REFERENCES Authors(id),
    CONSTRAINT unique_book_author UNIQUE (title, author_id) -- 
);


INSERT INTO Authors (name, surname) VALUES ('J.K.', 'Rowling'); 
INSERT INTO Authors (name, surname) VALUES ('George', 'Orwell'); 
INSERT INTO Authors (name, surname) VALUES ('F. Scott', 'Fitzgerald'); 
INSERT INTO Authors (name, surname) VALUES ('Harper', 'Lee');
INSERT INTO Authors (name, surname) VALUES ('J.R.R.', 'Tolkien'); 


INSERT INTO Books (title, isbn, author_id) VALUES ('Harry Potter and the Chamber of Secrets', '9780747538490', 1);
INSERT INTO Books (title, isbn, author_id) VALUES ('1984', '9780451524935', 2);
INSERT INTO Books (title, isbn, author_id) VALUES ('The Great Gatsby', '9780743273565', 3);
INSERT INTO Books (title, isbn, author_id) VALUES ('To Kill a Mockingbird', '9780061120084', 4);
INSERT INTO Books (title, isbn, author_id) VALUES ('The Hobbit', '9780547928227', 5);


