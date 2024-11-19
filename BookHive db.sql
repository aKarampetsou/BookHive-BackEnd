CREATE TABLE IF NOT EXISTS public."Authors" (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    surname VARCHAR(255) NOT NULL,
    CONSTRAINT unique_author UNIQUE (name, surname)
);

CREATE TABLE IF NOT EXISTS public."Books" (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    isbn VARCHAR(13) UNIQUE NOT NULL,
    author_id INTEGER NOT NULL,
    FOREIGN KEY (author_id) REFERENCES public."Authors" (id) ON DELETE CASCADE,
    CONSTRAINT unique_book_author UNIQUE (title, author_id)
);

CREATE TABLE IF NOT EXISTS public."Users" (
    id SERIAL PRIMARY KEY,
    username VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL
); 

INSERT INTO public."Authors" (name, surname) VALUES
('J.K.', 'Rowling'),
('George', 'Orwell'),
('F. Scott', 'Fitzgerald'),
('Harper', 'Lee');

INSERT INTO public."Books" (title, isbn, author_id) VALUES
('Harry Potter and the Chamber of Secrets', '9780747538490', 1),
('1984', '9780451524935', 2),
('The Great Gatsby', '9780743273565', 3),
('To Kill a Mockingbird', '9780061120084', 4);
