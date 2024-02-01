-- !Ups
CREATE TABLE IF NOT EXISTS tb_books (
    bookId UUID PRIMARY KEY NOT NULL,
    title VARCHAR(255) NOT NULL,
    synopsis VARCHAR(255) NOT NULL,
    language VARCHAR(255) NOT NULL,
    publisher VARCHAR(255) NOT NULL,
    pages SMALLINT NOT NULL,
    publicationDate DATE NOT NULL
);

-- !Downs
DROP TABLE tb_books;